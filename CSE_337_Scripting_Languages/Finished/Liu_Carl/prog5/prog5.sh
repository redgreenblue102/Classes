#!/bin/bash
IFILE=$1 #text file	
DFILE=$2 #dictionary file
if [[ $IFILE == "" || $DFILE == "" || $3 != "" ]]; #check for correct number of args
then
  echo "input file and dictionary missing" 
  exit 0
fi
if [[ -f $IFILE ]]; #checking files exists
then
  if [[ -f $DFILE ]];
  then #checking each word for 4 letters then adding to array. After adding check each word in array with dictionary
    
    (awk ' BEGIN {IGNORECASE=1;total = 0} NR==FNR { for(i = 1; i <= NF; ++i){if(length($i) == 4){++total ;fourw[total]=$i} } } 
     FNR < NR {for(i = 1; i<=length(fourw); i++){ if(tolower(fourw[i]) == tolower($1)){fourw[i] = ""}} }
     END {for(i =1; i <=length(fourw); i++){if(fourw[i] != ""){print fourw[i]}}}' $IFILE $DFILE  )
  else
    echo "$DFILE is not a file"
  fi
  
else
  echo "$IFILE is not a file"
  exit 0
fi
