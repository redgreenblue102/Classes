!Name: Carl Liu
!Nov 7 2023
!Solves the Poisson equation of a 2-D steady state em problem
program poisson
implicit none
integer,parameter :: NX = 100
integer,parameter :: NY = 100
real, parameter :: PI = 3.14159265
integer :: i
integer :: j
real, dimension(0:NY+1,0:NX+1) :: u
real, dimension(0:NY+1,0:NX+1) :: q
real, dimension(0:NY+1,0:NX+1) :: unew
real :: h = 0.5
real :: max = 1.0
real :: x=0,y=0
integer :: iter = 0
integer :: lun
q = 0.0
q(75,75) = 4
u =0.0
unew = 0.0
do while (max > 1.0e-7)
  do j = 1, NX , 1
    do i = 1, NY, 1
       unew(i,j) = (u(i+1,j) + u(i-1,j) + u(i,j+1)+u(i,j-1) &
       -4*PI*(h**2)*q(i,j))/4.0
    enddo
  enddo
max = maxval(abs(unew-u))
u = unew
iter = iter +1
enddo

open(newunit = lun, file = "poisson.txt", status = "replace")
x= 0.5*h
do j = 0, NX+1, 1
y = 0.5*h
  do i = 0, NY+1, 1
    write(lun,*) x, y, unew(i,j)
    y = y+h
  enddo
x= x+h
enddo
close(unit = lun)
write(*,*) unew(25,50), unew(25,25), unew(75,75), iter
stop 0
end program 