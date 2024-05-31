!Name: Carl_Liu
!Oct 3 2023
program quadratic
  implicit none
  real :: a,b,c !Component of the quadratic equation ax^2 + bx + c
  real :: quad !the square root term of the quadratic formula

  write(*,*) "Input coefficents a,b,c of ax^2+bx+c with space in between"
  read(*,*) a,b,c

  quad=b**2-(4.0*a*c)

  if(quad<0)then
    write(*,*) "The first root is ", -b/(2*a), "+i", sqrt(abs(quad))/(2*a)
    write(*,*) "The second root is ", -b/(2*a), "-i", sqrt(abs(quad))/(2*a)

  elseif(quad>0)then
    write(*,*) "The first root is ", (-b-sqrt(b**2-(4.0*a*c)))/(2*a)
    write(*,*) "The second root is ",  (-b+sqrt(b**2-(4.0*a*c)))/(2.0*a)

  else
    write(*,*) "The root is", -b/(2.0*a)
    write(*,*) "This is the only root of this equation"

  endif

  stop 0
end program  
    
