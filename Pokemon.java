
public class Pokemon {
	
	private String name;
	private String moveType;
	private int hp;
	private int defensePower;
	private int attackPower;
	private int catchGauge;
	private int attackGauge;
	private String zMove;
	private int speed;
	private int STAB; // same type attack bonus (move type = pokemon type)
	
	public Pokemon() {
	}
	
	public Pokemon(String pokemonCode) {
		setName("Pikachu");
	}
	
	public Pokemon(String name, String moveType, int hp, int defensePower, int attackPower, int catchGauge,
			int attackGauge, String zMove) {
		super();
		this.name = name;
		this.moveType = moveType;
		this.hp = hp;
		this.defensePower = defensePower;
		this.attackPower = attackPower;
		this.catchGauge = catchGauge;
		this.attackGauge = attackGauge;
		this.zMove = zMove;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDefensePower() {
		return defensePower;
	}

	public void setDefensePower(int defensePower) {
		this.defensePower = defensePower;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getCatchGauge() {
		return catchGauge;
	}

	public void setCatchGauge(int catchGauge) {
		this.catchGauge = catchGauge;
	}

	public int getAttackGauge() {
		return attackGauge;
	}

	public void setAttackGauge(int attackGauge) {
		this.attackGauge = attackGauge;
	}

	public String getzMove() {
		return zMove;
	}

	public void setzMove(String zMove) {
		this.zMove = zMove;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSTAB() {
		return STAB;
	}

	public void setSTAB(int sTAB) {
		STAB = sTAB;
	}

	public String displayHp() {
		return String.format("Healh point: %d", getHp());
	}
	
	public String displayAttackGauge() {
		return String.format("Healh point: %d", getAttackGauge());
	}
}

	

