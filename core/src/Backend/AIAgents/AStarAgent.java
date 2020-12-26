package Backend.AIAgents;

import Backend.GameManager;
import Backend.models.Agent;
import Backend.models.State;
import Backend.models.Territory;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarAgent extends Agent {
    public AStarAgent(){
        super();
    }

    class StateComparator implements Comparator<State> {
        public int compare(State s1, State s2) {
            if (s1.getG_n() + s1.getH_n() < s2.getG_n() + s2.getH_n())
                return 1;
            return -1;
        }
    }
    @Override
    public void attack() {
        PriorityQueue<State> pq = new PriorityQueue<>(9999, new StateComparator());
        State currentState = new State(this, GameManager.getInstance().getGameMap());
        Agent enemy = null;
        if(getAgentID() == 1){
            enemy = GameManager.getInstance().getPlayer2();
        }else{
            GameManager.getInstance().getPlayer1();
        }

        currentState.setG_n(attackHeuristic(getTerritories().size(), enemy.getTerritories().size(),
                getTotalArmiesOwned(), enemy.getTotalArmiesOwned()));
        currentState.setH_n(0); // no g_n for start
        pq.add(currentState);
        while ((!pq.isEmpty())){
            currentState = pq.peek();
            for (Territory territory : getTerritories()) {
                for (Territory neighbour : territory.getNeighbors()) {
                    if (neighbour.getAgent() != this && territory.getArmySize() > neighbour.getArmySize()) {
                    }
                }
            }
        }
    }
}
