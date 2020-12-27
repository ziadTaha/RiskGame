package Backend.AIAgents;

import Backend.GameManager;
import Backend.models.Agent;
import Backend.models.Territory;

import java.util.ArrayList;

public class GreedyAgent extends Agent {

    public GreedyAgent() {
        super();
    }


    @Override
    public void attack() {
        Agent enemy;
        if (getAgentID() == 1) {
            enemy = GameManager.getInstance().getPlayer2();
        } else {
            enemy = GameManager.getInstance().getPlayer1();
        }

        int enemyArmiesSize = enemy.getTotalArmiesOwned(); // need to find it
        int enemyTerritoriesSize = enemy.getTerritories().size(); // need to find it
        int ownedTerritoriesSize = getTerritories().size();
        int ownedArmiesSize = getTotalArmiesOwned();

        double currentHeuristic = attackHeuristic(ownedTerritoriesSize,
                enemyTerritoriesSize, ownedArmiesSize, enemyArmiesSize);

        Territory bestTo = null;
        Territory bestFrom = null;
        double bestMoveHeuristic = 9999;
        for (Territory territory : getTerritories()) {
            if(territory.getArmySize() == 1){
                continue;
            }
            for (Territory neighbour : territory.getNeighbors()) {
                if (neighbour.getAgent() != this && territory.getArmySize() - 1 > neighbour.getArmySize()) {
                    if (neighbour.getAgent() != null) {
                        int newStateEnemyTerr, newStateEnemyArmies, newStateOwnedTerr, newStateOwnedArmies;
                        newStateOwnedTerr = ownedTerritoriesSize + 1;
                        newStateEnemyTerr = enemyTerritoriesSize - 1;
                        newStateOwnedArmies = ownedArmiesSize - neighbour.getArmySize();
                        newStateEnemyArmies = enemyArmiesSize - neighbour.getArmySize();
                        double stateHeuristic = attackHeuristic(newStateOwnedTerr,
                                newStateEnemyTerr, newStateOwnedArmies, newStateEnemyArmies);
                        if (stateHeuristic < bestMoveHeuristic) {
                            bestMoveHeuristic = stateHeuristic;
                            bestFrom = territory;
                            bestTo = neighbour;
                        }
                    } else {
                        int newStateEnemyTerr, newStateEnemyArmies, newStateOwnedTerr, newStateOwnedArmies;
                        newStateOwnedTerr = ownedTerritoriesSize + 1;
                        newStateEnemyTerr = enemyTerritoriesSize;
                        newStateOwnedArmies = ownedArmiesSize;
                        newStateEnemyArmies = enemyArmiesSize;
                        double stateHeuristic = attackHeuristic(newStateOwnedTerr,
                                newStateEnemyTerr, newStateOwnedArmies, newStateEnemyArmies);
                        if (stateHeuristic < bestMoveHeuristic) {
                            bestMoveHeuristic = stateHeuristic;
                            bestFrom = territory;
                            bestTo = neighbour;
                        }
                    }
                }
            }
        }
        // declare attack
        if (bestMoveHeuristic < currentHeuristic) {
            declareAttack(bestFrom, bestTo, 3, 2);
        }
        // else there will be no attack
    }
}
