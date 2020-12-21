package Backend.models;

import java.util.ArrayList;

public  interface Agent {

     void addArmies();
     void attack();
    // human case
     void addArmies(Territory territory, int armiesCount);
     void attack(Backend.models.Territory from, Territory to, int armiesCount);
     void addTerritory(Territory territory);
     ArrayList<Territory> getOwnedTerritories();


}
