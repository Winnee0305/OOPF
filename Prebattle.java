import java.util.Arrays;

public class Prebattle {
	
	private AllyPokemon [] allyPokemons = new AllyPokemon [2];
	private OpponentPokemon [] opponentPokemons = new OpponentPokemon[2];
	private String [] pokemonCodes = {"123456", "23456", "34567"}; //just an example
	private Screen screen = new Screen();
	private Controller controller = new Controller();
	private int numPokemonEntered = 0;
	
	public Prebattle() {
		
	}
	
	public Prebattle(AllyPokemon[] allyPokemons, OpponentPokemon[] opponentPokemons, String[] pokemonCodes, Screen screen,
			Controller controller) {
		this.allyPokemons = allyPokemons;
		this.opponentPokemons = opponentPokemons;
		this.pokemonCodes = pokemonCodes;
	}
	
	
	
	public AllyPokemon[] getAllyPokemons() {
		return allyPokemons;
	}

	public void setAllyPokemons(AllyPokemon[] allyPokemons) {
		this.allyPokemons = allyPokemons;
	}

	public OpponentPokemon[] getOpponentPokemons() {
		return opponentPokemons;
	}

	public void setOpponentPokemons(OpponentPokemon[] opponentPokemons) {
		this.opponentPokemons = opponentPokemons;
	}

	public String[] getPokemonCodes() {
		return pokemonCodes;
	}

	public void setPokemonCodes(String[] pokemonCodes) {
		this.pokemonCodes = pokemonCodes;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void increaseNum() {
		this.numPokemonEntered ++;
	}

	public void addAllyPokemon(String pokemonCode){
		AllyPokemon newAllyPokemon = new AllyPokemon(pokemonCode, 0);
		newAllyPokemon.displayDetails();
		allyPokemons[numPokemonEntered] = newAllyPokemon;
		increaseNum();
	}
	
	public void addAllyPokemon(Pokemon newPokemon, int index) {
		allyPokemons[index] = new AllyPokemon(newPokemon, 0);	
	}
	
	public Pokemon generateRentalPokemon() {
		// some calculation
		Pokemon newPokemon = new Pokemon ();
		return newPokemon;
	}
	
	public void addRentalPokemon() {
		while (numPokemonEntered != 2) { // if ally pokemons not enough 2
			Pokemon newAllyPokemon = generateRentalPokemon();
			addAllyPokemon(newAllyPokemon, numPokemonEntered);
		}
	}
	
	public void selectAllyPokemon(){
		while (numPokemonEntered != 2) { //check ally pokemon 1 and 2 is added successfully
			screen.displayMessage("Enter the code of pokemon to battle (do not have enough pokemon? enter 0 to get rental pokemon) >> ");
			String pokemonCode = controller.getString();
			if (pokemonCode.equals("0")) { //user do not have enough pokemon
				addRentalPokemon();
				break;
			}
			else {
				if (!Arrays.asList(pokemonCodes).contains(pokemonCode)) {
					screen.displayMessage("The code does not exist, please try another code, press ENTER to continue >>");
					controller.getString();
				}
				else {
					addAllyPokemon(pokemonCode);
				}
			}
		}
	}
	
	public void prepare() {
		selectAllyPokemon();
	}
}
