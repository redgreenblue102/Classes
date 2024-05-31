package Simulator;

import Simulator.Simulator;

import java.util.Scanner;
/**
 * interface for user to interact with simulator
 * @author Carl Liu
 * sec: 06
 */
public class Analyzer {
    /**
     * asks the user for probability of request, floors in building, elevators in building, and run time, then runs the simulator
     * @param args
     * takes in input from terminal
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean task = true;
        double prob=0;
        int floors=0;
        int elevators=0;
        int time=0;
        while(task) {
            try {
                while (task) {
                    System.out.println("Input probability of elevator request as double between 0 and 1 inclusive");
                    prob = input.nextDouble();
                    task = false;
                    if ((prob < 0) || (prob > 1)) {
                        System.out.println("Probability isn't in proper range");
                        task = true;
                    }
                }
                task = true;
                while (task) {
                    System.out.println("Input number of floors in the building as an integer greater than 1");
                    floors = input.nextInt();
                    task = false;
                    if (floors <= 1) {
                        System.out.println("simulation time isn't in proper range");
                        task = true;
                    }
                }
                task = true;
                while (task) {
                    System.out.println("Input number of elevators in the building as an integer greater than 0");
                    elevators = input.nextInt();
                    task = false;
                    if (elevators <= 0) {
                        System.out.println("floors isn't in proper range");
                        task = true;
                    }
                }
                task = true;
                while (task) {
                    System.out.println("Input length of simulation as an integer greater than 0");
                    time = input.nextInt();
                    task = false;
                    if (time <= 0) {
                        System.out.println("floors isn't in proper range");
                        task = true;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Input doesn't match specification");
                System.out.println(ex);
                input.nextLine();
            }
        }
        double[] results = Simulator.simulate(prob, floors, elevators, time);
        System.out.println("Total wait time is " + results[0]);
        System.out.println("Total requests arrived at is " + results[1]);
        System.out.printf("Average wait time for requests is %.2f", results[2]);
    }
}
