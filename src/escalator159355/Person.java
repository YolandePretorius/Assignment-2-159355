package escalator159355;

public class Person implements Runnable{
	 

	private Boolean travelDirection;
	private Escalator lift;

	public Person(Boolean travelDir, Escalator escalator) {
		this.travelDirection = travelDir; //  FirstFloor = true; secondFloor = false;
		this.lift = escalator;
	}

	@Override
	public void run() {
		lift.loadLift(travelDirection);
		
	}

}
