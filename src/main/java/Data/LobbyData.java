package Data;

import Models.Player;
import Models.Tune;

import java.util.ArrayList;

public class LobbyData {
    private static LobbyData instance = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String username;
    ArrayList<Player> players;
    boolean isGameStarted;
    ArrayList<Tune> tunes;

    public String getSelectedPack() {
        return selectedPack;
    }

    String selectedPack = "";

    public static LobbyData getInstance(){
        if (instance == null){
            instance = new LobbyData();
        }
        return instance;
    }

    private LobbyData(){
        players = new ArrayList<Player>();
        isGameStarted = false;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }


    public void setPack(String pack) {
        selectedPack = pack;
    }

    public void setGameState(boolean gameStarted) {
        isGameStarted = gameStarted;
    }

    public void setTunes(ArrayList<Tune> tunes) {
        this.tunes = tunes;
    }

    public ArrayList<Tune> getTunes() {
        return tunes;
    }
}
