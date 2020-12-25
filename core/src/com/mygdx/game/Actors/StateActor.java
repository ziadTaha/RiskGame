package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class StateActor extends BaseActor {
    private Image image;
    private Label army_count;
    private int count ;
    public StateActor(float x, float y,float height,float width, Stage s,String mapName,String stateNo) {
        super(x, y, s);
        image = new Image(new Texture(Gdx.files.internal("maps/"+mapName+"/"+stateNo+".png")));
        count =0 ;
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fonts/armies.fnt"));
        label1Style.font = myFont;
        army_count= new Label(String.valueOf(count),label1Style);
        army_count.setPosition(x+height/2,y+width/2);

    }

}
