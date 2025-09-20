#!/bin/bash
IFILE=$1
OFILE=$2
if [[ $IFILE == "" || $OFILE == "" || $3 != "" ]];
then
  echo "data file or output file not found" 
  exit 0
fi
if [[ -f $IFILE ]];
then
  touch $OFILE
  (awk 'BEGIN {FS = "[;:,]"} {sum = 0} { for(i = 1; i <= NF; ++i) sum+=$i} {print "Col", NR, ":",sum}' $IFILE > $OFILE)
else
  echo "$IFILE not found"
  exit 0
fi

