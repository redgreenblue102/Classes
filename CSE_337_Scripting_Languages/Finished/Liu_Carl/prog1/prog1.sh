#!/bin/bash
DIR=$1 #directory of the files to be moved
BDIR=$2 #directory to move the c files to
if [[ $DIR == "" || $BDIR == "" || $3 != "" ]]; #checking for extra args or too few args
then
  echo "src and dest dirs missing" 
  exit 0
fi
if [[ -d $DIR ]]; then #checking if directory given is a directory
  dir=$(find $DIR -type d -name "*") #finding all subdirectories in directory
  for directory in ${dir[@]}; 
  do
    backupdir=$BDIR${directory:${#DIR}}
    mkdir $backupdir &>/dev/null #for each directory make backup directory
    currdir=($(ls $directory | grep -E "*\.c"))  #all files that have .c extension in current directory
    filesindir=${#currdir[@]}
    for file in ${currdir[@]}; 
    do
      if [[ $filesindir -gt 3 ]]; #for each file in directory check if there are more than 3, if yes ask to move
      then
        read -p "Want to move file $file? [y,Y]" option
        if [[ $option == "y" || $option == "Y" ]];
        then
          mv $directory/$file $backupdir/$file #moving files to backup
        fi;
      else 
        mv $directory/$file $backupdir/$file
      fi
    done
  done
else
  echo "$DIR not found" #directory not found
  exit 0
fi
