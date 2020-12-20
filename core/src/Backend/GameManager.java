package Backend;

import Backend.Model.Territory;

import java.util.Map;

public class GameManager {
    private static GameManager single_instance = null;
    private GameManager() {

    }
    public static GameManager getInstance() {
        if (single_instance == null)
            single_instance = new GameManager();
        return single_instance;
    }

    Map<Integer, Territory> map;

    // TODO fill map with 50 or 26 territory depending on the map selected & ids specified from the GUI


}
