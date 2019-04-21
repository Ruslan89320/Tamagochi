package com.tamagochi.game;

import com.badlogic.gdx.Game;
import com.tamagochi.game.screens.GameScreen;

public class TamagochiGame extends Game {

	public void create() {
		GameManager.getInstance().initializeGameData();
		setScreen(new GameScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		GameManager.getInstance().saveData();
		super.dispose();
	}
}
