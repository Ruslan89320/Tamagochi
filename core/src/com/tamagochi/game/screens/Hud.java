package com.tamagochi.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tamagochi.game.GameManager;
import com.tamagochi.game.Logic;
import com.tamagochi.game.TamagochiGame;
import com.tamagochi.game.utils.Constants;

public class Hud extends Group implements Disposable {
    public Stage stage;
    private Viewport viewport;
    public Logic logic;
    int a=0;


    private Label hungercountLabel, happinesscountLabel, sleepcountLabel, thristcountLabel, expungecountLabel;
    Texture hungerbuttonTexture,happinessbuttonTexture,sleepbuttonTexture, thristbuttonTexture,
            expungebuttonTexture, settingsbuttonTexture;
    TextureRegion hungerbuttonTextureRegion, happinessbuttonTextureRegion, thristbuttonTextureRegion,
            expungebuttonTextureRegion, sleepbuttonTextureRegion,settingsbuttonTextureRegion;
    TextureRegionDrawable hungerbuttonTextureRegionDrawable,  happinessbuttonTextureRegionDrawable,
            thristbuttonTextureRegionDrawable,expungebuttonTextureRegionDrawable,sleepbuttonTextureRegionDrawable,
            settingsbuttonTextureRegionDrawable;
    ImageButton HungerButton, HappinessButton,ThristButton, ExpungeButton,SleepButton,SettingsButton;

    public Hud(SpriteBatch sb, final Game game,final GameScreen gameScreen){
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        logic = new Logic(GameManager.getInstance().gameData.getHunger(),GameManager.getInstance().gameData.getExpunge(),
                GameManager.getInstance().gameData.getThist(), GameManager.getInstance().gameData.getSleep(),
                GameManager.getInstance().gameData.getHappines(),GameManager.getInstance().gameData.getAge(),GameManager.getInstance().gameData.getDate());



        hungercountLabel = new Label(String.format("%03d", logic.getHunger()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        hungerbuttonTexture = new Texture(Gdx.files.internal("Buttons/HungerButton.png"));
        hungerbuttonTextureRegion = new TextureRegion(hungerbuttonTexture);
        hungerbuttonTextureRegionDrawable = new TextureRegionDrawable(hungerbuttonTextureRegion);
        HungerButton = new ImageButton(hungerbuttonTextureRegionDrawable);
        HungerButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                logic.doEat(40);
            }
        });

        thristcountLabel = new Label(String.format("%03d", logic.getThrist()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        thristbuttonTexture = new Texture(Gdx.files.internal("Buttons/ThristButton.png"));
        thristbuttonTextureRegion = new TextureRegion(thristbuttonTexture);
        thristbuttonTextureRegionDrawable = new TextureRegionDrawable(thristbuttonTextureRegion);
        ThristButton = new ImageButton(thristbuttonTextureRegionDrawable);
        ThristButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                logic.doDrink(40);
            }
        });

        expungecountLabel = new Label(String.format("%03d", logic.getThrist()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        expungebuttonTexture = new Texture(Gdx.files.internal("Buttons/ExpungeButton.png"));
        expungebuttonTextureRegion = new TextureRegion(expungebuttonTexture);
        expungebuttonTextureRegionDrawable = new TextureRegionDrawable(expungebuttonTextureRegion);
        ExpungeButton = new ImageButton(expungebuttonTextureRegionDrawable);
        ExpungeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                logic.doWaste();
            }
        });

        sleepcountLabel = new Label(String.format("%03d", logic.getThrist()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        sleepbuttonTexture = new Texture(Gdx.files.internal("Buttons/SleepButton.png"));
        sleepbuttonTextureRegion = new TextureRegion(sleepbuttonTexture);
        sleepbuttonTextureRegionDrawable = new TextureRegionDrawable(sleepbuttonTextureRegion);
        SleepButton = new ImageButton(sleepbuttonTextureRegionDrawable);
        SleepButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                logic.doSleep(80);
            }
        });

        happinesscountLabel = new Label(String.format("%03d", logic.getHappiness()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        happinessbuttonTexture = new Texture(Gdx.files.internal("Buttons/HappinessButton.png"));
        happinessbuttonTextureRegion = new TextureRegion(happinessbuttonTexture);
        happinessbuttonTextureRegionDrawable = new TextureRegionDrawable(happinessbuttonTextureRegion);
        HappinessButton = new ImageButton(happinessbuttonTextureRegionDrawable);

        settingsbuttonTexture = new Texture(Gdx.files.internal("Buttons/SettingsButton.png"));
        settingsbuttonTextureRegion = new TextureRegion(settingsbuttonTexture);
        settingsbuttonTextureRegionDrawable = new TextureRegionDrawable(settingsbuttonTextureRegion);
        SettingsButton = new ImageButton(settingsbuttonTextureRegionDrawable);

        SettingsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //TODO
                game.setScreen(new Settings(game,gameScreen));

            }
        });


        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(HappinessButton).expandX().padTop(10);
        table.add(SleepButton).expandX().padTop(10);
        table.add(ThristButton).expandX().padTop(10);
        table.add(HungerButton).expandX().padTop(10);
        table.add(ExpungeButton).expandX().padTop(10);
        table.row();
        table.add(happinesscountLabel).expandX();
        table.add(sleepcountLabel).expandX();
        table.add(thristcountLabel).expandX();
        table.add(hungercountLabel).expandX();
        table.add(expungecountLabel).expandX();
        table.row();
        table.add(SettingsButton).expand().left().bottom();

        stage.addActor(table);
    }

    public void dispose() {
        GameManager.getInstance().saveData();
        stage.dispose(); }

    @Override
    public void act(float delta) {
        GameManager.getInstance().gameData.setAll(logic);
        super.act(delta);
        printNeeds();
        stage.act();
        stage.draw();
    }

    public Logic getLogic(){
        return logic;
    }
    public void printNeeds(){
        a++;
        if(a==10000) {
            logic.Cycle();
            a = 0;
        }
        hungercountLabel.setText(String.format("%03d", logic.getHunger()));
        thristcountLabel.setText(String.format("%03d", logic.getThrist()));
        happinesscountLabel.setText(String.format("%03d", logic.getHappiness()));
        expungecountLabel.setText(String.format("%03d", logic.getExpunge()));
        sleepcountLabel.setText(String.format("%03d", logic.getSleep()));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stage.draw();
    }

    @Override
    public void clear() {
        super.clear();
    }
}
