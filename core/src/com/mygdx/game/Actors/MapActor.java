package com.mygdx.game.Actors;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MapActor extends Group {
    private String type ;
    private TiledMap tiledMap;
    private OrthographicCamera tiledCamera;
    private OrthoCachedTiledMapRenderer tiledMapRenderer;
    Map<String,StateActor> map ;
    public MapActor(String filename, Stage theStage)
    {
// set up tile map, renderer, and camera
        tiledMap = new TmxMapLoader().load("maps/"+filename+"/tilemap.tmx");
        int tileWidth = (int)tiledMap.getProperties().get("tilewidth");
        int tileHeight = (int)tiledMap.getProperties().get("tileheight");
        int numTilesHorizontal = (int)tiledMap.getProperties().get("width");
        int numTilesVertical = (int)tiledMap.getProperties().get("height");
        int mapWidth = tileWidth * numTilesHorizontal;
        int mapHeight = tileHeight * numTilesVertical;

        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
        tiledMapRenderer.setBlending(true);
        tiledCamera = new OrthographicCamera();
        tiledCamera.setToOrtho(false, 1280, 720);
        tiledCamera.update();
        map = new HashMap<>();
        List<MapObject> list = getTileList();

        for(MapObject o : list){

            StateActor stateActor = new StateActor(((TiledMapTileMapObject) o).getX(),((TiledMapTileMapObject) o).getY(),theStage,filename,o.getName());
            map.put(o.getName(),stateActor);
            this.addActor(stateActor);

        }
        for(MapObject o : list){
            StateActor stateActor = map.get(o.getName());

            StateArmyActor stateArmyActor = new StateArmyActor(((TiledMapTileMapObject) o).getX(),((TiledMapTileMapObject) o).getY(),theStage,stateActor);
            this.addActor(stateArmyActor);

        }
        theStage.addActor(this);
    }
    public void act(float dt)
    {
        super.act( dt );
    }

    public ArrayList<MapObject> getTileList()
    {
        ArrayList<MapObject> list = new ArrayList<MapObject>();
        for ( MapLayer layer : tiledMap.getLayers() )
        {
            for ( MapObject obj : layer.getObjects() )
            {
                if ( !(obj instanceof TiledMapTileMapObject) )
                    continue;
                /*System.out.println(obj.getName());
                System.out.println(((TiledMapTileMapObject) obj).getX()+" "+((TiledMapTileMapObject) obj).getY());*/
                if(obj.getName()!=null)
                    list.add(obj);
            }
        }
        return list;
    }
}
