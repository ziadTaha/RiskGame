package Backend;

import Backend.AIAgents.AStarAgent;
import Backend.AIAgents.GreedyAgent;
import Backend.AIAgents.MinMaxAgent;
import Backend.AIAgents.RealTimeAStarAgent;
import Backend.models.Agent;
import Backend.models.State;
import Backend.models.Territory;
import Backend.nonAIAgents.AggressiveAgent;
import Backend.nonAIAgents.HumanAgent;
import Backend.nonAIAgents.PacifistAgent;
import Backend.nonAIAgents.PassiveAgent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
        gameMap = new HashMap<>();
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
            gameMap.put(i, new Territory(i));
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
        armiesPlacement();
    }

    private Agent initiateAgent(String type) {
        switch (type) {
            case "AI1":
                return new GreedyAgent();
            case "AI2":
                return new AStarAgent();
            case "AI3":
                return new RealTimeAStarAgent();
            case "AI4":
                return new MinMaxAgent();
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

    private void armiesPlacement() {
        int n1 = 20, n2 = 20;
        int n = gameMap.size();
        int terrLeft = n;
        Random rand = new Random();
        while (n1 != 0) {
            int randomInt = rand.nextInt(n) + 1;
            while (gameMap.get(randomInt).getAgent() != null) {
                randomInt = rand.nextInt(n) + 1;
            }
            Territory territory = gameMap.get(randomInt);
            territory.setAgent(player1);
            player1.addTerritory(territory);
            int randomSize = rand.nextInt(n1) + 1;
            territory.setArmySize(randomSize);
            n1 -= randomSize;
            terrLeft--;
        }
        while (n2 != 0) {
            if (terrLeft == 0)
                break;
            int randomInt = rand.nextInt(n) + 1;
            while (gameMap.get(randomInt).getAgent() != null) {
                randomInt = rand.nextInt(n) + 1;
            }
            Territory territory = gameMap.get(randomInt);
            territory.setAgent(player2);
            player2.addTerritory(territory);
            int randomSize = rand.nextInt(n2) + 1;
            territory.setArmySize(randomSize);
            n2 -= randomSize;
            terrLeft--;
        }
        if (terrLeft == 0 && n2 > 0) {
            if (n2 > 0) {
                Territory firstTerr = player2.getTerritories().get(0);
                firstTerr.setArmySize(firstTerr.getArmySize() + n2);
                n2 = 0;
            }
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

    public State getStateClone(Agent player, Map<Integer, Territory> map) {
        Map<Integer, Territory> cloneMap = new HashMap<>();
        Agent playerClone = player.getEmptyClone();

        // creating clone map carrying new territories with their agents (without neighbours)
        for (Map.Entry<Integer, Territory> entry: map.entrySet()) {
            Territory territory = entry.getValue();
            Territory terClone = territory.getEmptyClone();
            if (territory.getAgent().getAgentID() == player.getAgentID()) {
                terClone.setAgent(playerClone);
                playerClone.addTerritory(terClone);
            }
            cloneMap.put(entry.getKey(), terClone);
        }

        // add neighbours (have nothing to do with states(same in all cases))
        for (Map.Entry<Integer, Territory> entry: cloneMap.entrySet()) {
            Territory curClone = entry.getValue();
            Territory originalTerr = map.get(entry.getKey());
            ArrayList<Territory> cloneNeighbors = new ArrayList<>();
            for (Territory neighbour : originalTerr.getNeighbors()) {
                int neighbourId = neighbour.getId();
                cloneNeighbors.add(cloneMap.get(neighbourId));
            }
            curClone.setNeighbors(cloneNeighbors);
        }

        return new State(playerClone, cloneMap);
    }

}
