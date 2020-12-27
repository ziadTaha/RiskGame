package Backend;

import Backend.models.Agent;
import Backend.models.State;
import Backend.models.Territory;

import java.util.Map;


public class Simulation {
    public static void main(String[] args) {

        GameManager gameManager = GameManager.getInstance();
        gameManager.setMapType("egypt");
        gameManager.setPlayersType("Pacifist", "AI2");
        Agent player1 = gameManager.getPlayer1();
        Agent player2 = gameManager.getPlayer2();
        Map<Integer, Territory> gameMap = gameManager.getGameMap();
        System.out.println("map size = "+gameMap.size());

        State temp = GameManager.getInstance().getStateClone(player1, player2, gameMap);




////        // checking map construction
////        System.out.println("checking map construction");
////        Territory num1 = gameMap.get(1);
////        for (Territory neighbour: num1.getNeighbors()) {
////            System.out.println("neighbour id = "+neighbour.getId());
////        }
////        System.out.println("checking map construction done 100%");
//
//        // checking players territories
//        System.out.println("checking players territories");
//        System.out.println("player1 territories size = "+ player1.getTerritories().size());
//        System.out.println("player1 army size = "+ player1.getTotalArmiesOwned());
////        for (Territory terr: player1.getTerritories()) {
////            System.out.println("player1 territory id = "+ terr.getId()+" has armies = "+terr.getArmySize());
////        }
//
////        System.out.println("player2 territories size = "+ player2.getTerritories().size());
////        System.out.println("player2 army size = "+ player2.getTotalArmiesOwned());
////        for (Territory terr: player2.getTerritories()) {
////            System.out.println("player2 territory id = "+ terr.getId()+" has armies = "+terr.getArmySize());
////        }
//
//        System.out.println("after adding bonus");
//        player1.addArmies();
//        System.out.println("player1 army size = "+ player1.getTotalArmiesOwned());
////        for (Territory terr: player1.getTerritories()) {
////            System.out.println("player1 territory id = "+ terr.getId()+" has armies = "+terr.getArmySize());
////        }
//
//        System.out.println("after attacking (passive don't attack)");
//        player1.attack();
//        System.out.println("player1 army size = "+ player1.getTotalArmiesOwned());
//        System.out.println("player1 territories size = "+ player1.getTerritories().size());
//
//        System.out.println("=================================== player 2 turn =======================================");
//        System.out.println("player2 territories size = "+ player2.getTerritories().size());
//        System.out.println("player2 army size = "+ player2.getTotalArmiesOwned());
//        for (Territory terr: player2.getTerritories()) {
//            System.out.println("player2 territory id = "+ terr.getId()+" has armies = "+terr.getArmySize());
//        }
//        System.out.println("after adding bonus");
//        player2.addArmies();
//        System.out.println("player2 army size = "+ player2.getTotalArmiesOwned());
//        for (Territory terr: player2.getTerritories()) {
//            System.out.println("player2 territory id = "+ terr.getId()+" has armies = "+terr.getArmySize());
//        }
//
//        System.out.println("after attacking");
//        player2.attack();
//        System.out.println("player2 army size = "+ player2.getTotalArmiesOwned());
//        System.out.println("player2 territories size = "+ player2.getTerritories().size());
//        for (Territory terr: player2.getTerritories()) {
//            System.out.println("player2 territory id = "+ terr.getId()+" has armies = "+terr.getArmySize());
//        }
        int counter = 0;
        while ((player1.getTotalArmiesOwned() != 0 || player2.getTotalArmiesOwned() != 0 ) && counter++ < 10) {
            System.out.print("player1 territories: ");
            for (Territory terr: player1.getTerritories()) {
                System.out.print(terr.getId() + " "+"has armies = "+terr.getArmySize()+ "    ");
                //System.out.print(terr.getId() + " ");
            }
            System.out.println();
            System.out.print("player2 territories: ");
            for (Territory terr: player2.getTerritories()) {
                System.out.print(terr.getId() + " "+"has armies = "+terr.getArmySize() + "    ");
                //System.out.print(terr.getId() + " ");

            }
            System.out.println();
            System.out.println("--------------------------------------------");
            player1.addArmies();
            player1.attack();
            player2.addArmies();
            player2.attack();
        }
        System.out.println(counter);
        System.out.println("player1 army size = "+ player1.getTotalArmiesOwned());
        System.out.println("player1 territories size = "+ player1.getTerritories().size());
        System.out.println("player2 army size = "+ player2.getTotalArmiesOwned());
        System.out.println("player2 territories size = "+ player2.getTerritories().size());

    }
}
