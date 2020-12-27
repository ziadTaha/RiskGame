package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;



public class GameOverScreen extends BaseScreen{
    private Label gameOver;
    private Label message;
    private TextButton menueButton;
    private TextButton exitButton;
    private int p;
    public GameOverScreen(Game game,int p) {
        super(game);
        this.p=p;
        initialize();
    }

    @Override
    public void initialize() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        Texture buttonTex = new Texture( Gdx.files.internal("ui_elements/button_purble.png") );
        NinePatch buttonPatch = new NinePatch(buttonTex, 24,24,24,24);
        textButtonStyle.up = new NinePatchDrawable( buttonPatch );
        BitmapFont customFont = new BitmapFont( Gdx.files.internal("fonts/arial.fnt") );
        textButtonStyle.font = customFont;
        textButtonStyle.fontColor = Color.WHITE;
        com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle label1Style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fonts/game_over_font.fnt"));
        label1Style.font = myFont;
        menueButton = new TextButton("main menu",textButtonStyle);
        exitButton = new TextButton("exit",textButtonStyle);
        message = new Label("Player "+p+" wins ",label1Style);
        gameOver=new Label("Game Over",label1Style);
        menueButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if ( !(event instanceof InputEvent) ||
                        !((InputEvent)event).getType().equals(InputEvent.Type.touchDown) ){
                    return false;
                }
                game.setScreen(new MenuScreen(game));
                return false;
            }

        });
        exitButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if ( !(event instanceof InputEvent) ||
                        !((InputEvent)event).getType().equals(InputEvent.Type.touchDown) ){
                    return false;
                }
                Gdx.app.exit();
                return false;
            }

        });
        Table table = new Table();
        Group group = new Group();
        table.add(gameOver).colspan(2);
        table.row();
        table.add(message);
        table.row();
        table.add(menueButton);
        table.row();
        table.add(exitButton);
        table.setFillParent(true);
        mainStage.addActor(table);

    }

    @Override
    public void update(float dt) {

    }
}
