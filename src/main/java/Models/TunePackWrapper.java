package Models;

import java.util.ArrayList;

public class TunePackWrapper {
    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public ArrayList<Tune> getTunes() {
        return tunes;
    }

    public void setTunes(ArrayList<Tune> tunes) {
        this.tunes = tunes;
    }

    String packName;
    ArrayList<Tune> tunes;
}
