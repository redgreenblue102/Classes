!Name: Carl Liu
!Nov 3 2023
!Finds the mean, median and standard deviation of a data set
program stats
implicit none
integer :: lun,lun2 !lun for file
integer :: lines = 0 !number of lines in file
real, dimension(:), allocatable :: data !The data from the file
integer :: i,j !The loop index
integer :: error=0, error2 = 0 !The error status of I/O
character(len=100) :: input !Name of the input file
character(len=100) :: output !Name of the output file
real :: sum !sum of all the data
real :: mean !mean of the data
real :: median !median of the data
real :: sd !standard deviation of the data
real :: swap !will be swapped with min in selection sort
real :: min !min in the unsorted array to be used during sorting
integer :: pmin !position of min in array

write(*,*) "Input the name of the file to be read"
read(*,*) input
write(*,*) "Input the name of the file to output to" 
read(*,*) output
open(newunit =lun, file = input,status = "OLD", IOSTAT = error)
open(newunit = lun2, file =output, status ="NEW", IOSTAT = error2)
!Opens the file

if((error/=0).or.(error2 /= 0)) then !Stops program when there is an error in file opening
  write(*,*) "File did not open"
  stop 1
endif

do while(error == 0) !continues reading while there is still data
  read(lun, *, iostat = error) sum
  if(error ==0) then !adds to the amount of lines when still data
    lines = lines+1
  else
    exit
  endif
enddo

allocate(data(lines)) !allocate data array with lines
rewind(lun) !go back to start of file

sum=0.0
do i=1, lines, 1 !read data in file to array and add data together
  read(lun,*) data(i)
  sum = sum+data(i)
enddo

close(unit = lun) !close input file

mean = sum/(1.0*lines) !compute mean of data
do i=1, lines-1, 1 !Does selection sort
  min = data(i)
  pmin = i
    do j=i, lines, 1 !Goes through non sorted part for min value
      if(min>data(j)) then
        min = data(j)
        pmin = j
      endif
    enddo
  swap = data(i)!swap position of min value and ith place of array
  data(i) = min
  data(pmin) = swap
enddo

do i =1, lines, 1
  write(lun2, *) data(i) !write sorted values into output file
enddo
close(unit = lun2) !close output file

if(mod(lines,2)==1) then !if number is odd median is defined
  median = data((lines/2) +1)
else !if number is even median is defined
  median = (data(lines/2) + data((lines/2) +1))/2
endif

do i=1, lines, 1  !add together the squared component of standar dev
  sd =sd + (data(i)-mean)**2
enddo
sd = sqrt(sd/(1.0*lines)) !finish calculating standard dev
deallocate(data) !deallocate array data

write(*,*) "The amount of data points is", lines !print results
write(*,*) "The mean is", mean
write(*,*) "The median is", median
write(*,*) "The standard deviation is", sd

stop 0
end program
