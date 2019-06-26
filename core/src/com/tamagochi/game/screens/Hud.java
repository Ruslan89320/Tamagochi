package com.tamagochi.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
    int a=0,b=0;
    Table table;
    Image TableImage;
    Skin skin;
    Button  NewGameButton;

    private Label hungercountLabel, happinesscountLabel, sleepcountLabel, thristcountLabel, expungecountLabel,deathLabel;
    Texture TableTexture;
    ImageButton HungerButton, HappinessButton,ThristButton, ExpungeButton,SleepButton,SettingsButton, AchievButton;

    public Hud(SpriteBatch sb, final TamagochiGame game){
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        logic = new Logic(GameManager.getInstance().gameData.getHunger(),GameManager.getInstance().gameData.getExpunge(),
                GameManager.getInstance().gameData.getThist(), GameManager.getInstance().gameData.getSleep(),
                GameManager.getInstance().gameData.getHappines(),GameManager.getInstance().gameData.getAge(),GameManager.getInstance().gameData.getDate());



        hungercountLabel = new Label(String.format("%03d", logic.getHunger()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));

        HungerButton = makeImageButton("Buttons/HungerButton.png");
        HungerButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(logic.getHunger()==100 && GameManager.getInstance().gameData.getAchievGolodniy() == false){
                    GameManager.getInstance().gameData.setAchievGolodniy(true);
                    Music YaNeGolodniy = Gdx.audio.newMusic(Gdx.files.internal("YaNeGolodniy.mp3"));
                    YaNeGolodniy.play();
                }
                logic.doEat(40);
            }
        });



        thristcountLabel = new Label(String.format("%03d", logic.getThrist()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));

        ThristButton = makeImageButton("Buttons/ThristButton.png");
        ThristButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                logic.doDrink(40);
            }
        });



        expungecountLabel = new Label(String.format("%03d", logic.getThrist()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));

        ExpungeButton = makeImageButton("Buttons/ExpungeButton.png");
        ExpungeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                logic.doWaste();
            }
        });




        sleepcountLabel = new Label(String.format("%03d", logic.getThrist()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));

        SleepButton = makeImageButton("Buttons/SleepButton.png");
        SleepButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                logic.doSleep(80);
            }
        });



        happinesscountLabel = new Label(String.format("%03d", logic.getHappiness()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        HappinessButton = makeImageButton("Buttons/HappinessButton.png");



        SettingsButton = makeImageButton("Buttons/SettingsButton.png");
        SettingsButton.setSize(30,30);
        SettingsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Settings(game));

            }
        });



        AchievButton = makeImageButton("Buttons/AchievButton.png");
        AchievButton.setSize(30,30);
        AchievButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Achievement(game));
            }
        });


        TableTexture = new Texture(Gdx.files.internal("DeathTable.png"));
        TableImage = new Image(TableTexture);
        TableImage.setPosition(Constants.WIDTH/2-TableImage.getWidth()/2,Constants.HEIGHT/2-TableImage.getHeight()/2);
        TableImage.setVisible(false);
        deathLabel = new Label("Dead", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        deathLabel.setVisible(false);

        skin = new Skin(Gdx.files.internal("MenuSkin/uiskin.json"));
        NewGameButton = new TextButton("Start new game",skin);
        NewGameButton.setSize(Constants.WIDTH/2,Constants.HEIGHT/6);
        NewGameButton.setPosition(Constants.WIDTH/2-NewGameButton.getWidth()/2,2*Constants.WIDTH/7 - NewGameButton.getHeight()/2);
        NewGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                GameManager.getInstance().startNewGame();
                game.setScreen(new GameScreen(game));
            }
        });
        NewGameButton.setVisible(false);

        table = new Table();
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
        table.add();
        table.add();
        table.add();
        table.add(AchievButton).right().bottom();


        stage.addActor(table);
        stage.addActor(TableImage);
        stage.addActor(deathLabel);
        stage.addActor(NewGameButton);
    }

    public ImageButton makeImageButton(String file){
        Texture buttonTexture = new Texture(Gdx.files.internal(file));
        TextureRegion buttonTextureRegion = new TextureRegion( buttonTexture);
        TextureRegionDrawable buttonTextureRegionDrawable = new TextureRegionDrawable( buttonTextureRegion);
        ImageButton button = new ImageButton(buttonTextureRegionDrawable);
        return button;
    }

    public void DeathLabel(){
        if(logic.getHunger()==0){
            deathLabel.setText("Your pet has died from Hunger");
        }
        if(logic.getThrist()==0){
            deathLabel.setText("Your pet has died from Thrist");
        }
        if(logic.getExpunge()==0){
            deathLabel.setText("Your pet has died from Disease");
        }
        if(logic.getAge()==819200){
            deathLabel.setText("Your pet has died of old age");
        }
        deathLabel.setPosition(300,2*Constants.HEIGHT/3-deathLabel.getHeight()/2);
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
        if(logic.isDead()){
            b++;
            if(b>100) {
                table.setVisible(false);
                deathLabel.setVisible(true);
                TableImage.setVisible(true);
                NewGameButton.setVisible(true);
                DeathLabel();
            }
        }
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
