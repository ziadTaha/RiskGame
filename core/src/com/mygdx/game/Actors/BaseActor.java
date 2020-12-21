package com.mygdx.game.Actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
/**
 * Extend functionality of the LibGDX Actor class.
 */
public class BaseActor extends Group
{
    public BaseActor(float x, float y, Stage s)
    {
// call constructor from Actor class
        super();
// perform additional initialization tasks
        setPosition(x,y);
        s.addActor(this);
    }
}
