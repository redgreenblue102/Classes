#!/bin/bash
IFILE=$1 #input file
OFILE=$2 #output file
if [[ $IFILE == "" || $OFILE == "" || $3 != "" ]]; #check for correct number of args
then
  echo "data file or output file not found" 
  exit 0
fi
if [[ -f $IFILE ]]; # check if file exists
then
  touch $OFILE #make output file then add each row and print to output file
  (awk 'BEGIN {FS = "[;:,]"} {sum = 0} { for(i = 1; i <= NF; ++i) sum+=$i} {print "Col", NR, ":",sum}' $IFILE > $OFILE)
else
  echo "$IFILE not found" #input file not found
  exit 0
fi
