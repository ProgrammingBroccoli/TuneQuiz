package Controllers;

import Data.LobbyData;
import Data.PlayerData;
import Models.Player;
import Models.plainPlayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class AccountController {
    PlayerData playerData;
    //WebSocketController wsController;
    LobbyData lobbyData = LobbyData.getInstance();
    Gson gson = new Gson();


    private final String[] properties = {
            "register",
            "test",
            "login",
            "joinLobby"
    };

    public AccountController() throws IOException {
        //this.wsController = WebSocketController.getInstance();
        playerData = new PlayerData();
    }

    public boolean LoginPlayer(String Username, String Password) throws IOException {
        String params = "?username=" + Username + "&password=" + Password;
        String urlString = "http://localhost:8080/Login";

        URL url = new URL(urlString + params);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        plainPlayer plainPlayer  = mapper.readValue(responseStream, plainPlayer.class);

        if(plainPlayer.getPassword().equals(Password)){
            System.out.println("Resposne : "+plainPlayer.getUsername() + " " + plainPlayer.getPassword());
            Player player = new Player(plainPlayer.getUsername());
            playerData.setPlayer(player);
            lobbyData.setUsername(player.getName());
            return true;
        }else{return false;}

//        try {
//            URL obj = new URL("http://localhost:8080/Login");
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("User-Agent", "Mozilla/5.0");
//            int responseCode = con.getResponseCode();
//            System.shared.out.println("GET Response Code :: " + responseCode);
//            if(responseCode == HttpURLConnection.HTTP_OK){
//                //succes
//                BufferedReader shared.out.in = new BufferedReader(new InputStreamReader(
//                        con.getInputStream()));
//                String inputline;
//                StringBuffer response = new StringBuffer();
//                while ((inputline = shared.out.in.readLine())!=null){
//                    response.append(inputline);
//                }
//                shared.out.in.close();
//                //Print result
//                System.shared.out.println(response.toString());
//            } else{
//                System.shared.out.println("Get not worked");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //wsController.ConnectToServer(plainPlayer);
    }

    public Player getPlayer() {
        return playerData.getPlayer();
    }
}
