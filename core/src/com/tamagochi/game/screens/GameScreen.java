package com.tamagochi.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tamagochi.game.Logic;
import com.tamagochi.game.TamagochiActor;
import com.tamagochi.game.utils.Constants;


public class GameScreen implements Screen {
    private final Game game;
    SpriteBatch batch;
    private Stage stage;
    Texture backgroundTexture;
    Music backgroundMusic;
    TamagochiActor TamagochiActor;
    Logic logic;
    private Hud hud;



    public GameScreen(Game TamagochiGame){
        this.game = TamagochiGame;


        batch = new SpriteBatch();
        hud= new Hud(batch);


        logic = new Logic();


        stage = new Stage(new ScreenViewport());


        backgroundTexture = new Texture(Gdx.files.internal("BackgroundHouse.jpg"));
        Image background = new Image(backgroundTexture);
        background.setPosition(0,0);
        background.setSize(Constants.WIDTH,Constants.HEIGHT);
        stage.addActor(background);


        TamagochiActor = new TamagochiActor();
        stage.addActor(TamagochiActor);


        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("BackgroundMusic.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.2f);
        backgroundMusic.play();
    }

    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(Gdx.graphics.getDeltaTime());
        hud.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        hud.draw(batch,1);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        backgroundMusic.dispose();
        stage.dispose();
    }
}
