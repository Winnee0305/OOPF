import java.util.*;

public class Controller {
	private Scanner input;
	public Controller() {
		input = new Scanner(System.in);
	}
	
	public String getString() {
		return input.nextLine();
	}
	
	public char getChar() {
		return input.next().charAt(0);
	}
	
	public int getInt() {
		return input.nextInt();
	}
}
