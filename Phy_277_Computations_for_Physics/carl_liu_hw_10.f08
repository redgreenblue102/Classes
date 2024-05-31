!Name: Carl_Liu
!Now 16 2023
!Computes the integral given in the assignment using simpson's rule
!intervals   integral
!1           184.48739308470485
!2	     9528.1909396755545
!5	     281.87397473018302
!10	     2148.7112983520642
!20	     750.63753523877278
!50	     883.33984994064770
!100	     1023.9408162596677
!200	     993.62350989530489
!500	     993.63146996594890
!1000	     993.63146994286342
!1500	     993.63146994162832
!2000	     993.63146994142255
!5000        993.63146994132796
!10000       993.63146994132433
!20000       993.63146994131705
!I believe the most accurate value is at 5000 subintervals
!with a value of 993.63146994132796 because the values here do
!not vary as much. This is better than the convergence of the 
!previous program at 500 subintervals and of value 993.630554
!because this program converges at much smaller subintervals 
!which means a more accurate answer 
module simpson
implicit none
  integer, parameter :: dp = kind(1.0d0) !kind for double precision
contains
  subroutine integrate(intervals) !integrating subroutine
    implicit none
    integer, intent(in) :: intervals !number of subintervals
    real(kind=dp) :: sum !sum of all the subintervals
    real(kind=dp) :: dx !length of subintervals
    real(kind=dp) :: fi, fipd, fip1 
    !value of function at ith subinterval at points
    !of starting, middle, and end of subinterval 
    integer :: i !variable for incrementing index
    real(kind=dp) :: start = 1.0  !Starting x for the integral
    real(kind=dp) :: end = 20.0 !Ending x for the integral

    dx = (end-start)/(intervals*1.0d0)    
    sum = 0.0d0

    do i=0, intervals-1, 1 !integrating by summing over subintervals
      !using simpsons rule
      fi = func(1.0d0 + dx*i)
      !calculating value at left side of subinterval
      fipd = func(1.0d0 + dx*i + dx/2.0d0)
      !calculating value at midpoint of subinterval
      fip1 = func(1.0d0+dx*(i+1))
      !calculating value at right side of subinterval 
      sum = sum+ (fi+(4*fipd)+fip1) 
      !adding together using simpsons rule 
    enddo
    sum = sum*dx/6.0d0 !finishing using simpsons rule

    write(*,*) "We have evaluated the integral to", sum
    return
  end subroutine integrate
 
  real(kind=dp) function func(x)!function evaluation
    implicit none
    real(kind=dp), parameter  :: A = 4000.0 !constant term given
    real(kind=dp), parameter :: B = 15.15 !constant term given
    real(kind=dp), parameter :: C = 0.01 !constant term given
    real(kind = dp), intent(in) :: x
    func = (x+cos(x))*exp(cos(x)) + A*exp((-(x-B)**2)/C)
    !given function evaluated at x
    return 
  end function func
end module


program bettersimpson !main program
  use simpson
  implicit none
  integer :: intervals
  write(*,*) "Input number of subintervals"
  read(*,*) intervals 
  call integrate(intervals) !calls and integrates
  
  stop 0
end program
