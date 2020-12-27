package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class StateActor extends BaseActor {
    private Image image;
    private TextButton army_count;


    private int count ;
    private float height;
    private float width;
    public StateActor(float x, float y, Stage s,String mapName,String stateNo) {
        super(s);
        image = new Image(new Texture(Gdx.files.internal("maps/"+mapName+"/"+stateNo+".png")));

        this.height = image.getHeight();
        this.width =image.getWidth();
        count =Integer.valueOf(stateNo);
        image.setPosition(x,y+120);
        this.addActor(image);


    }
    public void changeColor(int col){
        if(col==1){
            image.setColor(Color.RED);
        }
        else if (col==2){
            image.setColor(Color.BLUE);
        }
        else{
            image.setColor(Color.WHITE);
        }
    }
    public int getCount() {
        return count;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getWidth() {
        return width;
    }
}
