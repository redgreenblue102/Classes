#TEST CASES
#testing testingprog3.txt with weight 1 2 3 4 5
./prog3.sh testingprog3.txt 1 2 3 4 5
#testing testingprog3.txt with no weights so default 1
./prog3.sh testingprog3.txt
#testing testingprog3.txt with weights 1 2 3 so the rest is 1
./prog3.sh testingprog3.txt 1 2 3
#testing testingprog3.txt with weights 1 2 3 4 5 6 7 8 which gets truncates at 5
./prog3.sh testingprog3.txt 1 2 3 4 5 6 7 8
#testing program with nonexistent file
./prog3.sh notafile.txt
