package Models;

import java.util.ArrayList;

public class LobbyWrapper {
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    ArrayList<Player> players;

    public LobbyWrapper() {

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
