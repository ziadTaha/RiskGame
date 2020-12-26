package Backend.models;

import java.util.ArrayList;

public class Territory {

    private int id;
    private Agent agent;
    private int armySize = 0;
    private ArrayList<Territory> neighbors;
    private int bonusPercent;

    public Territory(int id) {
        neighbors = new ArrayList<>();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public int getArmySize() {
        return armySize;
    }

    public void setArmySize(int armySize) {
        this.armySize = armySize;
    }

    public ArrayList<Territory> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<Territory> neighbors) {
        this.neighbors = neighbors;
    }

    public int getBonusPercent() {
        return bonusPercent;
    }

    public void setBonusPercent(int bonusPercent) {
        this.bonusPercent = bonusPercent;
    }

    public Territory getEmptyClone() {
        Territory clone = new Territory(this.id);
        clone.setArmySize(this.armySize);
        return clone;
    }
}
