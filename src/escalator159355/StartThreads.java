package escalator159355;

import java.util.ArrayList;

public class StartThreads {
	
	static int MaxnumberPassengers=5;

	 static Boolean FirstFloor = true;  // travel direction is up
	 static Boolean SecondFloor = false;// travel direction is down
	 static Boolean changeDir = false;
	 
	 static ArrayList<Thread> threadsFirstFloor = new ArrayList<Thread>();
	 static ArrayList<Thread> threadsSecondFloor = new ArrayList<Thread>();
	 
	
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("");
		System.out.println("      ############   Welcome to the escalator  #############");
		System.out.println(" ");
		
		Escalator escalator = new Escalator();
		
		Person personGoingUp = new Person(FirstFloor,escalator); // travel up : person is standing on first floor 
		Person personGoingDown = new Person(SecondFloor,escalator); // travel down : person is standing on second floor
		
		AutomaticSwitch AutoSwitch = new AutomaticSwitch(changeDir,escalator);
		
		Thread changeDirectionThread = new Thread(AutoSwitch,"change direction");
		
		changeDirectionThread.start();
		
		for (int i = 1; i <= MaxnumberPassengers; i++) { // Create Threads that travels up : person standing on first floor
			//added++;
			Thread customerTravelUp = new Thread(personGoingUp,"Customer #"+i);
			threadsFirstFloor.add(customerTravelUp);
			customerTravelUp.start();
			
		}
		
		for (int i = 1; i <= MaxnumberPassengers; i++) { // Create Threads that travels down : person standing on second floor
			//added++;
			Thread customerTravelDown = new Thread(personGoingDown,"Customer #"+i);
			threadsSecondFloor.add(customerTravelDown);
			customerTravelDown.start();
			
		}
		
		for (Thread thread : threadsFirstFloor) {
			thread.interrupt();
			thread.join();
		
		}
		
		for (Thread thread : threadsSecondFloor) {
			thread.interrupt();
			thread.join();
		
		}
		
	}

}
