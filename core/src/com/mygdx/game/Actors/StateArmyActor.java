package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class StateArmyActor extends BaseActor{
    private TextButton army_count;
    private int count ;
    private StateActor stateActor;
    private int mode ;
    public StateArmyActor(float x, float y, Stage s, final StateActor stateActor) {
        super(s);
        this.stateActor = stateActor;
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fonts/armies.fnt"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font=myFont;
        Texture buttonTex = new Texture( Gdx.files.internal("ui_elements/army_number.png") );
        NinePatch buttonPatch = new NinePatch(buttonTex, 0,0,0,0);
        textButtonStyle.up = new NinePatchDrawable( buttonPatch );
        count=stateActor.getCount();
        mode =0 ;
        army_count= new TextButton(String.valueOf(count),textButtonStyle);
        army_count.setPosition(stateActor.getWidth()/2+x,120+y+stateActor.getHeight()/2);
        army_count.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if ( !(event instanceof InputEvent) ||
                        !((InputEvent)event).getType().equals(InputEvent.Type.touchDown) ){
                    return false;
                }
                stateActor.changeColor(1);
                return false;
            }
        });

        this.addActor(army_count);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
