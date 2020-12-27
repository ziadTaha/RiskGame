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
import com.mygdx.game.GameScreen;

import Backend.models.Territory;

public class StateArmyActor extends BaseActor{
    private TextButton army_count;
    private int count ;
    private StateActor stateActor;
    private int mode ;
    private Territory territory;
    private GameScreen gameScreen;

    public StateArmyActor(float x, float y, Stage s, final StateActor stateActor, final Territory territory, final GameScreen gameScreen) {
        super(s);
        this.stateActor = stateActor;
        this.territory =territory;
        this.gameScreen = gameScreen;
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fonts/armies.fnt"));
        final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font=myFont;
        final Texture buttonTex = new Texture( Gdx.files.internal("ui_elements/army_number.png") );
         NinePatch buttonPatch = new NinePatch(buttonTex, 0,0,0,0);
        textButtonStyle.up = new NinePatchDrawable( buttonPatch );
        count=territory.getArmySize();
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
                if(gameScreen.mode==1){
                    if(territory.getAgent()!=null&&gameScreen.getCur()==territory.getAgent().getAgentID()){
                        territory.setArmySize(territory.getArmySize()+1);
                        gameScreen.setBonus(gameScreen.getBonus()-1);
                    }

                    return false;
                }
                if(gameScreen.getTerritory1()==null){
                    army_count.setStyle(getStyle(gameScreen.getCur()));
                    gameScreen.setTerritory1(territory);
                }
                else{
                    if(!territory.getNeighbors().contains(gameScreen.getTerritory1())){
                        gameScreen.getStateArmyActorMap().get(gameScreen.getTerritory1().getId()).setStyle(getStyle(0));
                        army_count.setStyle(getStyle(gameScreen.getCur()));
                        gameScreen.setTerritory1(territory);
                    }

                }
                
                return false;
            }
        });

        this.addActor(army_count);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(territory.getAgent()!= null){
            System.out.println(territory.getAgent().getAgentID()+" ");
            stateActor.changeColor(territory.getAgent().getAgentID());
        }
        army_count.setText(String.valueOf(territory.getArmySize()));

    }

    public void setMode(int mode) {
        this.mode = mode;
    }
     public TextButton.TextButtonStyle getStyle(int c){
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font=new BitmapFont(Gdx.files.internal("fonts/armies.fnt"));;
        if(c==1){
            Texture buttonTex = new Texture( Gdx.files.internal("ui_elements/army_number1.png") );
            NinePatch buttonPatch = new NinePatch(buttonTex, 0,0,0,0);
            textButtonStyle.up = new NinePatchDrawable( buttonPatch );
        }
        else if(c==2){
            Texture buttonTex = new Texture( Gdx.files.internal("ui_elements/army_number2.png") );
            NinePatch buttonPatch = new NinePatch(buttonTex, 0,0,0,0);
            textButtonStyle.up = new NinePatchDrawable( buttonPatch );
        }
        else{
            Texture buttonTex = new Texture( Gdx.files.internal("ui_elements/army_number.png") );
            NinePatch buttonPatch = new NinePatch(buttonTex, 0,0,0,0);
            textButtonStyle.up = new NinePatchDrawable( buttonPatch );
        }
        return textButtonStyle;
    }
    public void setStyle(TextButton.TextButtonStyle style){
        army_count.setStyle(style);
    }
}
