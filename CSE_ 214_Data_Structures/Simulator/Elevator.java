package Simulator;

import Simulator.Request;

/**
 * Implementation of an elevator for a building
 * @author Carl Liu
 * sec: 06
 */
public class Elevator {
    private int currentFloor;
    private final int IDLE = 0;
    private final int TO_SOURCE = 1;
    private final int TO_DESTINATION = 2;
    private int elevatorState;
    private Request request;

    /**
     * Constructor for an elevator, initializes it on idle and floor 1
     */
    public Elevator(){
        this.elevatorState = this.IDLE;
        this.currentFloor = 1;
    }

    /**
     * gets the current floor the elevator is on
     * @return
     * returns type int, which is the current floor of the elevator
     */
    public int getCurrentFloor() {
        return this.currentFloor;
    }

    /**
     * gets the state of the elevator
     * @return
     * returns int, 0 for idle, 1 for going to source, 2 for going to destination.
     */
    public int getElevatorState(){
        return this.elevatorState;
    }

    /**
     * gets the request that the elevator is doing
     * @return
     * returns Simulator.Request object which contains info about the source and destination for elevator
     */
    public Request getRequest(){
        return this.request;
    }

    /**
     * sets the currentfloor that the elevator is on
     * @param floor
     * is of type int, sets the current floor of elevator should be greater than 0
     */
    public void setCurrentFloor(int floor){
        this.currentFloor = floor;
    }

    /**
     * sets the state of the elevator
     * @param state
     * is of type int, should be 0 for idle, 1 for going to source, and 2 for going to destination
     */
    public void setElevatorState(int state){
        this.elevatorState = state;
    }

    /**
     * sets the request that the elevator is doing
     * @param request
     * is of type Simulator.Request, is the request that the elevator will fulfill.
     */
    public void setRequest(Request request){
        this.request = request;
    }

}
