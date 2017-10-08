/* MovingCompanyCrew: contains a list of jobs in a day's work and an hoursOfWork() method
*/

import java.util.ArrayList;

public class MovingCompanyCrew implements Comparable<MovingCompanyCrew> {
	ArrayList<Double> jobsOfCrew = new ArrayList<Double>();

	@Override
	public int compareTo(MovingCompanyCrew o) {
		if(this.hoursOfWork() > o.hoursOfWork()){
			return 1;
		} else if (this.hoursOfWork() < o.hoursOfWork()){
			return -1;
		} else return 0;
	}

	public Double hoursOfWork() {
		Double total = 0.0;
		for (int i = 0; i < jobsOfCrew.size(); i++) {
			total = jobsOfCrew.get(i) + total;
		}
		return total;
	}

	public void printAssignments(){
		for(int i = 0; i < jobsOfCrew.size(); i++){
			System.out.print("job " + i + ": " + jobsOfCrew.get(i) + ", ");
		}
		System.out.print("Total hours of work: " + this.hoursOfWork());
	}
}