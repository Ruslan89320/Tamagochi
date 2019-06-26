package com.tamagochi.game.screens;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.scenes.scene2d.ui.Image;
        import com.badlogic.gdx.scenes.scene2d.ui.Label;
        import com.tamagochi.game.TamagochiGame;
        import com.tamagochi.game.utils.Constants;

public class LoadingScreen implements Screen {
    private TamagochiGame game;
    int i =0;
    Stage stage;
    Texture catTexture;
    Image catImage;
    Label LoadingLabel;
    private OrthographicCamera camera;

    public LoadingScreen(final TamagochiGame game) {
        this.game = game;
        stage = new Stage();
        camera = (OrthographicCamera) stage.getViewport().getCamera();
        camera.setToOrtho(false,Constants.WIDTH,Constants.HEIGHT);
        catTexture = new Texture(Gdx.files.internal("LoadingCat.png"));
        catImage = new Image(catTexture);
        catImage.setOrigin(catImage.getWidth()/2,catImage.getHeight()/2);
        catImage.setPosition(Constants.WIDTH/2-211/2,Constants.HEIGHT/2-163/2);
        LoadingLabel = new Label("Loading...", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        LoadingLabel.setPosition(Constants.WIDTH/2-LoadingLabel.getWidth()/2, 110);
        LoadingLabel.setSize(100,20);
        stage.addActor(LoadingLabel);
        stage.addActor(catImage);

    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        catImage.rotateBy(0.6f);
        i++;
        if(i==200){
            game.setScreen(new GameScreen(game));
        }
        camera.update();
        stage.act();
        stage.draw();

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
    }
}
