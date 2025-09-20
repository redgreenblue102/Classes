#!/bin/bash
function file_count(){
    echo -n $1:
    (ls $1 | awk 'BEGIN {sum=0} {sum = sum+1} END {print sum}')
}

file_count /etc
file_count /var
file_count /usr/bin
(awk '{print $1 $4}' employee.txt)