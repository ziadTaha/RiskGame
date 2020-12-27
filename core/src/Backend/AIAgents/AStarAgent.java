package Backend.AIAgents;
import Backend.GameManager;
import Backend.models.Agent;
import Backend.models.State;
import Backend.models.Territory;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class AStarAgent extends Agent {
    public AStarAgent() {
        super();
    }

    class StateComparator implements Comparator<State> {
        public int compare(State s1, State s2) {
            if (s1.getG_n() + s1.getH_n() < s2.getG_n() + s2.getH_n())
                return 1;
            else if (s1.getG_n() + s1.getH_n() > s2.getG_n() + s2.getH_n())
                return -1;
            return 0;
        }
    }

    @Override
    public void attack() {
        PriorityQueue<State> pq = new PriorityQueue<>(9999, new StateComparator());
        Agent enemy;
        if (getAgentID() == 1) {
            enemy = GameManager.getInstance().getPlayer2();
        } else {
            enemy = GameManager.getInstance().getPlayer1();
        }
        State currentState = new State(this, enemy, GameManager.getInstance().getGameMap());

        currentState.setH_n(attackHeuristic(getTerritories().size(), enemy.getTerritories().size(),
                getTotalArmiesOwned(), enemy.getTotalArmiesOwned()));
        currentState.setG_n(0); // no g_n for start
        pq.add(currentState);
        double currentF_n = currentState.getG_n() + currentState.getH_n();
        while ((!pq.isEmpty())) {
            currentState = pq.poll();

            double nextStateF_n = currentState.getH_n() + currentState.getG_n();

            /******** termination condition ********/
            if (currentF_n < nextStateF_n) {
                GameManager.getInstance().setGameMap(currentState.getGameMap());
                if (currentState.getCurrentPlayer().getAgentID() == 1) {
                    GameManager.getInstance().setPlayer1(currentState.getCurrentPlayer());
                    GameManager.getInstance().setPlayer2(currentState.getOtherPlayer());
                } else {
                    GameManager.getInstance().setPlayer1(currentState.getOtherPlayer());
                    GameManager.getInstance().setPlayer2(currentState.getCurrentPlayer());
                }
                return;
            }
            currentF_n = nextStateF_n;
            for (Territory territory : currentState.getCurrentPlayer().getTerritories()) {

                for (Territory neighbour : territory.getNeighbors()) {
                    if (neighbour.getAgent() != currentState.getCurrentPlayer() && territory.getArmySize() - 1 > neighbour.getArmySize() &&
                            territory.getArmySize() > 1) {
                        State newState = GameManager.getInstance().getStateClone(currentState.getCurrentPlayer(),
                                currentState.getOtherPlayer(), currentState.getGameMap());
                        double G_n = newState.getCurrentPlayer().attackCost(newState.getGameMap().get(territory.getId()).getArmySize(),
                                newState.getGameMap().get(neighbour.getId()).getArmySize());
                        newState.getCurrentPlayer().declareAttack(newState.getGameMap().get(territory.getId()),
                                newState.getGameMap().get(neighbour.getId()), 3, 2);
                        double H_n = newState.getCurrentPlayer().attackHeuristic(newState.getCurrentPlayer().getTerritories().size(),
                                newState.getOtherPlayer().getTerritories().size(),
                                newState.getCurrentPlayer().getTotalArmiesOwned(),
                                newState.getOtherPlayer().getTotalArmiesOwned());
                        newState.setH_n(H_n);
                        newState.setG_n(currentState.getG_n() + G_n);
                        pq.add(newState);
                    }
                }
            }
        }
    }
}
