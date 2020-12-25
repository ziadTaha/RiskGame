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
        // adding bonus armies s bonus armies to the territory with the fewest armies
        int bonusArmies =  this.getTerritories().size() / 3;
        if(bonusArmies< 3)
            bonusArmies = 3;
        fewestArmiesTerritory.setArmySize(fewestArmiesTerritory.getArmySize() + bonusArmies);
    }
}
