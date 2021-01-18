package GUI;

import Controllers.LobbyController;
import Models.GameStateWrapper;
import Models.Player;
import Models.PlayerWrapper;
import Models.TunePackWrapper;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class LobbyVBox {
    LobbyController lobbyController;
    TuneQuizApplication tuneQuizApplication;
    ListView listPlayers;
    Gson gson = new Gson();
    private final String[] properties = {
            "register",
            "test",
            "login",
            "joinLobby",
            "showLobby",
            "updatePack",
            "gameStateUpdate",
            "getTunes",
            "rightAnswer",
            "pointsUpdate"
    };
    public LobbyVBox(LobbyController lobbyController, TuneQuizApplication tuneQuizApplication){
        this.lobbyController = lobbyController;
        this.tuneQuizApplication = tuneQuizApplication;
    }

    public VBox getBox(){
        //lobbyController.JoinLobby();
        VBox returningBox = new VBox();
        Label lblTitle = new Label("Lobby");
        listPlayers = new ListView();
        Button btnStart = new Button("Start game");

//        lblTitle.setLayoutX(250);
//        lblTitle.setLayoutY(100);
        lblTitle.setTextFill(Color.web("#03dac6"));
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 24; -fx-padding: 20, 0, 0, 0");

//        listPlayers.setLayoutX(250);
//        listPlayers.setLayoutY(20);
        listPlayers.setMaxHeight(150);
        listPlayers.setMaxWidth(200);

        btnStart.setOnAction(
                (EventHandler)(actionEvent -> {
                    GameStateWrapper wrapper = new GameStateWrapper(true);
                    TunePackWrapper packWrapper = new TunePackWrapper();
                    packWrapper.setPackName("efteling");
                    tuneQuizApplication.sendMessage(properties[6], gson.toJson(wrapper));
                    tuneQuizApplication.sendMessage(properties[7], gson.toJson(packWrapper));
                })
        );

        returningBox.getChildren().add(lblTitle);
        returningBox.getChildren().add(listPlayers);
        returningBox.getChildren().add(btnStart);

        returningBox.setBackground(new Background(new BackgroundFill(Color.web("121212"), null, null)));
        returningBox.setAlignment(Pos.CENTER);
        return returningBox;
    }
    public void refreshList(ArrayList<Player> players){
        listPlayers.getItems().clear();
        System.out.println(players);

        Platform.runLater(()->{
            for (Player player: players) {

                listPlayers.getItems().add(player.getName());
            }
        });
    }
}
