#TEST CASES
#dictionary being used may be missing some 4 letter words
#missing arguments
./prog5.sh
#invalid files
./prog5.sh fdki dkfji
#check text for mispells
./prog5.sh prob5-sample.txt dictionary.txt
#checking another text
./prog5.sh test.txt dictionary.txt
#changing to a bad dictionary with only a few words
./prog5.sh test.txt baddict.txt
