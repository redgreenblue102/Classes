def contain_virus(isInfected)
  count = 0 #Total walls needed
  for i in 0...isInfected.length 
    for k in 0...isInfected[i].length #loop through array
      if(isInfected[i][k] == 1) #check if infected
        #When infected the below code will check if any block in the 4 directions is infected if not add 1 to the count
        if(i-1 <0 || isInfected[i-1][k] ==0) 
          count +=1
        end
        if((i+1) >=isInfected.length || isInfected[i+1][k] ==0)
          count +=1
        end
        if((k-1) < 0 || isInfected[i][k-1] ==0)
          count +=1
        end
        if((k+1) >=isInfected[i].length || isInfected[i][k+1] ==0)
          count +=1
        end
      end
    end
  end
  return count
end
#Test cases for infected
isInfected = [[1,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
result = contain_virus(isInfected)
puts "Number of walls needed #{result}" 

isInfected = [[1,1,0,1],[0,1,1,1],[0,0,0,0],[0,0,0,0]]
result = contain_virus(isInfected)
puts "Number of walls needed #{result}" 

isInfected = [[1,1,1,1,1],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]
result = contain_virus(isInfected)
puts "Number of walls needed #{result}" 

isInfected = [[1,1,1,1,1],[1,0,0,0,0],[1,0,0,0,0],[0,0,0,0,0]]
result = contain_virus(isInfected)
puts "Number of walls needed #{result}" 

isInfected = [[1,0,0],[0,0,0],[0,0,0],[0,0,0]]
result = contain_virus(isInfected)
puts "Number of walls needed #{result}" 