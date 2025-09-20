
module Towable
  def can_tow?(pounds) #Checks if it is towable
    if pounds < 2000
      return true
    else 
      return false
    end
  end
end

class Vehicle
  @@number_of_vehicles = 0 #Total number of vehicles
  def initialize(year, model, color) #Initialize car with conditions
    @year  = year
    @model = model
    @color = color
    @current_speed = 0
  end
  def speed_up(number)  #Increase speed 
    @current_speed += number
    return "You push the gas and accelerate #{number} mph"
  end
  def brake(number) #Decrease speed
    @current_speed -= number
    return "You push the brake and decelerate #{number} mph"
  end
  def shut_down #Sets speed to 0 and shuts down the car
    @current_speed = 0
  end
  def spray_paint(given_color) #Paint car a different color
    @color = given_color
    return "Your new #{@color} paint job looks great!"
  end
  def self.gas_mileage(number1, number2) #number 1 is gallons used and number 2 is miles drove finds mileage
    return "#{(number2/number1).round} miles per gallon of gas "
  end
  def current_speed() #speed currently at
    return "You are now going #{@current_speed} mph"
  end
  def self.number_of_vehicles #returns number of vehicles
    return "There are #{@@number_of_vehicles} vehicles"
  end
end


class MyCar < Vehicle
  include Towable
  NUMER_OF_DOORS = 4 #Descriptors of car
  TYPE = "car"
  def initialize(year,model,color) #Makes a MyCar object
    super
    @@number_of_vehicles += 1
  end
  def shut_down # Shuts down the car
    super
    return "Lets park the #{TYPE}!"
  end
  def to_s # returns a string description of the car
    return "My #{TYPE} is a #{@color}, #{@year}, #{@model}!"
  end
end  
class MyTruck < Vehicle
  include Towable
  NUMER_OF_DOORS = 2 #Descriptors of truck
  TYPE = "truck"
  def initialize(year,model,color) #Makes a MyTruck obeject
    super
    @@number_of_vehicles += 1
  end
  def shut_down #Shuts down the truck
    super
    return "Lets park the #{TYPE}"
  end
  def to_s # returns string description of truck
    return "My #{TYPE} is a #{@color}, #{@year}, #{@model}!"
  end
end

#Test cases
puts lumina = MyCar.new(1997, 'chevy lumina', 'white')
puts lumina.speed_up(20)
puts lumina.current_speed
puts lumina.speed_up(20)
puts lumina.current_speed
puts lumina.to_s
puts lumina.brake(20)
puts lumina.current_speed
puts lumina.brake(20)
puts lumina.current_speed
puts lumina.shut_down
puts MyCar.gas_mileage(13, 351)
puts lumina.spray_paint("red")
puts ram = MyTruck.new(1990, 'GMC', "black")
puts ram.can_tow?(1000)
puts lumina.can_tow?(3000)
puts Vehicle.number_of_vehicles
puts ram.speed_up(30)
puts ram.brake(30)
puts ram.current_speed
puts ram.shut_down
