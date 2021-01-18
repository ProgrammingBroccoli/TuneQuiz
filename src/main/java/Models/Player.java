package Models;

public class Player {
    public Player() {
    }

    public Player(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    String Name;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    int points;

}
