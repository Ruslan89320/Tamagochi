package com.tamagochi.game;

import com.badlogic.gdx.Game;
import com.tamagochi.game.screens.LoadingScreen;

public class TamagochiGame extends Game {

	public void create() {
		GameManager.getInstance().initializeGameData();
		setScreen(new LoadingScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		GameManager.getInstance().saveData();
		super.dispose();
	}
}
