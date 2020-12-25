package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MapChooserActor extends BaseActor{
    private boolean map ;
    private Button leftArrow,rightArrow;
    private Label mapTypes;
    private Image imageUSA;
    private Image imageEgypt;
    public MapChooserActor(float x, float y, Stage s) {
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
                change();
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

                change();
                return false;
            }
        });
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fonts/golden.fnt"));
        label1Style.font = myFont;
        mapTypes = new Label("USA",label1Style);
        imageUSA = new Image(new Texture(Gdx.files.internal("maps/usa1.png")));
        imageEgypt = new Image(new Texture(Gdx.files.internal("maps/egypt1.png")));
        Table table = new Table();
        Group group = new Group();
        imageEgypt.setVisible(false);
        imageUSA.setHeight(90);
        imageUSA.setWidth(160);
        imageEgypt.setHeight(90);
        imageEgypt.setWidth(160);
        group.addActor(imageUSA);
        group.addActor(imageEgypt);
        table.add(group).colspan(2).center();
        table.row();
        table.add(leftArrow);
        table.add(mapTypes);
        table.add(rightArrow);
        this.addActor(table);
    }
    public void change(){
        map =!map;
        System.out.println(map);
        if(!map) {
            mapTypes.setText("USA");
            imageEgypt.setVisible(false);
            imageUSA.setVisible(true);
        }
        else {
            mapTypes.setText("EGYPT");
            imageEgypt.setVisible(true);
            imageUSA.setVisible(false);
        }

    }
    public String value(){
        if(!map) return "USA";
        return "EGYPT";
    }
}
