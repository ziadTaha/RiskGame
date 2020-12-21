package com.mygdx.game;

public class RiskGame extends  BaseGame{
    @Override
    public void create() {
        setActiveScreen(new MenuScreen(this));
    }
}
