package Backend.AIAgents;

import Backend.models.Agent;
import Backend.models.Territory;

import java.util.ArrayList;

public class GreedyAgent implements Agent {
    private ArrayList<Territory> territories;
    private int cost;


    @Override
    public void addArmies() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void addArmies(Territory territory, int armiesCount) {

    }

    @Override
    public void attack(Territory from, Territory to, int armiesCount) {

    }

    @Override
    public void addTerritory(Territory territory) {

    }

    @Override
    public ArrayList<Territory> getOwnedTerritories() {
        return null;
    }
    // need to be edited
    private int calculateHeuristicCost(){
        return 0;
    }

}
