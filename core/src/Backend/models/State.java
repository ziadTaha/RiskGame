package Backend.models;

import java.util.Map;

public class State {
    private Agent agent;
    private Map<Integer,Territory> gameMap;
    private double h_n;
    private double g_n;
    public double getH_n() {
        return h_n;
    }

    public void setH_n(double h_n) {
        this.h_n = h_n;
    }

    public double getG_n() {
        return g_n;
    }

    public void setG_n(double g_n) {
        this.g_n = g_n;
    }


    public State(Agent agent, Map<Integer,Territory> gameMap){
        this.agent = agent;
        this.gameMap = gameMap;
    }


}
