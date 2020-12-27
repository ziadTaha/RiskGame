package Backend.nonAIAgents;

import Backend.models.Agent;
import Backend.models.Territory;

public class PacifistAgent extends Agent {
    private Territory attackingTerritory;
    public PacifistAgent(){
        super();
    }

    @Override
    public void addArmies() {
        Territory fewestArmiesTerritory = this.getTerritories().get(0);
        for(Territory ownedTerritory: this.getTerritories()){
            if(ownedTerritory.getArmySize() < fewestArmiesTerritory.getArmySize())
                fewestArmiesTerritory = ownedTerritory;
        }
        // adding bonus armies to the territory with the fewest armies
        int bonusArmies =  calculateBonus();
        fewestArmiesTerritory.setArmySize(fewestArmiesTerritory.getArmySize() + bonusArmies);
        attackingTerritory = fewestArmiesTerritory;
    }

    public void attack() {
        Territory from = attackingTerritory;
        Territory to;
        to = from.getNeighbors().get(0);
        for(Territory neighbour: from.getNeighbors()){
            if(neighbour.getArmySize() < to.getArmySize() && neighbour.getAgent() != this)
                to = neighbour;
        }

        //attack with 3 dice for attacker and two for
        if(to.getAgent() != this && from.getArmySize() > 1){
            declareAttack(from, to, 3, 2);
            if(to.getAgent() == this){
                moveArmies(from, to, from.getArmySize() - 1);
            }
        }

    }
}
