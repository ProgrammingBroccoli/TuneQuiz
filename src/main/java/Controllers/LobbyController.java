package Controllers;

import Data.LobbyData;
import Models.PackWrapper;
import Models.Player;
import Models.PlayerWrapper;
import Models.Tune;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class LobbyController{
    WebSocketController wsController;
    //ScreenController screenController;
    AccountController accountController;
    LobbyData lobbyData;
    Gson gson = new Gson();
    private final String[] packs = {
            "Efteling",
            "Top40"
    };
    private final String[] properties = {
            "register",
            "test",
            "login",
            "joinLobby",
            "showLobby",
            "updatePack"
    };

    public LobbyController(AccountController accountController) throws IOException {
       lobbyData = LobbyData.getInstance();
       wsController = WebSocketController.getInstance();
       //screenController = ScreenController.getInstance();
       this.accountController = accountController;
    }


    public void JoinLobby(){
        Player player = accountController.getPlayer();
        PlayerWrapper wrapper = new PlayerWrapper(properties[3], player);
        wsController.sendMessage(properties[3], gson.toJson(wrapper, PlayerWrapper.class));
    }
    public ArrayList<Player> GetPlayers() {
       return lobbyData.getPlayers();
    }

    public void SelectPack(String pack){
        if ("Efteling".equals(pack)) {
            lobbyData.setPack(pack);
            PackWrapper wrapper = new PackWrapper("Efteling");
            wsController.sendMessage(properties[5], gson.toJson(wrapper, PackWrapper.class));
        }



    }

    public void setPlayers(ArrayList<Player> players) {
        lobbyData.setPlayers(players);
    }

    public void setGameState(boolean gameStarted) {
        lobbyData.setGameState(gameStarted);
    }

    public void setTunes(ArrayList<Tune> tunes) {
        lobbyData.setTunes(tunes);
    }

    public ArrayList<Tune> getTunes() {
        return lobbyData.getTunes();
    }

    public String getUsername() {
        return lobbyData.getUsername();
    }

    public ArrayList<Player> getPlayers() {
        return lobbyData.getPlayers();
    }
}
