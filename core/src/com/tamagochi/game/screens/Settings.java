package com.tamagochi.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tamagochi.game.TamagochiGame;
import com.tamagochi.game.utils.Constants;
import com.tamagochi.game.GameManager;

public class Settings implements Screen {
    Stage stage;
    Skin skin;
    Label musicLabel;
    Slider musicSlider;
    Button BackButton;
    private Texture backgroundTexture;
    private OrthographicCamera camera;
    ImageButton CostumeMenuIconButton, FirstCatButton,SecondCatButton, ThirdCatButton;

    public Settings(final TamagochiGame game) {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("MenuSkin/uiskin.json"));



        camera = (OrthographicCamera) stage.getViewport().getCamera();
        camera.setToOrtho(false,Constants.WIDTH,Constants.HEIGHT);



        musicLabel = new Label("Music",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        musicLabel.setPosition(Constants.WIDTH/2-musicLabel.getWidth()/2,420);
        musicLabel.setSize(20,8);

        musicSlider = new Slider(0, 1, 0.05f, false, skin);
        musicSlider.setPosition(Constants.WIDTH/2-musicSlider.getWidth()/2,380);
        musicSlider.setValue(GameManager.getInstance().gameData.getmusicVolume());
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameData.setmusicVolume(musicSlider.getValue());
                game.getBackgroundMusic().setVolume(musicSlider.getValue());
            }
        });


        CostumeMenuIconButton = makeImageButton("CostumeMenuIcon.png");
        CostumeMenuIconButton.setSize(50,50);
        CostumeMenuIconButton.setPosition(Constants.WIDTH/2- CostumeMenuIconButton.getWidth()/2, 250);
        CostumeMenuIconButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                CostumeMenuIconButton.setVisible(false);
                FirstCatButton.setVisible(true);
                SecondCatButton.setVisible(true);
                ThirdCatButton.setVisible(true);
            }
        });


        FirstCatButton = makeImageButton("TamagochiCat1.png");
        FirstCatButton.setVisible(false);
        FirstCatButton.setSize(100,100);
        FirstCatButton.setPosition(Constants.WIDTH/3- FirstCatButton.getWidth()/2, 250);
        FirstCatButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                GameManager.getInstance().gameData.setCatType(1);
                CostumeMenuIconButton.setVisible(true);
                FirstCatButton.setVisible(false);
                SecondCatButton.setVisible(false);
                ThirdCatButton.setVisible(false);
            }
        });


        SecondCatButton = makeImageButton("TamagochiCat2.png");
        SecondCatButton.setVisible(false);
        SecondCatButton.setSize(100,100);
        SecondCatButton.setPosition(Constants.WIDTH/2-  SecondCatButton.getWidth()/2, 250);
        SecondCatButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                GameManager.getInstance().gameData.setCatType(2);
                CostumeMenuIconButton.setVisible(true);
                FirstCatButton.setVisible(false);
                SecondCatButton.setVisible(false);
                ThirdCatButton.setVisible(false);
            }
        });


        ThirdCatButton = makeImageButton("TamagochiCat3.png");
        ThirdCatButton.setVisible(false);
        ThirdCatButton.setSize(100,100);
        ThirdCatButton.setPosition(2*Constants.WIDTH/3-  ThirdCatButton.getWidth()/2, 250);
        ThirdCatButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                GameManager.getInstance().gameData.setCatType(3);
                CostumeMenuIconButton.setVisible(true);
                FirstCatButton.setVisible(false);
                SecondCatButton.setVisible(false);
                ThirdCatButton.setVisible(false);
            }
        });


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
        stage.addActor(FirstCatButton);
        stage.addActor(SecondCatButton);
        stage.addActor(ThirdCatButton);
        stage.addActor(BackButton);
        stage.addActor(CostumeMenuIconButton);
        stage.addActor(musicSlider);
        stage.addActor(musicLabel);
    }

    public ImageButton makeImageButton(String file){
        Texture buttonTexture = new Texture(Gdx.files.internal(file));
        TextureRegion buttonTextureRegion = new TextureRegion( buttonTexture);
        TextureRegionDrawable buttonTextureRegionDrawable = new TextureRegionDrawable( buttonTextureRegion);
        ImageButton button = new ImageButton(buttonTextureRegionDrawable);
        return button;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
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

    public void show() {
        Gdx.input.setInputProcessor(stage);
    }}
