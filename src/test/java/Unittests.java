import Controllers.AccountController;
import Controllers.LobbyController;
import Data.LobbyData;
import GUI.TuneQuizApplication;
import Models.Player;
import Models.Tune;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Unittests {
    AccountController accountController;
    LobbyController lobbyController;
    LobbyData lobbyData;
    TuneQuizApplication tuneQuizApplication;

    public Unittests() throws IOException {
        accountController = new AccountController();
        lobbyController = new LobbyController(accountController);
        lobbyData = LobbyData.getInstance();
        tuneQuizApplication = new TuneQuizApplication();
    }


    @Test
    void SelectValidPack(){
        String pack = "Efteling";
        tuneQuizApplication.setupWebSocket();

        lobbyController.SelectPack(pack);

        String selectedPack = lobbyData.getSelectedPack();

        Assertions.assertEquals(pack, selectedPack);
    }
    @Test
    void SelectInvalidPack(){
        String pack = "invalid";
        tuneQuizApplication.setupWebSocket();

        lobbyController.SelectPack(pack);

        String selectedPack = lobbyData.getSelectedPack();

        Assertions.assertNotEquals(pack, selectedPack);
    }

    @Test
    void SetPlayersInLobbyData(){
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Joost"));
        players.add(new Player("Peter"));

        lobbyController.setPlayers(players);
        ArrayList<Player> returningPlayers = lobbyController.getPlayers();

        Assertions.assertEquals(players, returningPlayers);
    }

    @Test
    void SetGameState(){
        boolean newstate = true;
        lobbyController.setGameState(newstate);
        Assertions.assertEquals(true, newstate);
    }

    @Test
    void SetTunes(){
        ArrayList<Tune> tunes = new ArrayList<Tune>(){{
            add(new Tune("Villa volta",
                    "C:\\Users\\Ruben\\Documents\\SynologyDrive\\Drive\\Fontys\\-=S3=-\\BIG IDEA\\TuneQuiz\\src\\main\\resources\\tunes\\villavolta.mp3",
                    "villa volta"
            ));
            add(new Tune("Droomvlucht",
                    "/tunes/droomvlucht.mp3",
                    "droomvlucht"
            ));
        }};

        lobbyController.setTunes(tunes);
        ArrayList<Tune> returnedTunes = lobbyController.getTunes();
        Assertions.assertEquals(tunes, returnedTunes);

    }
    //TODO: SET TUNES


}
