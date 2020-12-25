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
        /*Group group = new Group();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fonts/armies.fnt"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font=myFont;
        Texture buttonTex = new Texture( Gdx.files.internal("ui_elements/army_number.png") );
        NinePatch buttonPatch = new NinePatch(buttonTex, 0,0,0,0);
        textButtonStyle.up = new NinePatchDrawable( buttonPatch );
        army_count= new TextButton(String.valueOf(count),textButtonStyle);
        army_count.setPosition(width/2+x,120+y+height/2);*/
        image.setPosition(x,y+120);
        /*group.addActor(image);
        group.addActor(army_count);*/
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
