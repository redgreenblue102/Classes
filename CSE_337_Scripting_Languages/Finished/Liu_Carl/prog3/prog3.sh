#!/bin/bash
IFILE=$1 #input file
ALL=${@:2} #weights
if [[ -f $IFILE ]]; #check if input file exists
then #add the weighted score of each row then divide entire thing by the total weight
  awk -v usrin="$ALL" 'BEGIN {FS = ","; split(usrin,arr, " ");tweight=0;sum=0;} NR>1 { for(i = 2; i <= NF; ++i){
     if(i <=length(arr)+1){sum+=arr[i-1]*$i;tweight+=arr[i-1]}else{sum+=$i;tweight+=1}} }  END {print int(sum/tweight)}' $IFILE
else
  echo "$IFILE not found" #file not found
  exit 0
fi

