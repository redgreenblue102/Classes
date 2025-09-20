#!/usr/bin/env ruby

if ARGV[0] ==  nil ||(File.exist?(ARGV[0]) == false) #Checks if there are arguments and first argument is file
  puts("Missing required arguments")
else
  file = File.open(ARGV.shift())
  ARGV.each { |arg|    #Checks to see if all arguments are proper
    if(arg =~ /^-[^0-9wpvcm]$/) then file.close
      puts("Invalid option")
      exit()
    end
  }
  pattern = ARGV.pop() #pops pattern off the end 
  if( pattern == nil)  # if there is no pattern then exit
    file.close
    puts("Missing required arguments")
    exit()
  end
  if(ARGV.uniq.length != ARGV.length) # Checks if there are duplicate arguments
    file.close
    puts ("Invalid combination of options")
  end
  
  w = Proc.new {|line| if(line.split(" ").include?(pattern)) then puts(line) end} # procs for executing arguments
  p = Proc.new {|line| if(line =~Regexp.new(pattern)) then puts line end}
  v = Proc.new {|line| if(!(line =~Regexp.new(pattern))) then puts line end}
  if (ARGV.empty?) # the case when no arguments are provided, p is used
    pattern = Regexp.new(pattern)
    file.each {|line| p.call(line)}
    file.close()
    exit()
  end
  if (ARGV.include?("-c") && !ARGV.include?("-m") )  # The case when using -c
    count = 0
    multiple = 0
    if (ARGV.include?("-p"))  #-c -p option
      rpattern = Regexp.new(pattern)
      multiple +=1
      file.each {|line| if(line =~rpattern) then count +=1 end}
    end
    if (ARGV.include?("-v")) #-v -p option
      rpattern = Regexp.new(pattern)
      multiple+=1
      file.each {|line| if(!(line =~rpattern)) then count +=1 end}
    end
    if (ARGV.include?("-w"))# -w -p option
      multiple+=1
      file.each {|line| if(line.split(" ").include?(pattern)) then count +=1 end}
    end
    if multiple ==1 #puts count when only one of the options is chosen
      puts(count)
    else
      puts("Invalid combination of options")
    end
  
  end
  if (!ARGV.include?("-c") && ARGV.include?("-m") ) #The case when using -m argument we will
    multiple = 0
    counts = 0
    if (ARGV.include?("-w")) #finding number of multiples
      multiple +=1
    end
    if (ARGV.include?("-p"))
      multiple +=1
    end
    if multiple == 1
      if (ARGV.include?("-w")) #-m -w option
        
        file.each {|line| if(line.split(" ").count(pattern)>0)then puts pattern*line.split(" ").count(pattern) end}
      end
      if (ARGV.include?("-p"))# -m -p option
        
        file.each {|line| if(line.scan(Regexp.new(pattern))> 0) then puts pattern *line.scan(Regexp.new(pattern)) end}
      end

        
    else
      puts("Invalid combination of options")
    end
    
  
  end
  if (!ARGV.include?("-c") && !ARGV.include?("-m") )# checks the case without -c and -m
    multiple = 0
    if (ARGV.include?("-p")) #checks for multiples 
      multiple +=1
    end
    if (ARGV.include?("-v"))
      multiple+=1
    end
    if (ARGV.include?("-w"))
      multiple+=1
    end
    if multiple ==1
      if (ARGV.include?("-p"))  #The cases for each argument
        file.each {|line| p.call(line)}
      end
      if (ARGV.include?("-v"))
        file.each {|line| v.call(line)}
      end
      if (ARGV.include?("-w"))
        file.each {|line| w.call(line)}
      end
    else
      puts("Invalid combination of options")
    end
  
  end
  file.close
end