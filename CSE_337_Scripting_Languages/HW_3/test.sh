#!/bin/bash
cat /etc/shadow
if [[ $? == 0 ]];
 then
 echo "command succeded"
 else
 echo "Command failed"
fi