package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
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
    private Image background;
    private Label player;
    private Label playerType;
    private int player_mode ;
    private Label bonusText;
    private GameScreen gameScreen;
    public PlayerBarActor(Stage s, int p, String type, GameScreen gameScreen) {
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
            end = new TextButton("end",textButtonStyle);
            Table table1 = new Table();
            table1.padLeft(500);
            table1.add(attack);
            table1.add(end);
            group.addActor(table1);
            group.addActor(bonusText);


            table.add(group);

        }
        this.addActor(table);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(player_mode!=gameScreen.mode){
            player_mode=gameScreen.mode;
            if(player_mode==1){
                attack.setVisible(false);
                end.setVisible(false);
                bonusText.setVisible(true);
            }
            else{
                attack.setVisible(true);
                end.setVisible(true);
                bonusText.setVisible(false);
            }
        }

    }
    public void setBonus(int bonus){
        bonusText.setText("Bonus : "+bonus);
    }
}
