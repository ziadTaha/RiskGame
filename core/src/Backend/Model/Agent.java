package Backend.Model;

import java.util.ArrayList;

public abstract class Agent {
    private int player;
    private int currentCost;
    private ArrayList<Territory> territories;

    public int calculateHeuristic() {
        return 0;
    }

    public void addArmy() {
    }
    public void attack() {
    }
    // for human agent
    public void humanAddArmy(Territory territory, int count) {
    }
    public void notifyAttack(Territory from, Territory to, int count) {
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(int currentCost) {
        this.currentCost = currentCost;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(ArrayList<Territory> territories) {
        this.territories = territories;
    }

}
