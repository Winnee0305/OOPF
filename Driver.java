

public class Driver {

	public static void main(String[] args) {
//		create the battle object in the game class
//		Calling the constructor by passing the opponentPokemon that get from stage.
//		Battle battle = new Battle();
//		battle.run();
		
		FileHandler file = new FileHandler("moveTypeEffectiveness.txt");
		System.out.println(file.getDataAt(0, 1));
	}

}
