import java.util.Random;

public class Turn extends Battle{
	
	private Pokemon attacker;
	private Pokemon defender1;
	private Pokemon defender2;
	private Boolean performAttack;
	private int damage = 0;
	
	
	public Turn (Pokemon a, Boolean b, AllyPokemon[] ap, OpponentPokemon[] op) {
		this.attacker = a;
		this.performAttack = b;
		if (b) { // user will perform attack
			this.defender1 = op[0];
			this.defender2 = op[1];
		}
		else { //user will not perform attack
			this.defender1 = ap[0];
			this.defender2 = ap[1];
		}
	}
	
	public int generateNum() {
		int [] numList = {1,2,3,4,5,6,7,8,9,10};
    	int randomIndex = new Random().nextInt(10);
        int randomNum = numList[randomIndex];
        
        System.out.println("Guess a number between 1-10 (The closer number you guest with the random number, the highest attack roulette you got) >>");
        return randomNum;
	}
	
	public int spinAttackRoulette() {
		int randomNum = generateNum();
        Controller controller = getController();
        int numGuessed = controller.getInt();

        int interval = Math.abs(randomNum - numGuessed);
        
        int [] roulette = {50, 50, 20, 20, 20, 10, 10, 10, 10, 10};
        System.out.println("Attack roulette you got: " + roulette[interval]);
        return roulette[interval];
	}
	
	public String spinDefenseRoulette() {
		int randomNum = generateNum();
        Controller controller = getController();
        int numGuessed = controller.getInt();

        int interval = Math.abs(randomNum - numGuessed);
        
        String [] roulette = {"Evasion rose", "Evasion rose", "Defense rose", "Defense rose", "none", "none", "none", "none", "none", "none"};
        System.out.println("Defense roulette you got: " + roulette[interval]);
        return roulette[interval];
	}
	
	public void chargeSpirit() {
		
	}
	
	public float calculateDamage(Pokemon attacker, Pokemon defender) {
        double random = 0.85 + (Math.random() * 0.15);
		double damage = (((attacker.getSpeed()) * (attacker.getAttackPower()/defender.getDefensePower())/50)+2) * effectivenessMoveType(attacker.getMoveType(), defender.getMoveType()) * random * attacker.getSTAB();
		return (float) damage;
	}
	
	public double checkEffectiveness(int idxAttacker, int idxDefender) {
		FileHandler file = new FileHandler("moveTypeEffectiveness.txt");
		return file.getDataAt(idxDefender, idxAttacker);
	}
	
	public double effectivenessMoveType(String attackerMoveType, String defenderMoveType) {
		String [] moveTypes = {"Normal" , "Fighting", "Poison", "Ground", "Flying", "Bug", "Rock", "Ghost", "Steel", "Fire", "Water", "Electric", "Grass", "Ice", "Psyhic", "Dragon", "Dark", "Fairy"};
		int idxAttacker = 0;
		int idxDefender = 0;
		for (int i = 0; i < moveTypes.length; i++) {
			if (moveTypes[i].equals(attackerMoveType)) {
				idxAttacker = i;
			}
			if (moveTypes[i].equals(defenderMoveType)) {
				idxDefender = i;
			}
		}
		 return checkEffectiveness(idxAttacker, idxDefender);
	}
	
	@Override
	public void run() {
		if (performAttack) {
			int attackRoulette = spinAttackRoulette();
			chargeSpirit();
			int damage1 = Math.round((calculateDamage(attacker, defender1)) * attackRoulette / 100 + 1);
			super.addBattleScore(damage1 * 2);
			int damage2 = Math.round((calculateDamage(attacker, defender2)) * attackRoulette / 100 + 1);
			super.addBattleScore(damage2 * 2);
		}
		else {
			String defenseType = spinDefenseRoulette();
			double defenseRoulette = 0;
			switch (defenseType){
			case "Evasion rose": defenseRoulette = 0.3;
			case "Defense rose": defenseRoulette = 0.6;
			}
			int damage1 = (int) Math.round((calculateDamage(attacker, defender1)) * defenseRoulette / 100 + 1);
			super.addBattleScore(damage1);
			int damage2 = (int) Math.round((calculateDamage(attacker, defender2)) * defenseRoulette / 100 + 1);
			super.addBattleScore(damage2);
		}
	}
}
