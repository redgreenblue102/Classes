cmake_minimum_required(VERSION 3.0.0)
project(HW1 VERSION 0.1.0 LANGUAGES C CXX)
include_directories(cppTPS)
#include_directories(cppTPSTester)
include_directories(TripPlanner)
add_subdirectory(cppTPS)
#add_executable(HW1 cppTPSTester/cppTPSTester.cpp)
add_executable(HW1 TripPlanner/TripPlanner.cpp)
target_link_libraries(HW1 cppTPS)
include(CTest)
enable_testing()

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
