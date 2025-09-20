#TEST CASES
#testing empty arguments
./prog2.sh
#testing f.txt to nonexistent file fadd.txt
./prog2.sh f.txt fadd.txt
#testing f2.txt to existing file f2add.txt
./prog2.sh f2.txt f2add.txt
#testing f3.txt to non existing file f3add.txt
./prog2.sh f3.txt f3add.txt
#testing singular argument
./prog2.sh f3.txt
