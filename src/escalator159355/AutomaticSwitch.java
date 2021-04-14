package escalator159355;

public class AutomaticSwitch implements Runnable
{

	private Escalator lift;
	private Boolean autoSwitch;

	public AutomaticSwitch(Boolean switchAuto, Escalator escalator) { // Switch on is true, Switch off it is false, start with on
		this.autoSwitch = switchAuto;
		this.lift = escalator;
		
	}

	@Override
	public void run() {
//		for (int i = 0; i < 10; i++) {
//			//lift.changeDirection(autoSwitch);
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
	}

}
