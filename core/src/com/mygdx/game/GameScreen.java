package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Actors.MapActor;
import com.mygdx.game.Actors.PlayerBarActor;
import com.mygdx.game.Actors.StateArmyActor;

import java.util.HashMap;
import java.util.Map;

import Backend.GameManager;
import Backend.models.Territory;
import Backend.models.Agent;

public class GameScreen extends BaseScreen{
    private String map ;
    private String p1 ;
    private  String p2;
    int cur ;
    int temp ;
    private Territory territory1;
    private Territory territory2;
    public int mode ;
    private Agent agent1 ;
    private Agent agent2;
    private PlayerBarActor p1Bar;
    private PlayerBarActor p2Bar;
    private MapActor mapActor;
    private GameManager gameManager;
    private boolean bonus_flag;
    private int bonus;
    Map<Integer ,Territory> territoryMap;
    Map<Integer, StateArmyActor> stateArmyActorMap;
    public GameScreen(Game game,String map,String p1,String p2) {
        super(game);
        this.map=map;
        this.p1=p1;
        this.p2=p2;
        mode = 1;
        temp = 1;
        gameManager=GameManager.getInstance();
        gameManager.setMapType(map.toLowerCase());
        territoryMap=gameManager.getGameMap();
        gameManager.setPlayersType(p1,p2);
        agent1=gameManager.getPlayer1();
        agent2=gameManager.getPlayer2();
        stateArmyActorMap = new HashMap<>();

        initialize();
    }
    @Override
    public void initialize() {
        Table table = new Table();
        table.align(Align.top);


        mapActor = new MapActor(map,mainStage,this);


        mainStage.addActor(mapActor);

        Group group = new Group();
        p1Bar = new PlayerBarActor(mainStage,1,p1,this);
        p2Bar = new PlayerBarActor(mainStage,2,p2,this);

        group.addActor(p1Bar);
        group.addActor(p2Bar);
        p2Bar.setVisible(false);
        cur = 1;
        table.align(Align.left);
        table.add(group).align(Align.bottomLeft).expandY();
        table.setFillParent(true);
        mainStage.addActor(table);
    }

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }

    @Override
    public void update(float dt) {
        if(cur==1){
            if(p1.equals("Human")){
                if(mode ==0){
                    mode=1;
                }
                else if(mode==1){
                    if (!bonus_flag){
                        bonus = agent1.calculateBonus();
                        bonus_flag =true;
                    }
                    else if(bonus==0){
                        mode =2;
                        bonus_flag=false;
                    }
                }
            }
            p2Bar.setVisible(false);
            p1Bar.setVisible(true);
        }
        else{
            if(p1.equals("Human")){
                if(mode ==0){
                    mode=1;
                }
                else if(mode==1){
                    if (!bonus_flag){
                        bonus = agent1.calculateBonus();
                        bonus_flag =true;
                    }
                    else if(bonus==0){
                        mode =2;
                        bonus_flag=false;
                    }
                }
            }
            p1Bar.setVisible(false);
            p2Bar.setVisible(true);
        }
    }
    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public Territory getTerritory1() {
        return territory1;
    }

    public void setTerritory1(Territory territory1) {
        this.territory1 = territory1;
    }

    public Territory getTerritory2() {
        return territory2;
    }

    public void setTerritory2(Territory territory2) {
        this.territory2 = territory2;
    }

    public Map<Integer, Territory> getTerritoryMap() {
        return territoryMap;
    }

    public Map<Integer, StateArmyActor> getStateArmyActorMap() {
        return stateArmyActorMap;
    }
}
