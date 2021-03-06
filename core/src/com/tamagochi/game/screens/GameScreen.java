package com.tamagochi.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tamagochi.game.GameManager;
import com.tamagochi.game.Logic;
import com.tamagochi.game.TamagochiActor;
import com.tamagochi.game.TamagochiGame;
import com.tamagochi.game.utils.Constants;


public class GameScreen implements Screen {
    private final TamagochiGame game;
    private SpriteBatch batch;
    private Stage stage;
    private Texture backgroundTexture;
    TamagochiActor TamagochiActor;
    private Hud hud;
    private OrthographicCamera camera;
    private InputMultiplexer multiplexer;


    public GameScreen(TamagochiGame TamagochiGame){
        this.game = TamagochiGame;

        batch = new SpriteBatch();
        hud= new Hud(batch,game);

        stage = new Stage(new ScreenViewport());


        backgroundTexture = new Texture(Gdx.files.internal("BackgroundHouse1.png"));
        Image background = new Image(backgroundTexture);
        background.setPosition(0,0);
        background.setSize(Constants.WIDTH,Constants.HEIGHT);
        stage.addActor(background);


        camera = (OrthographicCamera) stage.getViewport().getCamera();
        camera.setToOrtho(false,Constants.WIDTH,Constants.HEIGHT);


        TamagochiActor = new TamagochiActor(hud);
        stage.addActor(TamagochiActor);

    }

    public void show() {
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(hud.stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        stage.act(Gdx.graphics.getDeltaTime());
        hud.act(Gdx.graphics.getDeltaTime());

        stage.draw();

        hud.draw(batch,1);
        if((hud.getLogic().getHappiness() + TamagochiActor.getHappy()) <= 80) hud.getLogic().setHappiness(hud.getLogic().getHappiness() + TamagochiActor.getHappy());
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        GameManager.getInstance().saveData();
    }

    @Override
    public void resume() {
        hud.logic=new Logic(GameManager.getInstance().gameData.getHunger(),GameManager.getInstance().gameData.getExpunge(),
                GameManager.getInstance().gameData.getThist(), GameManager.getInstance().gameData.getSleep(),
                GameManager.getInstance().gameData.getHappines(),GameManager.getInstance().gameData.getAge(),GameManager.getInstance().gameData.getDate());
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        GameManager.getInstance().saveData();
        backgroundTexture.dispose();
        stage.dispose();
    }
}
