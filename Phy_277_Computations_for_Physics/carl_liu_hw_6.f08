!Name: Carl_Liu
!Oct 17 2023
!Computes a summation given a certain index and at a certain x value
program summation
  implicit none
  integer:: user !User input for N that the summation will sum to
  real  :: sum=0.0 !sum of the summation after each iteration
  real :: x !User input for x to evaluate at
  integer :: i !Index for the loop
  
  write(*,*) "Input an integer N to do the summation to and real x>0"
  read(*,*) user, x
  
  do i = user, 1,-1 !backwards iteration
    sum= (sum + ( ((-1)**i) * (x**(2*i)) ) )/((2.0*i)*(2.0*i-1))
    !doing factorial part by dividing whole sum by factorial element that
    !is decrementing down. So lets say N=5 then it starts by dividing total
    !sum by 10 and 9 then by 8 and 7 and so on
  enddo
  sum = sum+1

  write(*,*) sum, "is the total"
  stop 0
end program
