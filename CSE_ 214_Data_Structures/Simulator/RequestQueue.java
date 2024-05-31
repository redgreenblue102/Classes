package Simulator;

import java.util.ArrayList;
/**
 * Queue of requests for the elevator, standard first in first out,  extends ArrayList class.
 * @author Carl Liu
 * sec: 06
 */
public class RequestQueue extends ArrayList<Request>{
    /**
     * adds request onto the queue
     * @param request
     * is of type request, adds request to queue
     */
    public void enqueue(Request request){
        this.add(request);
    }

    /**
     * removes request from queue
     * @return
     * returns type Simulator.Request, removes the first request in queue.
     */
    public Request dequeue(){
       return this.remove(0);
    }
    // isEmpty and size method not implemented because ArrayList already has it and this is a subclass of ArrayList

}
