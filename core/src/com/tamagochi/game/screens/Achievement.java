package com.tamagochi.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tamagochi.game.GameManager;
import com.tamagochi.game.TamagochiGame;
import com.tamagochi.game.utils.Constants;

public class Achievement implements Screen {

    Table table;
    Stage stage;
    Skin skin;
    private OrthographicCamera camera;
    private Texture backgroundTexture;
    Button BackButton;

    public Achievement(final TamagochiGame game){
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("MenuSkin/uiskin.json"));


        camera = (OrthographicCamera) stage.getViewport().getCamera();
        camera.setToOrtho(false, Constants.WIDTH,Constants.HEIGHT);

        backgroundTexture = new Texture(Gdx.files.internal("BackgroundSettings.jpg"));
        Image background = new Image(backgroundTexture);
        background.setPosition(0,0);
        background.setSize(Constants.WIDTH,Constants.HEIGHT);


        BackButton = new TextButton("Back to game",skin);
        BackButton.setSize(Constants.WIDTH/2,Constants.HEIGHT/6);
        BackButton.setPosition(Constants.WIDTH/2-BackButton.getWidth()/2,30);
        BackButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        stage.addActor(background);


        if(GameManager.getInstance().gameData.getAchievGolodniy()){
          Achiev("YaNeGolodniy.png", "Feed a pet when its Hunger is 100%",400);
        }
        else{
           AchievLocked(400);
        }


        if(GameManager.getInstance().gameData.getAchievShirokiy()){
            Achiev("Shirokiy.png", "Get the animation \"Shirokiy\"",300);
        }
        else{AchievLocked(300);}



        stage.addActor(BackButton);
    }

    public void Achiev(String file ,String AchievName, float y){
        Texture LockTexture = new Texture(Gdx.files.internal(file));
        Image Lock = new Image(LockTexture);
        Lock.setPosition(Constants.WIDTH/4,y-Lock.getHeight()/2);
        Label AchievLabel = new Label(AchievName, new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        AchievLabel.setPosition(Constants.WIDTH/2-AchievLabel.getWidth()/2,y-AchievLabel.getHeight()/2);
        stage.addActor(Lock);
        stage.addActor(AchievLabel);
    }

    public void AchievLocked(float y){
        Texture LockTexture = new Texture(Gdx.files.internal("AchievLock.png"));
        Image Lock = new Image(LockTexture);
        Lock.setPosition(Constants.WIDTH/4,y-Lock.getHeight()/2);
        Label AchievLockedLabel = new Label("Achievement Locked", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        AchievLockedLabel.setPosition(Constants.WIDTH/2-AchievLockedLabel.getWidth()/2,y-AchievLockedLabel.getHeight()/2);
        stage.addActor(Lock);
        stage.addActor(AchievLockedLabel);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {
        GameManager.getInstance().saveData();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
