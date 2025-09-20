#!/bin/bash
DIR=$1
BDIR=$2
if [[ $DIR == "" || $BDIR == "" || $3 != "" ]];
then
  echo "src and dest dirs missing" 
  exit 0
fi
if [[ -d $DIR ]]; then
  dir=$(find $DIR -type d -name "*")
  for directory in ${dir[@]};
  do
    backupdir=$BDIR${directory:${#DIR}}
    mkdir $backupdir &>/dev/null
    currdir=($(ls $directory | grep -E "*\.c"))
    filesindir=${#currdir[@]}
    for file in ${currdir[@]};
    do
      if [[ $filesindir -gt 3 ]];
      then
        read -p "Want to move file $file? [y,Y]" option
        if [[ $option == "y" || $option == "Y" ]];
        then
          mv $directory/$file $backupdir/$file
        fi;
      else 
        mv $directory/$file $backupdir/$file
      fi
    done
  done
else
  echo "$DIR not found"
  exit 0
fi