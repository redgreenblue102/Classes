class Array
 def [](index) #index method
  if((index >= length())||(index < -length())) #return \0 if index outside length bounds
   return '\0'
  else #returns similar at method
   return at(index)
  end
 end
 
 def map(sequence =nil, &block) #map method
  ary = [] #initialize empty array
  if(sequence ==nil) #no sequence behavior
   (0..(length()-1)).each {|x| ary.push(block.call(at(x)))}
  else #behavior given a sequence
   ((sequence.to_a) & ((-length())..(length()-1)).to_a).each {|x| ary.push(block.call(at(x)))}
  end
  return ary
 end
end

#Test Cases
a=[1,2,34,5]
puts a[1]
puts a[10]
p a.map(2..4) {|i| i.to_f}
p a.map{|i| i.to_f}

b=["cat","bat","mat","sat"]
puts b[-1]
puts b[5]
p b.map(2..10) {|x| x[0].upcase + x[1,x.length]}
p b.map(2..4) {|x| x[0].upcase + x[1,x.length]}
p b.map(-3..-1) {|x| x[0].upcase + x[1,x.length]}
p b.map {|x| x[0].upcase + x[1,x.length]}
