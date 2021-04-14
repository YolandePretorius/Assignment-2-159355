package escalator159355;

public class Escalator {
	
	volatile static Boolean travelingDirectionVariable;
	static Boolean travelingDirectionOld;
	static int MaxnumberPassengers;
	static int numberPassengers;
	private long startTime = 0;
	private long endTime   = 0;
	private long timeDifference = 0;

	

	public Escalator() {
		travelingDirectionVariable = false;
		MaxnumberPassengers = 2;

	
	
	}
	
	
	//if floorNumberAt true then travel direction is  up to floor 2 if false then travel direction is down to floor 1
	synchronized void loadLift(Boolean TravelDirection) { //  FirstFloor = true; secondFloor = false;
		
		
		while(TravelDirection != travelingDirectionVariable)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
//		
//		endTime   = System.currentTimeMillis();
//		if((numberPassengers==MaxnumberPassengers) || (endTime - startTime)==2) {
//			System.out.println("Escalator is full number of passengers: "+numberPassengers);
//			System.out.println("Escalator will start moving towards:"+getFloorNumber((!TravelDirection)));
//			System.out.println("");
//			System.out.println("-----------------------------------------------------------------------");
//	        System.out.println("");
//			offLoadLift();
//		}
		
		if(numberPassengers < MaxnumberPassengers) {
			numberPassengers++;
			System.out.println(Thread.currentThread().getName()+" is entering escalator, waiting to traveling from "+getFloorNumber(TravelDirection)+" to" + getFloorNumber((!TravelDirection)));
		}
		
		if(numberPassengers==MaxnumberPassengers) {
			System.out.println("Escalator is full number of passengers: "+numberPassengers);
			System.out.println("Escalator will start moving towards:"+getFloorNumber((!TravelDirection)));
			System.out.println("");
			System.out.println("-----------------------------------------------------------------------");
	        System.out.println("");
			offLoadLift();
	}

		
		
		
		
//				
//			while(startTime !=  endTime)
//					try {
//						wait();
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				
//				 offLoadLift();
			//}
				
			
//			
//			while((endTime - startTime) < 1)
//				try {
//					wait();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			   offLoadLift();
//			
			
	}
	
	synchronized void offLoadLift() {//  FirstFloor = true; secondFloor = false;
		
		System.out.println("Escalator stopped passengers can exit");
		//System.out.println(numberPassengers+" passengers are exiting the escalator on" + getFloorNumber(!(travelingDirectionVariable)));
		numberPassengers = 0;
		
//		travelingDirectionOld = travelingDirectionVariable; // change traveling direction
//		travelingDirectionVariable = !travelingDirectionVariable;
		
		changeDirection();
		
		startTime = System.currentTimeMillis();
		
		while((endTime - startTime) < 10)
			try {
				//endTime   = System.currentTimeMillis();
				wait(100);
				endTime   = System.currentTimeMillis();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}	
		changeDirection();
		
		
		//endTime   = System.currentTimeMillis();
		
	
	//	timeDifference = endTime - startTime;
		
				
//		System.out.println(startTime);
//		System.out.println(endTime);
//		System.out.println(timeDifference);
//		System.out.println(" ");
//		
//		System.out.println("Escalator is changing direction from going " + getTravelDirectionName(travelingDirectionOld) + " to going " + getTravelDirectionName(travelingDirectionVariable));
//		
//		System.out.println(" ");
		
		
	}
	
	public synchronized void changeDirection() { 
		System.out.println("changing direction+++++++++");
		travelingDirectionOld = travelingDirectionVariable; // change traveling direction
		travelingDirectionVariable = !travelingDirectionOld;
		System.out.println("Escalator is changing direction from going " + getTravelDirectionName(travelingDirectionOld) + " to going " + getTravelDirectionName(travelingDirectionVariable));
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
	
	public static String getFloorNumber(Boolean dir) {
		if (dir == false) {
			 return " second floor";
		}
		else {
			return  " first floor";
		}
	}	

}
