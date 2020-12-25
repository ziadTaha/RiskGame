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

public class PlayerBarActor extends BaseActor {
    private int p ;
    private String type;
    private TextButton attack;
    private TextButton move;
    private Image background;
    private Label player;
    private Label playerType;

    public PlayerBarActor( Stage s,int p,String type) {
        super(s);
        this.p=p;
        this.type=type;
        if(p == 1){
            background = new Image(new Texture(Gdx.files.internal("ui_elements/player_bar1.png")));
            background.setWidth(1024);
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
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        Texture buttonTex = new Texture( Gdx.files.internal("ui_elements/button_purble.png") );
        NinePatch buttonPatch = new NinePatch(buttonTex, 24,24,24,24);
        textButtonStyle.up = new NinePatchDrawable( buttonPatch );
        BitmapFont customFont = new BitmapFont( Gdx.files.internal("fonts/arial.fnt") );
        textButtonStyle.font = customFont;
        textButtonStyle.fontColor = Color.WHITE;
        attack = new TextButton("attack",textButtonStyle);
        move = new TextButton("attack",textButtonStyle);
        Group group = new Group();

        Table table = new Table();
        table.setBackground(background.getDrawable());
        table.setHeight(200);
        table.setWidth(1280);

        //table.align(Align.left);
        table.add(player).align((Align.topLeft));
        table.row();
        table.add(playerType);
        table.add(attack);
        table.add(move);



        this.addActor(table);
    }
}
