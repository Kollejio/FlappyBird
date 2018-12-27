package com.stasique.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ceasefire on 02.12.2018.
 */
public class Bird {
    // падение птицы
    private static final int GRAVITY = -15;
    // скорость птицы
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;

    // границы столкновений
    private Rectangle bounds;

    // присоединяем анимацию
    private com.stasique.game.sprites.Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");

        birdAnimation = new com.stasique.game.sprites.Animation(new TextureRegion(texture), 3, 0.5f);

        // создаем границу столкновений для птички
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());

        // звуки
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);

        // реализация гравитации
        if(position.y > 0)
            velocity.add(0, GRAVITY, 0);

        // скалярно умножаем вектор на время для получения правильного velocity
        // это важно
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        // не даем птице улететь вниз
        if(position.y < 0)
            position.y = 0;

        // скорость птицы при падении меняется с течением времени
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        // велосити на 250, при изменении времени
        // значение будет снижаться
        velocity.y = 250;

        // проигрывание звука
        flap.play(0.5f);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
        flap.dispose();
    }

}