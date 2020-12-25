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

public class GameScreen extends BaseScreen{
    private String map ;
    private String p1 ;
    private  String p2;
    int cur ;
    private PlayerBarActor p1Bar;
    private PlayerBarActor p2Bar;
    private MapActor mapActor;
    public GameScreen(Game game,String map,String p1,String p2) {
        super(game);
        System.out.println("ss"+map);
        this.map=map;
        this.p1=p1;
        this.p2=p2;
        initialize();
    }
    @Override
    public void initialize() {
        Table table = new Table();
        table.align(Align.top);
        Image image;

        mapActor = new MapActor(map,mainStage);


        mainStage.addActor(mapActor);

        Group group = new Group();
        p1Bar = new PlayerBarActor(mainStage,1,p1);
        p2Bar = new PlayerBarActor(mainStage,2,p2);


        group.addActor(p1Bar);
        group.addActor(p2Bar);
        p2Bar.setVisible(false);
        cur = 1;
        table.align(Align.left);
        table.add(group).align(Align.bottomLeft).expandY();
        table.setFillParent(true);
        mainStage.addActor(table);
    }

    @Override
    public void update(float dt) {
        if(cur==1){
            p2Bar.setVisible(false);
            p1Bar.setVisible(true);
        }
        else{
            p1Bar.setVisible(false);
            p2Bar.setVisible(true);
        }
    }
}
