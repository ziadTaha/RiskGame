package Backend.nonAIAgents;

import Backend.models.Agent;
import Backend.models.Territory;

public class PassiveAgent extends Agent {
    public PassiveAgent(){
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
    }
}
