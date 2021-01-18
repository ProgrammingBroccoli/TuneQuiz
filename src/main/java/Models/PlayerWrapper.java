package Models;

public class PlayerWrapper {
    public PlayerWrapper(String property, Player player) {
        this.property = property;
        this.player = player;
    }

    String property;
    Player player;


    public String getProperty() {
        return property;
    }

    public Player getPlayer() {
        return player;
    }


}