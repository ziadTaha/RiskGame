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
        // adding bonus armies s bonus armies to the territory with the fewest armies
        int bonusArmies =  this.getTerritories().size() / 3;
        if(bonusArmies< 3)
            bonusArmies = 3;
        fewestArmiesTerritory.setArmySize(fewestArmiesTerritory.getArmySize() + bonusArmies);
        attackingTerritory = fewestArmiesTerritory;
    }
    @Override
    public void attack() {
        Territory from = attackingTerritory;
        Territory to;
        to = from.getNeighbors().get(0);
        for(Territory neighbour: from.getNeighbors()){
            if(neighbour.getArmySize() < to.getArmySize())
                to = neighbour;
        }
        //attack with 3 dice for attacker and two for
        declareAttack(from, to, 3, 2);
    }
}
