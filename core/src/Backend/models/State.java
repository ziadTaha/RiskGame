package Backend.models;

import java.util.Map;

public class State {
    private Agent agent;
    private Map<Integer,Territory> gameMap;
    private int h_n;
    private int g_n;

    public State(Agent agent, Map<Integer,Territory> gameMap){
        this.agent = agent;
        this.gameMap = gameMap;
    }


}
