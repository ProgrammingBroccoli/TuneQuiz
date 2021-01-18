package Models;

import Controllers.GameController;

public class GameStateWrapper {
    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    private boolean gameStarted;
    public GameStateWrapper(boolean gameStarted){
        this.gameStarted = gameStarted;
    }

}
