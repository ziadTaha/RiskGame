package Backend;

import Backend.models.Agent;
import Backend.models.Territory;
import Backend.nonAIAgents.AggressiveAgent;
import Backend.nonAIAgents.HumanAgent;
import Backend.nonAIAgents.PacifistAgent;
import Backend.nonAIAgents.PassiveAgent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class GameManager {
    private static GameManager single_instance = null;
    private Map<Integer, Territory> gameMap;
    private Agent player1;
    private Agent player2;

    private GameManager() {
    }
    public static GameManager getInstance() {
        if (single_instance == null)
            single_instance = new GameManager();
        return single_instance;
    }

    // initialization of the map  from external text file according to the country chosen
    public void setMapType(String country) {
        int n = 0;
        String fileName = country + ".txt";
        if(country.equals("egypt")){ // 27 territory
            n = 27;
        }else if(country.equals("usa")){
            n = 50;
        }else{
            //error
        }
        for (int i = 1; i <= n; i++) {
            gameMap.put(i, new Territory());
        }
        fillMap(fileName);
    }

    private void fillMap(String fileName) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line=br.readLine()) != null) {
                String[] neighbours = line.split("\\s+");
                Territory t = gameMap.get(Integer.parseInt(neighbours[0]));
                for (int i = 1; i < neighbours.length; i++) {
                    Territory t2 = gameMap.get(Integer.parseInt(neighbours[i]));
                    t.getNeighbors().add(t2);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerType(String type, int playerNo) {
        if (playerNo == 1) {
            player1 = initiateAgent(type);
        } else {
            player2 = initiateAgent(type);
        }
    }

    private Agent initiateAgent(String type) {
        switch (type) {
            case "AI1":
                return null;    // TODO return greedy agent
            case "AI2":
                return null;    // TODO return A* agent
            case "AI3":
                return null;    // TODO return A* realtime agent
            case "AI4":
                return null;    // TODO return minMax agent
            case "Human":
                return new HumanAgent();
            case "Passive":
                return new PassiveAgent();
            case "Aggressive":
                return new AggressiveAgent();
            case "Pacifist":
                return new PacifistAgent();
            default:
                return null;
        }
    }

    public Map<Integer, Territory> getGameMap() {
        return gameMap;
    }

    public void setGameMap(Map<Integer, Territory> gameMap) {
        this.gameMap = gameMap;
    }

    public Agent getPlayer1() {
        return player1;
    }

    public void setPlayer1(Agent player1) {
        this.player1 = player1;
    }

    public Agent getPlayer2() {
        return player2;
    }

    public void setPlayer2(Agent player2) {
        this.player2 = player2;
    }
}
