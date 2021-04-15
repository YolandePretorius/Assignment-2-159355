package escalator159355;

import java.util.ArrayList;

public class Escalator {
	
	volatile static Boolean travelingDirectionVariable;
	static Boolean travelingDirectionOld;
	static int MaxnumberPassengers;
	static int numberPassengers;
	private long startTime = 0;
	private long endTime   = 0;
	static ArrayList<String> passengersOnLift = new ArrayList<String>();

	

	public Escalator() {
		travelingDirectionVariable = false;
		MaxnumberPassengers = 10;
	
	}
		
	//if floorNumberAt true then travel direction is  up to floor 2 if false then travel direction is down to floor 1
	synchronized void loadLift(Boolean TravelDirection) { //  FirstFloor = true; secondFloor = false;
			
		while(TravelDirection != travelingDirectionVariable)
			try {
				wait(); 	// Threads will wait their turn
			} catch (InterruptedException e) {
				offLoadLift();
				// TODO Auto-generated catch block
			//e.printStackTrace();
				
			}

		
		if(numberPassengers < MaxnumberPassengers) {
			passengersOnLift.add(Thread.currentThread().getName()); // add passengers on a list to keep track of thread names
			numberPassengers++;
			System.out.println(Thread.currentThread().getName()+" is entering escalator on "+getFloorNumber(TravelDirection));
			
		}
		
		if(numberPassengers==MaxnumberPassengers) {
			System.out.println();
			System.out.println("Escalator is full number of passengers: "+numberPassengers);
			System.out.println("Escalator will start moving towards:"+getFloorNumber((!TravelDirection)));
			System.out.println("");
			System.out.println("----------------------------------------------------------------------->");
	        System.out.println("");
			offLoadLift();  // function changes direction and empties lift
	}
		
		startTime = System.currentTimeMillis();
		// using system time to automatically change direction of thread after a time period
		while((endTime - startTime) < 100)
			try {
				endTime   = System.currentTimeMillis();
				wait(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}	
		changeDirection();
	
}
	// change direction 
	synchronized void offLoadLift() {//  FirstFloor = true; secondFloor = false;
		
		
		changeDirection(); // function changes direction and zeros list of passengers
		
		startTime = System.currentTimeMillis();
		// using system time to automatically change direction of thread after a time period
		while((endTime - startTime) < 100)
			try {
				endTime   = System.currentTimeMillis();
				wait(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}	
		changeDirection();
		
	    System.out.println(" ");
		
		
	}
	
	public synchronized void changeDirection() { 
		if(numberPassengers > 0) {
		System.out.println();
		System.out.println("Escalator stopped passengers can exit");
		System.out.println("");
		System.out.println(numberPassengers+" passengers are exiting the escalator on" + getFloorNumber(!(travelingDirectionVariable)));
		System.out.println();
		for (String thread : passengersOnLift) {
			System.out.println(thread+ " is exiting escalator");
		}
		System.out.println("");
		passengersOnLift.clear();
		numberPassengers = 0;
		System.out.println("Escalator direction changed to move towards" +getFloorNumber(travelingDirectionVariable));
		System.out.println();
		}
		travelingDirectionOld = travelingDirectionVariable; // change traveling direction
		travelingDirectionVariable = !travelingDirectionOld;
		notifyAll();
	}
	
	
	
	public static String getTravelDirectionName(Boolean dir) { // change floor number from boolean to a value
		if (dir == false) {
			 return "down to first floor";
		}
		else {
			return  "up to second floor";
		}
	}	
	
	public static String getFloorNumber(Boolean dir) { // passenger are currently on the floor number
		if (dir == false) {  
			 return " second floor";
		}
		else {
			return  " first floor";
		}
	}	

}
