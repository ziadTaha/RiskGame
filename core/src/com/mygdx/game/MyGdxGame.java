package com.mygdx.game;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MyGdxGame extends ApplicationAdapter {
	Texture img;
	TiledMap tiledMap;
	OrthographicCamera camera;
	/*TiledMapRenderer tiledMapRenderer;
	SpriteBatch sb;
	Texture texture;
	Sprite sprite;
	Table table;*/
	Stage stage ;
	
	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,w,h);
		camera.update();
		/*tiledMap = new TmxMapLoader().load("maps/map.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);*/
		//Gdx.input.setInputProcessor(this);
		stage = new Stage(new FillViewport(640,480,camera));
		Table table = new Table();
		Image image = new Image(new Texture(Gdx.files.internal("maps/usa1.png")));
		table.add(image);
		table.setFillParent(true);
		stage.addActor(table);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		/*tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();*/


		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
