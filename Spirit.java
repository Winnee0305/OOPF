
public class Spirit {

	private int value;
	private Screen screen = new Screen();
	private Controller controller = new Controller();
	
	public Spirit() {
		
	}
	
	public Spirit(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	//methods
	public String displayChargeSpirit() {
		screen.displayMessage("Mash button to charge your spirit! >> ");
		return controller.getString();
	}
	
	public void chargeSpirit() {
		String buttonMashed = displayChargeSpirit();
		int numMashed = buttonMashed.length();
		value += numMashed;
	}
	
	

}
