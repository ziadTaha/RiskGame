package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.GameScreen;

public class PlayerBarActor extends BaseActor {
    private int p ;
    private String type;
    private TextButton attack;
    private TextButton end;
    private TextButton fortify;
    private Image background;
    private Label player;
    private Label playerType;
    private int player_mode ;
    private Label bonusText;
    private GameScreen gameScreen;
    public PlayerBarActor(Stage s, int p, String type, final GameScreen gameScreen) {
        super(s);
        this.p=p;
        this.type=type;
        this.gameScreen =gameScreen;
        if(p == 1){
            background = new Image(new Texture(Gdx.files.internal("ui_elements/player_bar1.png")));

        }
        else
        {
            background = new Image(new Texture(Gdx.files.internal("ui_elements/player_bar2.png")));
        }
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fonts/golden.fnt"));
        label1Style.font = myFont;
        player= new Label("player"+p,label1Style);
        playerType = new Label(type,label1Style);
        bonusText = new Label("Bonus : "+0,label1Style);
        Group group = new Group();
        Table table = new Table();
        table.align(Align.left);
        table.padLeft(40);
        table.padRight(50);
        table.setBackground(background.getDrawable());
        table.setHeight(200);
        table.setWidth(1280);
        //table.align(Align.left);
        table.add(player).align(Align.left);
        table.row();
        table.add(playerType).padRight(100);
        player_mode=0;
        if(type.equals("Human")) {
            player_mode=1;
            TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            Texture buttonTex = new Texture( Gdx.files.internal("ui_elements/button_purble.png") );
            NinePatch buttonPatch = new NinePatch(buttonTex, 24,24,24,24);
            textButtonStyle.up = new NinePatchDrawable( buttonPatch );
            BitmapFont customFont = new BitmapFont( Gdx.files.internal("fonts/arial.fnt") );
            textButtonStyle.font = customFont;
            textButtonStyle.fontColor = Color.WHITE;
            attack = new TextButton("attack",textButtonStyle);
            attack.addListener(new EventListener() {
                @Override
                public boolean handle(Event event) {
                    if ( !(event instanceof InputEvent) ||
                            !((InputEvent)event).getType().equals(InputEvent.Type.touchDown) ){
                        return false;
                    }

                    if(gameScreen.getTerritory1()!=null&&gameScreen.getTerritory2()!=null){
                        try{
                            if(gameScreen.getCur()==1){

                                gameScreen.getAgent1().attack(gameScreen.getTerritory1(), gameScreen.getTerritory2(), 3,2);
                            }
                            else{
                                gameScreen.getAgent2().attack(gameScreen.getTerritory1(),gameScreen.getTerritory2(),3,2);
                            }
                            for(StateArmyActor s : gameScreen.getStateArmyActorMap().values()){
                                s.setStyle(s.getStyle(0));
                            }
                            gameScreen.setTerritory1(null);
                            gameScreen.setTerritory2(null);
                        } catch (Error e){
                            for(StateArmyActor s : gameScreen.getStateArmyActorMap().values()){
                                s.setStyle(s.getStyle(0));
                            }
                            gameScreen.setTerritory1(null);
                            gameScreen.setTerritory2(null);
                        }

                    }
                    return false;

                }
            });
            end = new TextButton("end",textButtonStyle);
            end.addListener(new EventListener() {
                @Override
                public boolean handle(Event event) {
                    if ( !(event instanceof InputEvent) ||
                            !((InputEvent)event).getType().equals(InputEvent.Type.touchDown) ){
                        return false;
                    }
                    gameScreen.mode=(gameScreen.mode+1)%4;
                    for(StateArmyActor s : gameScreen.getStateArmyActorMap().values()){
                        s.setStyle(s.getStyle(0));
                    }
                    if(gameScreen.mode==0){
                        if(gameScreen.getCur()==1){
                            gameScreen.setCur(2);
                        }
                        else{
                            gameScreen.setCur(1);
                        }
                    }

                    return false;
                }
            });
            fortify = new TextButton("fortify",textButtonStyle);
            fortify.addListener(new EventListener() {
                @Override
                public boolean handle(Event event) {
                    if ( !(event instanceof InputEvent) ||
                            !((InputEvent)event).getType().equals(InputEvent.Type.touchDown) ){
                        return false;
                    }

                    if(gameScreen.getTerritory1()!=null&&gameScreen.getTerritory2()!=null){
                        try{
                            if(gameScreen.getCur()==1){
                                gameScreen.getAgent1().moveArmies(gameScreen.getTerritory1(),gameScreen.getTerritory2(),1);//gameScreen.getAgent1().attack(gameScreen.getTerritory1(), gameScreen.getTerritory2(), 3,2);

                            }
                            else{
                                //gameScreen.getAgent2().attack(gameScreen.getTerritory1(),gameScreen.getTerritory2(),3,2);
                                gameScreen.getAgent2().moveArmies(gameScreen.getTerritory1(),gameScreen.getTerritory2(),1);

                            }
                        } catch (Error e){

                        }

                    }
                    return false;

                }
            });
            Table table1 = new Table();
            //table1.padLeft(500);
           /* Group group1 = new Group();
            group1.addActor(attack);
            group1.addActor(fortify);*/
            table1.add(attack);
            table1.add(fortify);
            table1.add(end);
            table1.align(Align.left);
            group.addActor(table1);
            group.addActor(bonusText);
            attack.setVisible(false);
            end.setVisible(false);
            fortify.setVisible(false);
            bonusText.setVisible(true);
            table.add(group);

        }
        this.addActor(table);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(player_mode!=gameScreen.mode){
            player_mode=gameScreen.mode;
            if(type.equals("Human")){
                System.out.println(player_mode+" "+type);
                if(player_mode==1){
                    attack.setVisible(false);
                    end.setVisible(false);
                    fortify.setVisible(false);
                    bonusText.setVisible(true);
                }
                else if(player_mode==2){
                    attack.setVisible(true);
                    end.setVisible(true);
                    fortify.setVisible(false);
                    bonusText.setVisible(false);
                }
                else{
                    attack.setVisible(false);
                    end.setVisible(true);
                    fortify.setVisible(true);
                    bonusText.setVisible(false);
                }
            }

        }
        else if(player_mode==1){
            bonusText.setText(gameScreen.getBonus());
        }

    }
    public void setBonus(int bonus){
        bonusText.setText("Bonus : "+bonus);
    }
}
