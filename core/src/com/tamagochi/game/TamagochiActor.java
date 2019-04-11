package com.tamagochi.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tamagochi.game.utils.Constants;

public class TamagochiActor extends Actor {
    Sprite tamagochiSprite;
    Sound mewSound;
    TextureAtlas clickAtlas;
    Animation clickAnimation;
    float stateTime = 0;
    int anim = 0;



    public TamagochiActor() {
        clickAtlas = new TextureAtlas(Gdx.files.internal("clickatlas.atlas"));
        clickAnimation = new Animation(0.2f, clickAtlas.getRegions());
        tamagochiSprite =new Sprite(new Texture(Gdx.files.internal("TamagochiCat.png")));
        mewSound = Gdx.audio.newSound(Gdx.files.internal("mew.wav"));
        setTouchable(Touchable.enabled);
        tamagochiSprite.setPosition( Constants.WIDTH/2-tamagochiSprite.getWidth()/2,90);
        setBounds(tamagochiSprite.getX(),tamagochiSprite.getY(),tamagochiSprite.getWidth(),tamagochiSprite.getHeight());
        addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                anim = 1;
                mewSound.play();
            }});
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(anim == 1) {
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw((TextureRegion) clickAnimation.getKeyFrame(stateTime, false), Constants.WIDTH/2 - tamagochiSprite.getWidth()/2, 90);
            if(stateTime >= clickAnimation.getAnimationDuration()){
                //тут потом через кейсы сделать
                anim = 0;
                stateTime = 0;
            }
        }
        else{
            tamagochiSprite.draw(batch);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
