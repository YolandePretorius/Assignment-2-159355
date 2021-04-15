package escalator159355;

import java.util.ArrayList;
import java.util.Random;

public class StartThreads {
	
	static int MaxnumberPassengers=100;
	
	static Boolean floornumber;  // travel direction is up
	
	
	static ArrayList<Thread> threadsList = new ArrayList<Thread>(); // list to store random moving threads


	public static void main(String[] args) throws InterruptedException {
		System.out.println("");
		System.out.println("      ############   Welcome to the Escalator  #############");
		System.out.println(" ");
		
		Escalator escalator = new Escalator(); // create a new escalator thread
		
		
		for (int i = 1; i <= MaxnumberPassengers; i++) { // Create Threads that will randomly enter the lift
			floornumber = getTravelDirectionName();
			Person person = new Person(floornumber,escalator);  // create a new person wit a random travel direction that will enter the escalator
			Thread customerTakingLift = new Thread(person,"Customer #"+i);
			threadsList.add(customerTakingLift);
			customerTakingLift.start();
		}
		
		// stop threads on list 
		for (Thread thread : threadsList) {
			thread.interrupt();
			thread.join();
		
		}
		
		System.out.println("!!!!!!  Have great day !!!!!!");
		
	}
	
	// function gets a random number between 1 (customer is on floor number 1) and 2 (customer is on floor number 2)
	public static Boolean getTravelDirectionName() {
		Random randomNumber = new Random();
		int directionTravel = randomNumber.nextInt(2-1+1)+1;// random number determines direction
		if(directionTravel == 1) {
			floornumber = true;
		}else {
			floornumber = false;
		}
		
		return(floornumber);
	}
	
}
