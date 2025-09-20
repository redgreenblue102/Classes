#TEST CASES
#move from project 2 directory to nonexisting directory src
./prog1.sh project2 src
#move from src to project2
./prog1.sh src project2
#move from project to project_src_bkup
./prog1.sh project project_src_bkup
#test moving empty from project_src_bkup to project
./prog1.sh project project_src_bkup
#testing empty arguments
./prog1.sh

