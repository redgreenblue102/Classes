#!/bin/bash
DIR=$1
if [[ -d $DIR ]]; #check if directory exists
then
  currdir=$(ls $DIR) #all files in directory
  for file in ${currdir[@]}; #for file in directory add score then check case for alphabetical score
  do
    awk 'BEGIN {FS = ","; sum=0;score="D"} NR>1 { for(i = 2; i <= 6; ++i){
    sum+=$i}; sum = 2*sum; if(sum >=93) {score="A"} else if(sum >=80) {score="B"} else if(sum >= 65) {score="C"}; printf "%d:%s\n", $1, score}' $DIR/$file
  done
else
  echo "$DIR not found" #directory not found
  exit 0
fi
