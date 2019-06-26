package com.tamagochi.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class GameManager {
    private static GameManager ourInstance = new GameManager();

    public GameData gameData;
    private Json json = new Json();
    private FileHandle fileHandle = Gdx.files.local("GameData.json");
    private GameManager() {}

    public void initializeGameData() {
        if (!fileHandle.exists()) {
            gameData = new GameData();

            gameData.setDate((int)System.currentTimeMillis());
            gameData.setThist(100);
            gameData.setExpunge(100);
            gameData.setSleep(100);
            gameData.setHappines(100);
            gameData.setHunger(100);
            gameData.setAge(0);
            gameData.setCatType(1);
            gameData.setmusicVolume(0.5f);
            saveData();
        } else {
            loadData();
        }
    }

    public void startNewGame(){
        gameData.setDate((int)System.currentTimeMillis());
        gameData.setThist(100);
        gameData.setExpunge(100);
        gameData.setSleep(100);
        gameData.setHappines(100);
        gameData.setHunger(100);
        gameData.setAge(0);
        gameData.setCatType(1);
        gameData.setmusicVolume(0.5f);
        saveData();
    }

    public void saveData() {
        gameData.setDate((int)System.currentTimeMillis());
        if (gameData != null) {
            fileHandle.writeString(Base64Coder.encodeString(json.prettyPrint(gameData)),
                    false);
        }
    }

    public void loadData() {
        gameData = json.fromJson(GameData.class,
                Base64Coder.decodeString(fileHandle.readString()));
    }
    public static GameManager getInstance() {
        return ourInstance;
    }
}
