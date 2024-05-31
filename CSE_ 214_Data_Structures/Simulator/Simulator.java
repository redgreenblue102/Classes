package Simulator;

/**
 * Simulator.Simulator for elevator fulfilling requests in a building
 * @author Carl Liu
 * sec: 06
 */
public class Simulator {
    /**
     * takes in information about the building and run time for simulation then outputs average wait and requests completed as well as total wait of requests.
     * @param prob
     * is of type double, probability for request to come in, in a time unit, should be between 0 and 1 inclusive
     * @param floors
     * is of type int, number of floors in the building, should be greater than 1
     * @param elevators
     * is of type int, number of elevators in the building, should be greater than 0
     * @param runTime
     * the amount of time units that the simulator will run for, should be greater than 0
     * @return
     * returns an array of double, index 0 is total wait time, index 1 is total request sources arrived at, index 2 is average wait time
     */
    public static double[] simulate(double prob, int floors, int elevators, int runTime){
        BooleanSource probRequest = new BooleanSource(prob);
        Elevator[] elevator = new Elevator[elevators];
        boolean debug = false;
        for(int i = 0; i <elevators; i++){
            elevator[i] = new Elevator();
        }
        RequestQueue queue = new RequestQueue();
        int time = 0;
        int totalWait = 0;
        int sourceArrived = 0;
        if(debug){
            System.out.println("Info of elevator is before movement in time frame");
        }
        while(time <= runTime){
            //adds a request based on probability given
            if(probRequest.requestArrived()) {
                Request request = new Request(floors);
                request.setTimeEntered(time);
                queue.enqueue(request);
                if(debug){
                    System.out.println("Time entered: " + request.getTimeEntered() + " Source Floor: " + request.getSourceFloor() + " Dest FLoor: " + request.getDestinationFloor());
                }

            }
            //goes through each elevator in the building and goes through necessary actions
            for(int i = 0; i < elevators; i++){
                if(debug){
                    System.out.print("Simulator.Elevator: " + i + " State: " + elevator[i].getElevatorState() +
                            " CurrentFloor: " + elevator[i].getCurrentFloor());
                    if(elevator[i].getElevatorState() != 0){
                        System.out.println(" Simulator.Request Time: " + elevator[i].getRequest().getTimeEntered());
                    }else
                        System.out.println();
                }
                //checks if there are requests to put on an elevator
                if((elevator[i].getElevatorState() == 0) && (!queue.isEmpty())) {
                    elevator[i].setRequest(queue.dequeue());
                    elevator[i].setElevatorState(1);

                }
                // elevator goes toward source
                if(elevator[i].getElevatorState() == 1) {
                    if(elevator[i].getCurrentFloor()>elevator[i].getRequest().getSourceFloor())
                        elevator[i].setCurrentFloor(elevator[i].getCurrentFloor() - 1);
                    else if (elevator[i].getCurrentFloor()<elevator[i].getRequest().getSourceFloor())
                        elevator[i].setCurrentFloor(elevator[i].getCurrentFloor() + 1);
                    else {
                        elevator[i].setElevatorState(2);
                        totalWait += time - elevator[i].getRequest().getTimeEntered();
                        sourceArrived++;
                    }
                }
                //elevator goes towards destination
                if(elevator[i].getElevatorState() == 2){
                    if(elevator[i].getCurrentFloor() > elevator[i].getRequest().getDestinationFloor())
                        elevator[i].setCurrentFloor(elevator[i].getCurrentFloor() - 1);
                    else if(elevator[i].getCurrentFloor()<elevator[i].getRequest().getDestinationFloor()){
                        elevator[i].setCurrentFloor(elevator[i].getCurrentFloor() + 1);
                    }
                    if(elevator[i].getCurrentFloor() == elevator[i].getRequest().getDestinationFloor()){
                        elevator[i].setElevatorState(0);
                        elevator[i].setRequest(null);
                    }
                }
            }
            time++;
        }
        double[] results = {totalWait, sourceArrived, ((double)totalWait)/sourceArrived};
        return results;
    }
}
