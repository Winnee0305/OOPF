
public class Battle {

	private Prebattle prebatlle;
	private int battleScore;
	private OpponentPokemon [] opponentPokemons;
	private AllyPokemon [] allyPokemons;
	private int numAllyPokemon = 2;
	private Screen screen;
	private Controller controller = new Controller();
	private char[] initialButton;
	
	public Battle() {
		
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



	public Battle(OpponentPokemon[] opponentPokemons) {
		
		this.opponentPokemons = opponentPokemons;
	}



	public int getBattleScore() {
		return battleScore;
	}


	public void setBattleScore(int battleScore) {
		this.battleScore = battleScore;
	}

	public OpponentPokemon[] getOpponentPokemons() {
		return opponentPokemons;
	}

	public void setOpponentPokemons(OpponentPokemon[] opponentPokemons) {
		this.opponentPokemons = opponentPokemons;
	}

	public AllyPokemon[] getAllyPokemons() {
		return allyPokemons;
	}

	public void setAllyPokemons(AllyPokemon[] allyPokemons) {
		this.allyPokemons = allyPokemons;
	}
	
	///methods
	
	public void addBattleScore(int score) {
		this.battleScore += score;
	}
	
	public void increaseNumAlly() {
		this.numAllyPokemon ++;
	}
	
	public void startTurn() {
		
	}
	
	public Boolean checkHp(Pokemon [] pokemonList) {
		Boolean continueBattle = true;
		for (Pokemon i : pokemonList) {
			int hp = i.getHp();
			if (hp <= 0) {
				continueBattle = false;
			}
			break;
		}
		return continueBattle;
	}
	
	public Boolean checkCatchGauge() { // only opponent pokemon have to check catch gauge
		Boolean continueBattle = true;
		for (Pokemon i : opponentPokemons) {
			int catchGauge = i.getCatchGauge();
			if (catchGauge >= 100) {
				continueBattle = false;
			}
			break;
		}
		return continueBattle;
	}
	
	public Boolean checkReplacement() {
		Boolean canReplace = false;
		if (numAllyPokemon < 4) {
			canReplace = true;	
		}
		return canReplace;
	}
	
	public Boolean checkAttack() {
		Boolean canAttack = false;
		for (AllyPokemon a : allyPokemons) {
			if (a.getAttackGauge()==100) {
				canAttack = true;
			}
		}
		for (OpponentPokemon o : opponentPokemons) {
			if (o.getAttackGauge()==100) {
				canAttack = true;
			}
		}
		return canAttack;
	}
	
	public void displayBattleMessage() {
		screen.displayMessageLine("Ally pokemon 1");
		screen.displayMessageLine("------------------------");
		screen.displayMessageLine(allyPokemons[0].displayHp());
		screen.displayMessageLine(allyPokemons[0].displayAttackGauge());
		screen.displayMessageLine("");
		screen.displayMessageLine("Ally pokemon 2");
		screen.displayMessageLine("------------------------");
		screen.displayMessageLine(allyPokemons[1].displayHp());
		screen.displayMessageLine(allyPokemons[1].displayAttackGauge());
		screen.displayMessageLine("");
		screen.displayMessageLine("------------------------");
		screen.displayMessageLine(opponentPokemons[1].displayHp());
		screen.displayMessageLine(opponentPokemons[1].displayAttackGauge());
		screen.displayMessageLine("");
		screen.displayMessageLine("------------------------");
		screen.displayMessageLine(opponentPokemons[1].displayHp());
		screen.displayMessageLine(opponentPokemons[1].displayAttackGauge());
		screen.displayMessageLine("");
	}
	
//	public void chargeAttackGauge() {
//		initializeButton();
//		while (checkAttack()) {
//			screen.displayMessage("Mash the buttons to charge Attack Gauge !!");
//			String btnMashed = controller.getString();
//			for (int i = 0; i < btnMashed.length(); i++) {
//				if (btnMashed.charAt(i) == initialButton[0]) {
//					allyPokemons[0].chargeAttackGauge(1);
//				}
//				if (btnMashed.charAt(i) == initialButton[0]) {
//					allyPokemons[1].chargeAttackGauge(1);
//				}
//			}
//			opponentPokemons[0].chargeAttackGauge();
//			opponentPokemons[1].chargeAttackGauge();
//			displayBattleMessage();
//		}
//	}
	
	public String checkAttacker() {
		String attacker = null;
		for (AllyPokemon a : allyPokemons) {
			if (a.getAttackGauge()==100) {
				attacker = "ally";
			}
		}
		for (OpponentPokemon o : opponentPokemons) {
			if (o.getAttackGauge()==100) {
				attacker = "oppoenent";
			}
		}
		return attacker;
	}
	
	public Pokemon initializeAttackerPokemon() {
		String attacker = checkAttacker();
		Pokemon attackerPokemon = null;
		if (attacker.equals("ally")) {
			for (AllyPokemon i : allyPokemons) {
				if (i.getAttackGauge() == 100) {
					attackerPokemon = i;
					break;
				}
			}
		}
		else {
			for (OpponentPokemon i : opponentPokemons) {
				if (i.getAttackGauge() == 100) {
					attackerPokemon = i;
					break;
				}
			}
		}
		return attackerPokemon;
	}
	
	public Boolean checkPerformAttack(Pokemon attackerPokemon) {
		Boolean attack = false;
		for (AllyPokemon i : allyPokemons) {
			if (i.equals(attackerPokemon)) {
				attack = true;
				break;
			}
		}
		return attack;
	}
	
	public void startBattle() { // charge attack,check attack, then start battle
//		chargeAttackGauge();
		Pokemon attackerPokemon = initializeAttackerPokemon();
		Turn turn = new Turn(attackerPokemon, checkPerformAttack(attackerPokemon), allyPokemons, opponentPokemons);
	}
	
	public void run() {
		Prebattle prebattle = new Prebattle();
		prebattle.prepare();
		setAllyPokemons(prebattle.getAllyPokemons());
		
		//condition to check before start a turn
		while (checkHp(opponentPokemons) && checkCatchGauge()){ // check opponent hp first and catch gauge first, either both must be continue battle (catchgauge<100 and hp>0)
			if (checkHp(allyPokemons)) { // check ally hp 
				startBattle();
			}
			else{
				if (checkReplacement()){ //can replace pokemon
					setAllyPokemons(prebattle.replacement(allyPokemons));
				}
				else { //cannot replace pokemon
					break; //end game
				}
			}
		}
	}
}
