
public class AllyPokemon extends Pokemon {
	
	private int attackGauge;
	private Screen screen = new Screen();
	private Controller controller = new Controller();

	public AllyPokemon(String pokemonCode, int a) {
		super(pokemonCode);
		this.attackGauge = a;
	}

	public AllyPokemon(Pokemon pokemon,int a) {
		super(pokemon.getName(), pokemon.getMoveType(), pokemon.getHp(), pokemon.getDefensePower(), pokemon.getAttackPower(), pokemon.getCatchGauge(), pokemon.getAttackGauge(), pokemon.getzMove());
		// TODO Auto-generated constructor stub
	}

	public AllyPokemon(String pokemonCode) {
		super(pokemonCode);
		// TODO Auto-generated constructor stub
	}

	public void chargeAttackGauge(int charge) {
		this.attackGauge += charge;
	}
	
	public void displayDetails() {
		screen.displayMessageLine("Pokemon name: " + getName());
		screen.displayMessageLine("Move Type: " + getMoveType());

	}
}