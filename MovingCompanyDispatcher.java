/* Randomly generates a day's worth of jobs for H households using V van crews
   H and V may be passed through a constructor or a newDay() method.

   [Suggestion: create an array of MovingCompanyCrew objects;
                a MovingCompanyCrew has a list of jobs and an hoursOfWork() method]

   Orders jobs from longest to shortest and assigns jobs in order to the first available crew.

   Prints the assignment and hours worked by the last crew to get off work.
   */

// imports

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MovingCompanyDispatcher {
 	private int nCrews = 0;
 	private int nHouseholds = 0;
 	private Double[] jobs = null;
	private Random rand = new Random();
 	private MovingCompanyCrew[] crews = null;

	public MovingCompanyDispatcher() {
			nHouseholds = rand.nextInt(10) + 1;
			nCrews = rand.nextInt(5) + 1;
	}


 	public void newDay(int C, int H) {
		this.nCrews = C;
		this.nHouseholds = H;
 	}

 	public double getLowerBound() {
 		// lower bound on number of hours worked by last crew to get off work
		Double total = 0.0;
		for(Double current: jobs){
			total+=current;
		}

 		return (total / nCrews);
 	}

 	public void makeJobs() {
 		// generate random jobs
		this.jobs = new Double[nHouseholds];
		for(int i = 0; i < this.nHouseholds; i++){
			jobs[i] = Math.random() * (4 - 1) + 1;
		}
 	}

 	public MovingCompanyCrew whichCrew(){
		Double currentMin = 0.0;
		MovingCompanyCrew current = crews[0];
		for(int i = 1; i < nCrews; i++){
			if (current.hoursOfWork() >= crews[i].hoursOfWork()){
				current = crews[i];
			}
		}
		return current;
	}

 	public void assignJobs() {
 		// order jobs longest to shortest 
		Arrays.sort(jobs);
		Collections.reverse(Arrays.asList(jobs));

		crews = new MovingCompanyCrew[nCrews];
 		// assign each job in order to first available crew
		for(int i = 0; i < nCrews; i++){
			crews[i] = new MovingCompanyCrew();
		}

		for(int i = 0; i < jobs.length; i++) {
			MovingCompanyCrew current = whichCrew();
			current.jobsOfCrew.add(jobs[i]);
		}
 	}

 	// this method lets the client figure out how good a job the Dispatcher did
 	// Alternatively, you can let the Dispatcher do its own self-evaluation 
 	// and expose the stats in different method
	/*
 	public MovingCompanyCrew[] getCrews() {

 	} */

 	public static void main(String[] args) {
		MovingCompanyDispatcher disp = new MovingCompanyDispatcher();
		disp.makeJobs();
		disp.assignJobs();
		Double lower = disp.getLowerBound();
		for(int i = 0; i < disp.nCrews; i++){
			System.out.println("Crew #" + i + ":");
			disp.crews[i].printAssignments();
			System.out.println();
		}
		System.out.println("Lower bound: " +  disp.getLowerBound());
		Double lastHours = disp.crews[disp.crews.length - 1].hoursOfWork();
		System.out.println("Last crew works " + lastHours  + ", which is " + (lastHours - disp.getLowerBound()) + " hours over the lower bound");
		System.out.println("This is  " +  disp.getLowerBound() / (lastHours-disp.getLowerBound()) + " percent of the lower bound.");
 	}

 }