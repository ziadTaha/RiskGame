package Backend;

import Backend.models.Territory;

import java.util.Map;

public class GameManager {
    private static GameManager single_instance = null;
    private Map<String, Territory> gameMap;
    private GameManager() {
    }
    public static GameManager getInstance() {
        if (single_instance == null)
            single_instance = new GameManager();
        return single_instance;
    }

    // initialization of the map  from external text file according to the country chosen
    public void setMapType(String countryChoosed)
    {
        if(countryChoosed.equals("Egypt")){

        }else if(countryChoosed.equals("USA")){

        }else{
            //error
        }
    }


    // TODO fill map with 50 or 26 territory depending on the map selected & ids specified from the GUI

}
