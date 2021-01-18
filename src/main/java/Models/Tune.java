package Models;


import java.util.ArrayList;


public class Tune {
    String name;

    public Tune(String name, String location, String answers) {
        this.name = name;
        this.location = location;
        this.answers = answers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    String location;
    String answers;

}
