!Name: carl_liu
!Sep 19 2023
!Takes in velocity in m/s and mass in kg and returns
!nonrelativistic and relativistic kinetic energies
!The nonrelativistic limit does not match due to round off error
!at very small values of v, v/c gets very close to 0 and gets round to 0
!So we have gamma = (1/(1-0))0.5 = 1 and so mc^2(1-1)= 0
program relative_energy
  implicit none
  real :: mass !mass in kg
  real :: velocity ! velocity in m/s
  real, parameter :: C = 299792458.0 !speed of light in m/s
  real :: B !v/C where v is velocity and C is the speed of light
  real :: gamma ! the lorentz factor 1.0/(1-(B)**2)**0.5
  write(*,*) "input mass in kg"
  read(*,*) mass
  write(*,*) "input velocity in m/s"
  read(*,*) velocity
  B = velocity/C
  gamma = 1.0/(1-(B)**2)**0.5
  write(*,*) "nonrelativistic kinetic energy is", 0.5*mass*velocity**2 &
       , "Joules"
  write(*,*) "relativistic kinetic energy is", (mass*C**2)*(gamma-1), "Joules"
  stop 0
end program relative_energy
