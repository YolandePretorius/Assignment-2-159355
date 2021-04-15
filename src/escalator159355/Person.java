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
	
			//lift.loadLift(travelDirection);
		if(Thread.currentThread().isInterrupted()) {
			return;
		}else {
			try {
				//System.out.println("THREAD ENTERING =========" + Thread.currentThread().getName());
				lift.loadLift(travelDirection);
				//System.out.println("++++++++++++THREAD EXIT" + Thread.currentThread().getName());
				Thread.sleep(300);		
			} 
			catch (InterruptedException e) {
				return;
			}	
		}
	}

}
