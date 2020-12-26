package Backend.nonAIAgents;
import Backend.models.Agent;
import Backend.models.Territory;
import java.util.ArrayList;


public class HumanAgent extends Agent {
    private ArrayList<Territory> territories;

    public HumanAgent() {
        super();
    }

    public void addArmies(Territory territory, int armiesCount) {
        //check territory whether to be owned or not
        if (territory.getAgent() != this) {
            throw new Error("Territory is not owned");
        }

        territory.setArmySize(territory.getArmySize() + armiesCount);
    }

    /** attacker dice count is 1, 2 or 3 & defender dice count is 1 or 2 **/

    public void attack(Territory from, Territory to, int attackDiceCount, int defendDiceCount) {
        //check adjacency of two territories
        boolean isAdjacent = false;
        for(Territory neighbour: from.getNeighbors()){
            if(neighbour == to){
                isAdjacent = true;
                break;
            }
        }
        if(!isAdjacent)
            throw new Error("attacks is only allowed to adjacent territories");

        //check the validation of the attack
        if (from.getAgent() != this)
            throw new Error("Territory is not owned");
        else if (to.getAgent() == this)
            throw new Error("Cannot attack owned Territory");

        //check armies in attacking territory
        if(from.getArmySize() == 1 )
            throw new Error("Territory must have more than 1 army to Declare attack");

        // attack
        this.declareAttack(from, to, attackDiceCount, defendDiceCount);
    }
}
