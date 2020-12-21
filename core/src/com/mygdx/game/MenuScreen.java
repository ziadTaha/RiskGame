package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Actors.MapChooserActor;
import com.mygdx.game.Actors.PlayerChooserActor;


public class MenuScreen extends BaseScreen{

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void initialize() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

		/*tiledMap = new TmxMapLoader().load("maps/map.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);*/
        //Gdx.input.setInputProcessor(this);
        Table table = new Table();
        TextButton.TextButtonStyle textButtonStyle = new TextButtonStyle();
        Texture buttonTex = new Texture( Gdx.files.internal("ui_elements/button_purble.png") );
        NinePatch buttonPatch = new NinePatch(buttonTex, 24,24,24,24);
        textButtonStyle.up = new NinePatchDrawable( buttonPatch );
        BitmapFont customFont = new BitmapFont( Gdx.files.internal("fonts/arial.fnt") );
        textButtonStyle.font = customFont;
        textButtonStyle.fontColor = Color.WHITE;
        TextButton startButton = new TextButton("Start",textButtonStyle);
        startButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(!(event instanceof InputEvent) ||
                        !((InputEvent)event).getType().equals(InputEvent.Type.touchDown)){
                    return false;
                }
                game.setScreen(new GameScreen(game));
                return false;
            }
        });
        TextButton exitButton = new TextButton("Exit",textButtonStyle);
        exitButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(!(event instanceof InputEvent) ||
                        !((InputEvent)event).getType().equals(InputEvent.Type.touchDown)){
                    return false;
                }
                Gdx.app.exit();
                return false;
            }
        });
        MapChooserActor mapChooser = new MapChooserActor(500,500,mainStage);
        PlayerChooserActor p1Chooser = new PlayerChooserActor(300,300,mainStage);
        PlayerChooserActor p2Chooser = new PlayerChooserActor(700,300,mainStage);
        table.add().expandY();
        table.row();
        table.add(startButton).bottom();
        table.row();
        table.add(exitButton);
        table.pad(10);
        table.setFillParent(true);
        mainStage.addActor(table);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void dispose() {
        super.dispose();
        mainStage.dispose();
    }
}
