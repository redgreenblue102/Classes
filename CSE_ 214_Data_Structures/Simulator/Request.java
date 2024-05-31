package Simulator;

/**
 * Info about elevator request by a person.
 * @author Carl Liu
 * sec: 06
 */
public class Request {
    private int sourceFloor;
    private int destinationFloor;
    private int timeEntered;

    /**
     * constructor gives a random value for source and destination floor between 1 and floors inclusive
     * @param floors
     * of int type, how many floors are in the building should be greater than 1
     */
    public Request(int floors){
        this.sourceFloor = (int)(Math.random()*floors)+1;
        this.destinationFloor = (int)(Math.random()*floors)+1;

    }

    /**
     * allows user to set the source floor of request
     * @param src
     * int type, source floor of request
     */
    public void setSourceFloor(int src){
        this.sourceFloor = src;
    }

    /**
     * sets the destination floor of request
     * @param dest
     * int type, destination floor of request
     */
    public void setDestinationFloor(int dest){
        this.destinationFloor=dest;
    }

    /**
     * gets the source floor of the request
     * @return
     * returns int type that is the source floor of request
     */
    public int getSourceFloor(){
        return this.sourceFloor;
    }

    /**
     * gets the destination floor of request
     * @return
     * returns int type, destination floor of request
     */
    public int getDestinationFloor(){
        return this.destinationFloor;
    }

    /**
     * set the time that the request is put in
     * @param time
     * is of type int, should be positive and is the time the request is put in.
     */
    public void setTimeEntered(int time){
        this.timeEntered = time;
    }

    /**
     * gets the time the request is put in.
     * @return
     * returns type int, the time the request is put in
     */
    public int getTimeEntered(){
        return this.timeEntered;
    }
}
