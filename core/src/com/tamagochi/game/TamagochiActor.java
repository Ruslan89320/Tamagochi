package com.tamagochi.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tamagochi.game.utils.Constants;

public class TamagochiActor extends Actor {
    Sprite tamagochiSprite;
    Music mewSound;
    Music shirokiySound;
    Sound mrrSound;
    TextureAtlas shirokiyAtlas;
    TextureAtlas clickAtlas;
    TextureAtlas clickAtlas2;
    TextureAtlas mrrAtlas;
    Animation mrrAnimation;
    Animation clickAnimation;
    Animation clickAnimation2;
    Animation shirAnimation;
    float stateTime = 0;
    float happyTime = 0;
    int anim = 0;
    boolean canClicked = true;
    int happy = 0;

    public TamagochiActor() {
        clickAtlas = new TextureAtlas(Gdx.files.internal("clickatlas.atlas"));
        clickAtlas2 = new TextureAtlas(Gdx.files.internal("clickatlas2.atlas"));
        clickAnimation = new Animation(0.2f, clickAtlas.getRegions());
        clickAnimation2 = new Animation(0.2f, clickAtlas2.getRegions());
        shirokiyAtlas = new TextureAtlas(Gdx.files.internal("shirokiyatlas.atlas"));
        shirAnimation = new Animation(0.06f, shirokiyAtlas.getRegions());
        mrrAtlas = new TextureAtlas(Gdx.files.internal("mrratlas.atlas"));
        mrrAnimation = new Animation(0.2f, mrrAtlas.getRegions());
        tamagochiSprite = new Sprite(new Texture(Gdx.files.internal("TamagochiCat.png")));
        shirokiySound = Gdx.audio.newMusic(Gdx.files.internal("shirokiy.wav"));
        mewSound = Gdx.audio.newMusic(Gdx.files.internal("mew.wav"));
        mrrSound = Gdx.audio.newSound(Gdx.files.internal("mrr.wav"));
        setTouchable(Touchable.enabled);
        tamagochiSprite.setPosition( Constants.WIDTH/2-tamagochiSprite.getWidth()/2 + 10,100);
        setBounds(tamagochiSprite.getX(),tamagochiSprite.getY(),tamagochiSprite.getWidth(),tamagochiSprite.getHeight());

        addListener(new InputListener() {
            boolean canAnim = true, canSound = true;
            long time1 = 0, time2 = 0;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { //нажатие начинается
                time1 = System.currentTimeMillis();
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) { //котик гладится
                time2 = System.currentTimeMillis() - time1;
                if(time2 > 150)
                {
                    if (canAnim) anim = 3;
                    else anim = 0;
                    if (canSound) {
                        mrrSound.loop();
                        canSound = false;
                    }
                    if (canClicked) canClicked = false; //чтоб он не отрисовывал анимации клика
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) { //в пределах котика
                canAnim = true;
                canSound = true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) { //за пределами котика стопаю анимацию
                canAnim = false;
                mrrSound.stop();
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { //нажатие останавливается
                time2 = System.currentTimeMillis() - time1;
                if(time2 < 200) {
                    canClicked = true;
                }
                if(anim == 3) anim = 0;
                mrrSound.stop();
                time1 = 0;
                time2 = 0;
                super.touchUp(event, x, y, pointer, button);
                }
            }
        );
        addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if(canClicked) {
                    int rand = (int) (Math.random() * 1000 + 1);
                    if (anim == 0) {
                        if (rand == 491) //ШИРОКИЙ
                        {
                            if(!shirokiySound.isPlaying()) shirokiySound.play();
                            anim = 2;
                        } else //прыг
                        {
                            if(!mewSound.isPlaying()) mewSound.play();
                            if(rand%4 != 0)
                            {
                                anim = 1;
                            }
                            else
                            {
                                anim = 4;
                            }
                        }
                    }
                }
                else canClicked = true;
            }});
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(anim == 1)
        {
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw((TextureRegion) clickAnimation.getKeyFrame(stateTime, false), Constants.WIDTH/2 - tamagochiSprite.getWidth()/2 + 10, 100);

            if(stateTime >= clickAnimation.getAnimationDuration())
            {
                anim = 0;
            }
        }
        else if(anim == 2)
        {
            stateTime += Gdx.graphics.getDeltaTime();
            TextureRegion reg = (TextureRegion)shirAnimation.getKeyFrame(stateTime, false);
            batch.draw(reg, Constants.WIDTH/2 - reg.getRegionWidth()/2 + 10 , 100);

            if(stateTime >= shirAnimation.getAnimationDuration())
            {
                anim = 0;
            }
        }
        else if(anim == 3)
        {
            happy = 0;
            stateTime += Gdx.graphics.getDeltaTime();
            happyTime += Gdx.graphics.getDeltaTime();
            batch.draw((TextureRegion) mrrAnimation.getKeyFrame(stateTime, true), Constants.WIDTH/2 - tamagochiSprite.getWidth()/2 + 10, 100);
            if(happyTime >= 1.5)
            {
                happy = 1;
                happyTime = 0;
            }

        }
        else if(anim == 4)
        {
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw((TextureRegion) clickAnimation2.getKeyFrame(stateTime, true), Constants.WIDTH/2 - tamagochiSprite.getWidth()/2 + 10, 100);

            if(stateTime >= clickAnimation2.getAnimationDuration()*2)
            {
                anim = 0;
            }
        }
        else
        {
            stateTime = 0;
            tamagochiSprite.draw(batch);
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public int getHappy() {
        return this.happy;
    }
}
