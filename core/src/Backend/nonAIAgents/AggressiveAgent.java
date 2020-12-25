package Backend.nonAIAgents;

import Backend.models.Agent;
import Backend.models.Territory;

public class AggressiveAgent extends Agent {
    public AggressiveAgent(){
        super();
    }

    @Override
    public void addArmies() {
        Territory mostArmiesTerritory = this.getTerritories().get(0);
        for(Territory ownedTerritory: this.getTerritories()){
            if(ownedTerritory.getArmySize() > mostArmiesTerritory.getArmySize())
                mostArmiesTerritory = ownedTerritory;
        }
        // adding bonus armies s bonus armies to the territory with the most armies
        int bonusArmies =  this.getTerritories().size() / 3;
        if(bonusArmies< 3)
            bonusArmies = 3;
        mostArmiesTerritory.setArmySize(mostArmiesTerritory.getArmySize() + bonusArmies);
    }

    @Override
    public void attack() {
        Territory from;
        Territory to;
        from = this.getTerritories().get(0);
        for(Territory ownedTerritory: this.getTerritories()){
            if(ownedTerritory.getArmySize() > from.getArmySize())
                from = ownedTerritory;
        }

        to = from.getNeighbors().get(0);
        for(Territory neighbour: from.getNeighbors()){
            if(neighbour.getArmySize() < to.getArmySize())
                to = neighbour;
        }
        //attack with 3 dice for attacker and two for
        declareAttack(from, to, 3, 2);
    }
}
