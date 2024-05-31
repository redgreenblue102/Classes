!Name: Carl_Liu
!Oct 16 2023
!Calculates the apparent magnitude of light based on phase of
!the given day
program apparent_magnitude
  implicit none
  real :: days ! days inputed by user
  integer :: periods !How many periods have passed
  real :: phase !phase in a period
  real, parameter :: PI = 3.14159265359 !constant value of pi
  character(len=:), allocatable :: phrase
  phrase  = "is the apparent magnitude"

  write(*,*) "Input number of days"   !User inputs of days
  read(*,*) days
  periods = days/6.4
  phase = days - (periods*6.4)
  
  if((0<=phase).and.(phase<0.9)) then !execute in first part of the period
    write(*,*) 2.5, phrase
    
  elseif((0.9<= phase).and.(phase<2.3)) then  !execute in second part of period
    write(*,*) 3.335-log(1.352+cos(PI*(phase-0.9)/0.7)), phrase
    
  elseif((2.3<=phase).and.(phase<4.4)) then  !execute in third part of period
   write(*,*) 2.5, phrase
   
  elseif((4.4<=phase).and.(phase<5.2)) then  !execute in fourth part of period
   write(*,*) 3.598-log(1.998+cos(PI*(phase-4.4)/0.4))
   
  elseif((5.2<=phase).and.(phase<6.4)) then  !execute in fifth part of period
   write(*,*) 2.5, phrase
   
  endif
  stop 0
end program
