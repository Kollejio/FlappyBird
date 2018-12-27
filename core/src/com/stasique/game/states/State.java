package com.stasique.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ceasefire on 28.11.2018.
 */
public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected com.stasique.game.states.GameStateManager gsm;

    public State(com.stasique.game.states.GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
