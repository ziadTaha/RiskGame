package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameScreen extends BaseScreen{
    public GameScreen(Game game) {
        super(game);
    }
    @Override
    public void initialize() {
        Table table = new Table();
        Image image = new Image(new Texture(Gdx.files.internal("maps/usa1.png")));

        table.add(image);
        table.setFillParent(true);
        mainStage.addActor(table);
    }

    @Override
    public void update(float dt) {

    }
}
