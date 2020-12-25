package Backend.models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Agent {

    private ArrayList<Territory> territories;

    public Agent(){
        territories = new ArrayList<>();
    }

    public void addArmies() {

    }
    public void attack() {
    }

    // human case
    public void addArmies(Territory territory, int armiesCount) {
    }

    public void attack(Territory from, Territory to, int attackDiceCount, int defendDiceCount) {
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    private void addTerritory(Territory territory) {
        this.getTerritories().add(territory);
    }

    // need to be implemented
    private void intializeTerritoriesAndArmies(){

    }

    public void moveArmies(Territory from, Territory to, int armiesCount){
        // validation check
        if(from.getAgent() != this || to.getAgent() != this)
            throw new Error("source or destination or both territories are not owned by the player");
        if(armiesCount > from.getArmySize() -1 )
            throw new Error("1 army at least must stay at the source territory to defend it");

        from.setArmySize(from.getArmySize() - armiesCount);
        to.setArmySize(to.getArmySize() + armiesCount);
    }

    protected void declareAttack(Territory from, Territory to, int attackDiceCount, int defendDiceCount){
        //check attack Dice count
        if(attackDiceCount > from.getArmySize() - 1) // number of dice is more than allowed number of armies
            attackDiceCount = from.getArmySize() - 1;

        if(defendDiceCount > to.getArmySize()) // number of dice of defender is more than number of armies
            defendDiceCount = to.getArmySize();
        if(to.getAgent() == null){
            to.setAgent(this);
            to.setArmySize(1);
            from.setArmySize(from.getArmySize() - 1);
            this.addTerritory(to);
        }else {
            ArrayList<Integer> attackerDice = new ArrayList<>(attackDiceCount);
            ArrayList<Integer> defenderDice = new ArrayList<>(defendDiceCount);
            Random rand = new Random();
            for (int i = 0; i < attackDiceCount; i++) {
                attackerDice.add(rand.nextInt(6) + 1);
            }
            for (int i = 0; i < defendDiceCount; i++) {
                defenderDice.add(rand.nextInt(6) + 1);
            }

            int numberFights = Math.min(attackDiceCount, defendDiceCount);
            while (numberFights != 0) {
                numberFights--;
                int attackerDie = Collections.max(attackerDice);
                attackerDice.remove(attackerDie);
                int defenderDie = Collections.max(defenderDice);
                defenderDice.remove(defenderDie);
                if (attackerDie > defenderDie) { // defender will lose one army
                    to.setArmySize(to.getArmySize() - 1);
                } else { // attacker will lose one army
                    from.setArmySize(from.getArmySize() - 1);
                }
                if (to.getArmySize() == 0) // all armies at the to Territory are defeated
                {
                    to.setAgent(this);
                    to.setArmySize(1);
                    from.setArmySize(from.getArmySize() - 1);
                    this.addTerritory(to);
                    break;
                }
            }
        }
    }
}
