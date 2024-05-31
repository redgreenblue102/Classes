//Name: carl_liu
//Dec 8 2023
//fits line ax+b to data points given in file and also outputs 
//chi-squared value. Also outputs data points sorted by x coord
#include <iostream>
#include <fstream>
#include <cmath>

void selectionSort(double x_array[], double y_array[],int pts);
void linefit(double x_array[], double y_array[], double line[], int pts);

int main(){
 
  double x, y;
  double *x_array{nullptr}, *y_array{nullptr}; //array of x and y values
  double line[2]; //array for slope and intercept
  std::string inputFile; //input file name
  std::string outputFile;//output file name
  std::ifstream inFile; //input file object
  std::ofstream outFile; //output file object
  int pts = 0;//number of lines in input file

  std::cout << "name of input file" << std::endl;
  std::cin >> inputFile;
  std::cout << "name of output file" << std::endl;
  std::cin >> outputFile;
  
  inFile.open(inputFile);
  while(inFile.good()){ //finding number of lines
    inFile >> x >> y;
    if(inFile.fail()){
      break;
    }
    pts++;
  }
  inFile.clear();
  inFile.seekg(0,std::ios::beg);
  //initiate array to amount of data points
  if(x_array == nullptr) {x_array = new double[pts];}
  if(y_array == nullptr) {y_array = new double[pts];}
  //read from file to arrays
  for(int i = 0; i < pts; i++){
    inFile >> x_array[i] >> y_array[i];
  }
  inFile.close();
  inFile.clear();

  selectionSort(x_array, y_array, pts); //sorts data based on
  //x coord using selection sort
  linefit(x_array, y_array, line,  pts);// fits data to a line by 
  //minimizing chi-squared

  outFile.open(outputFile);
  for(int i = 0; i < pts; i++){//outputting sorted data
    outFile << x_array[i] << " " <<y_array[i] << " " << 
      line[0]*x_array[i] + line[1] << std::endl; //line of best fit
    //evaluated at each x coord
  }
  outFile.close();
  return 0;
}
//selection sorting function that sorts by x coord
void selectionSort(double x_array[], double y_array[], int pts){

  double temp;
  int index;
  double min;
  
  for(int i = 0; i < pts-1; i++){
    min = x_array[i];
    index = i;
    for(int j = i; j < pts; j++){
      if(min>x_array[j]){
	min = x_array[j];
        index = j;
      }
    }
    temp = x_array[i];
    x_array[i] = min;
    x_array[index] = temp;
    temp = y_array[i];
    y_array[i] = y_array[index];
    y_array[index] = temp;
  }
}
//line fitting function
void linefit(double x_array[], double y_array[], double line[], int pts){
  double sx_i; //sum over x_i
  double sx_ix_i; //sum over x_i*x_i
  double sy_i; //sum over y_i
  double sx_iy_i; //sum over x_i*y_i
  double m; //m is the slope of best fit
  double b; //b is the intercept of best fit
  double chi; // chi-squared value
  for(int i = 0; i < pts; i++){ //summing for values to find fit
    sx_i += x_array[i];
    sx_ix_i += x_array[i]*x_array[i];
    sy_i += y_array[i];
    sx_iy_i += x_array[i]*y_array[i];
  }
  m = (pts*sx_iy_i - sx_i*sy_i)/(pts*sx_ix_i - sx_i*sx_i);//slope
  b = (sy_i -m*sx_i)/(1.0*pts);//y intercept
  std::cout << "The slope of the best fit line is " << m
	    << " and the y intercept is " << b<< std::endl;
  std::cout << "This makes the line of best fit " << m <<"x" 
	    << "+" << b << std::endl;
  for(int i = 0; i < pts; i++){
    chi += pow(m*x_array[i] +b -y_array[i],2);
  }
  std::cout << "The chi-squared value is " << chi <<std::endl;
  line[0] = m; //line of best fit info
  line[1] = b;
}
