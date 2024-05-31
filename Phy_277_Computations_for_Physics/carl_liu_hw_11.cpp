//Name: carl_liu
//Dec 3 2023
//finds roots of the quadratic Ax^2+ Bx+C
#include <iostream>
#include <cmath>

int main(){
  double A; //Coefficients of Ax^2+Bx+C
  double B;
  double C;

  double q; // Evaluation of B^2-4AC of the quadratic function 
  std::cout << "Input coefficients A, B, and C" << std::endl;
  std::cin >> A >> B >> C;  //Getting user input for coefficients
  q = pow(B,2)-(4*A*C);

  if(A == 0){ // Root of linear equation
    std::cout << "The only root is " << -C/B << std::endl;
  }
  else if(q == 0){ //Root when q = 0
    std::cout << "The only root is " << -B/(2*A) << std::endl;
  }
  else if(q > 0){ //Root when sqrt(q) is real results in real roots
    std::cout << "The first root is " << (-B+sqrt(q))/(2*A) << std::endl;
    std::cout << "The second root is " << (-B-sqrt(q))/(2*A) << std::endl;
  }
  else if(q < 0){ //Root when sqrt(q) is complex results in complex roots
    std::cout << "The first root is " << -B/(2*A) <<
      "+i" << sqrt(std::abs(q))/(2*A) << std::endl;
    std::cout << "The second root is " << -B/(2*A) <<
      "-i" << sqrt(std::abs(q))/(2*A) << std::endl;
  }
  return 0;
}
