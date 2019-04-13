package com.tamagochi.game.screens;

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
import com.tamagochi.game.Logic;
import com.tamagochi.game.utils.Constants;

public class Hud extends Group implements Disposable {
    public Stage stage;
    private Viewport viewport;
    public Logic logic;

    private Label hungercountLabel, happinescountLabel, sleepcountLabel, thristcountLabel, expungecountLabal;
    Texture hungerbuttonTexture;
    TextureRegion hungerbuttonTextureRegion;
    TextureRegionDrawable hungerbuttonTextureRegionDrawable;
    ImageButton HungerButton;

    public Hud(SpriteBatch sb){
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        logic = new Logic();

        hungercountLabel = new Label(String.format("%03d", logic.getHunger()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        hungerbuttonTexture = new Texture(Gdx.files.internal("Buttons/HungerButton.png"));
        hungerbuttonTextureRegion = new TextureRegion(hungerbuttonTexture);
        hungerbuttonTextureRegionDrawable = new TextureRegionDrawable(hungerbuttonTextureRegion);
        HungerButton = new ImageButton(hungerbuttonTextureRegionDrawable);
        HungerButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                logic.doEat(2000);
            }
        });

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(HungerButton).expandX().padTop(10);
        table.row();
        table.add(hungercountLabel).expandX();

        stage.addActor(table);
    }

    public void dispose() { stage.dispose(); }

    @Override
    public void act(float delta) {
        super.act(delta);
        printNeeds();
        stage.act();
    }

    public void printNeeds(){
        logic.Cycle();
        hungercountLabel.setText(String.format("%03d", logic.getHunger()));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stage.draw();
    }
}
