
public class OpponentPokemon extends Pokemon {
	
	private int catchGauge;
	private int attackGauge;
	
	public OpponentPokemon() {
		super();
	}
	
	public OpponentPokemon(String name, String moveType, int hp, int defensePower, int attackPower, int catchGauge,
			int attackGauge, String zMove) {
		super(name, moveType, hp, defensePower, attackPower, catchGauge, attackGauge, zMove);
	}
	
	public OpponentPokemon(String pokemonCode) {
		super(pokemonCode);
	}

	public void chargeAttackGauge() {
		this.attackGauge++;
	}
	
}
