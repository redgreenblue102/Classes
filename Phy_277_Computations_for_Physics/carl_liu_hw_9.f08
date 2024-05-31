!Name: Carl Liu
!Nov 7 2023
!Solves the Poisson equation of a 2-D steady state em problem
!At (25,50) we have the value 3.178E-02 as the electric potential
program poisson
implicit none
integer,parameter :: NX = 100 !Number of squares in x direction in box
integer,parameter :: NY = 100 !Number of squares in y direction in box
real, parameter :: PI = 3.14159265
integer :: i !Index to iterate over
integer :: j !Index to iterate over
real, dimension(0:NY+1,0:NX+1) :: u !old electric potential
real, dimension(0:NY+1,0:NX+1) :: q !charge density 
real, dimension(0:NY+1,0:NX+1) :: unew !new electric potential
real :: h = 0.1 !size of squares
real :: max = 1.0 !max value to check how many iterations for poisson
real :: x=0.0,y=0.0 !Values for x and y directions
integer :: iter = 0 !Holds how many iterations
integer :: lun !lun of file to write to

q = 0.0 !Setting charge density array to 0
q(25,25) = -4 !Setting charge density at certain points
q(75,75) = 4
u =0.0  !Setting old and new electric potential array to 0
unew = 0.0

do while (max > 1.0e-9) 
!Will run until difference in old and new potential is miniscule
  do j = 1, NX , 1 !Loops through the whole array and apply
!relaxation
    do i = 1, NY, 1
       unew(i,j) = (u(i+1,j) + u(i-1,j) + u(i,j+1)+u(i,j-1) &
       -4*PI*(h**2)*q(i,j))/4.0  !Equation used for relaxation 
    enddo
  enddo
max = maxval(abs(unew-u)) !Sets new max to largest diff of old and new
u = unew !Sets old array to new array
iter = iter +1
enddo

!Open file to write gnuplot formatted array to
open(newunit = lun, file = "poisson.txt", status = "replace") 

x= -0.5*h !Sets the value for x
do j = 0, NX+1, 1
y = -0.5*h !sets the value for y
  do i = 0, NY+1, 1 
    !Write each potential with x and y values
    write(lun,*) x, y, unew(i,j)
    y = y+h !Increase y value by a square length
  enddo
x= x+h !Increase x value by a square length
enddo

close(unit = lun)
write(*,*) "At (25,50) we have", unew(25,50)
write(*,*) "There has been", iter, "iterations" 
stop 0
end program 