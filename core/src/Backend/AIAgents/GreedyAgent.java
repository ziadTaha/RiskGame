package Backend.AIAgents;

import Backend.models.Agent;
import Backend.models.Territory;

public class GreedyAgent extends Agent {

    public GreedyAgent() {

        super();
    }

    @Override
    public void attack() {
        int enemyArmiesSize = 0; // need to find it
        int enemyTerritoriesSize = 0; // need to find it
        int ownedTerritoriesSize = getTerritories().size();
        int ownedArmiesSize = getTotalArmiesOwned();
        double currentHeuristic = calculateHeuristicCost(ownedTerritoriesSize,
                enemyTerritoriesSize, ownedArmiesSize, enemyArmiesSize);
        Territory bestTo = null;
        Territory bestFrom = null;
        double bestMoveHeuristic = -1;
        for (Territory territory : getTerritories()) {
            for(Territory neighbour : territory.getNeighbors()){
                if(neighbour.getAgent() != this ){
                    int newStateEnemyTerr = 0, newStateEnemyArmies = 0, newStateOwnedTerr = 0, newStateOwnedArmies = 0;
                    if(territory.getArmySize() - 1 > neighbour.getArmySize()){
                        newStateOwnedTerr = ownedTerritoriesSize + 1;
                        newStateEnemyTerr = enemyTerritoriesSize - 1;

                    }
                    newStateOwnedArmies = ownedArmiesSize - neighbour.getArmySize();
                    newStateEnemyArmies = enemyArmiesSize - neighbour.getArmySize();
                    double stateHeuristic = calculateHeuristicCost(newStateOwnedTerr,
                            newStateEnemyTerr, newStateOwnedArmies, newStateEnemyArmies);
                    if( stateHeuristic > bestMoveHeuristic){
                        bestMoveHeuristic = stateHeuristic;
                        bestFrom = territory;
                        bestTo = neighbour;
                    }
                }
            }
        }

        // declare attack
        if(bestMoveHeuristic > currentHeuristic){
            declareAttack(bestFrom, bestTo, 3, 2);
        }
        // else there will be no attack
    }
}
