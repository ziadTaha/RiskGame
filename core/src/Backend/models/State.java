package Backend.models;

import java.util.Map;

public class State {
    private Agent currentPlayer;    // indicate state current turn player
    private Agent otherPlayer;
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

    public Agent getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Agent currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Agent getOtherPlayer() {
        return otherPlayer;
    }

    public void setOtherPlayer(Agent otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public Map<Integer, Territory> getGameMap() {
        return gameMap;
    }

    public void setGameMap(Map<Integer, Territory> gameMap) {
        this.gameMap = gameMap;
    }


    public State(Agent currentPlayer, Agent otherPlayer, Map<Integer,Territory> gameMap){
        this.currentPlayer = currentPlayer;
        this.otherPlayer = otherPlayer;
        this.gameMap = gameMap;
    }


}
