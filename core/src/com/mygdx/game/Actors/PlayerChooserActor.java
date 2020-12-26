package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayerChooserActor extends BaseActor{
    private Button leftArrow,rightArrow;
    private Label playerTypes;
    private int cur ;
    final String[] types={"AI1","AI2","AI3","AI4","Passive","Aggressive","Pacifist","Human"};
    public PlayerChooserActor(float x, float y, Stage s) {
        super(x, y, s);
        Button.ButtonStyle buttonStyleLeft = new Button.ButtonStyle();
        Texture buttonTexLeft = new Texture( Gdx.files.internal("ui_elements/leftArrow.png") );
        TextureRegion buttonRegionL = new TextureRegion(buttonTexLeft);
        buttonStyleLeft.up = new TextureRegionDrawable( buttonRegionL );
        leftArrow = new Button(buttonStyleLeft);
        leftArrow.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if ( !(event instanceof InputEvent) ||
                        !((InputEvent)event).getType().equals(InputEvent.Type.touchDown) ){
                    return false;
                }
                left();
                return false;
            }
        });
        Button.ButtonStyle buttonStyleRight = new Button.ButtonStyle();
        Texture buttonTexRight = new Texture( Gdx.files.internal("ui_elements/rightArrow.png") );
        TextureRegion buttonRegionR = new TextureRegion(buttonTexRight);
        buttonStyleRight.up = new TextureRegionDrawable( buttonRegionR );
        rightArrow = new Button(buttonStyleRight);
        rightArrow.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if ( !(event instanceof InputEvent) ||
                        !((InputEvent)event).getType().equals(InputEvent.Type.touchDown) ){
                    return false;
                }

                right();
                return false;
            }
        });
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fonts/golden.fnt"));
        label1Style.font = myFont;
        cur=0;
        playerTypes = new Label(types[0],label1Style);
        Table table = new Table();
        table.add(leftArrow);
        table.add(playerTypes);
        table.add(rightArrow);

        this.addActor(table);

    }
    public void left(){
        cur--;
        if(cur==-1) cur = 7;
        playerTypes.setText(types[cur]);
    }
    public void right(){
        cur++;
        cur%=8;
        playerTypes.setText(types[cur]);
    }
    public String value(){
        return types[cur];
    }
}
