package com.tamagochi.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.tamagochi.game.screens.LoadingScreen;

public class TamagochiGame extends Game {
	Music backgroundMusic;
	public void create() {
        GameManager.getInstance().initializeGameData();
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("BackgroundMusic.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(GameManager.getInstance().gameData.getmusicVolume());
		backgroundMusic.play();
		setScreen(new LoadingScreen(this));
	}

	public Music getBackgroundMusic(){
		return backgroundMusic;
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		GameManager.getInstance().saveData();
		backgroundMusic.dispose();
		super.dispose();
	}
}
