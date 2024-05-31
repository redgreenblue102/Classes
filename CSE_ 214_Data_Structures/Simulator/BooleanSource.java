package Simulator;

/**
 * returns true or false based on probability given.
 * @author Carl Liu
 * sec: 06
 */
public class BooleanSource {
    private double probability;

    /**
     * Constructor that takes in the probability for true.
     * @param prob
     * is of type double, should be between 0 and 1 inclusive, probability of true.
     */
    public BooleanSource(double prob){
        this.probability = prob;
    }

    /**
     * returns true based on probability, simulated request arrival
     * @return
     * returns boolean which is true when random number between 0 and 1 is less than probability
     */
    public boolean requestArrived(){
        return Math.random() < this.probability;
    }
}
