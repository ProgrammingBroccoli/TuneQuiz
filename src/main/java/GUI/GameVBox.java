package GUI;

import Controllers.GameController;
import Controllers.LobbyController;
import Models.*;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.net.MalformedURLException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.concurrent.atomic.LongAccumulator;

public class GameVBox {
    GameController gameController;
    LobbyController lobbyController;
    TuneQuizApplication tuneQuizApplication;
    ArrayList<Tune> tunes;
    Label lblP1;
    Label lblP2;
    Label lblP3;

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
    public GameVBox(GameController gameController, TuneQuizApplication tuneQuizApplication, LobbyController lobbyController){
        this.gameController = gameController;
        this.tuneQuizApplication = tuneQuizApplication;
        this.lobbyController = lobbyController;
        tunes = lobbyController.getTunes();
    }

    public VBox getBox() throws MalformedURLException {
        tuneQuizApplication.playTune(tunes.get(0).getLocation());
        ArrayList<Player> players = lobbyController.getPlayers();
        //lobbyController.JoinLobby();
        VBox returningBox = new VBox();
        Label lblTitle = new Label("What is this tune called?");
        Label test = new Label(tunes.get(0).getName());
        lblP1 = new Label();
        lblP2 = new Label();
        lblP3 = new Label();
        for (int i = 0; i < players.size(); i++){
            Player player = players.get(i);
            switch (i){
                case 0 : lblP1.setText(player.getName() + " : " + player.getPoints() + " points");
                break;
                case 1 : lblP2.setText(player.getName() + " : " + player.getPoints() + " points");
                break;
                case 2 : lblP3.setText(player.getName() + " : " + player.getPoints() + " points");
            }
        }
        TextField textField = new TextField();
        Button button = new Button("Submit answer");

        button.setOnAction(
                (EventHandler)(actionEvent -> {
                    String answer = textField.getText();
                    if(checkAnswer(answer)){
                        lblTitle.setText("Indeed! " + tunes.get(0).getName());
                        AnswerWrapper wrapper = new AnswerWrapper();
                        wrapper.setUsername(lobbyController.getUsername());
                        tuneQuizApplication.sendMessage(properties[8], gson.toJson(wrapper));
                        tuneQuizApplication.stopTune();
                        button.setDisable(true);
                    }
                    else{
                        lblTitle.setText("Wrong!");
                    }
                })
        );

//        lblTitle.setLayoutX(250);
//        lblTitle.setLayoutY(100);
        lblTitle.setTextFill(Color.web("#03dac6"));
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 24; -fx-padding: 20, 0, 0, 0");

        lblP1.setTextFill(Color.web("#BB86FC"));
        lblP2.setTextFill(Color.web("#BB86FC"));
        lblP3.setTextFill(Color.web("#BB86FC"));

        textField.setMaxWidth(300);


        returningBox.getChildren().add(lblP1);
        returningBox.getChildren().add(lblP2);
        returningBox.getChildren().add(lblP3);
        returningBox.getChildren().add(lblTitle);
        returningBox.getChildren().add(textField);
        returningBox.getChildren().add(button);
        returningBox.getChildren().add(test);

        returningBox.setBackground(new Background(new BackgroundFill(Color.web("121212"), null, null)));
        returningBox.setAlignment(Pos.CENTER);
        return returningBox;
    }

    private boolean checkAnswer(String answer) {
        if(answer.equals(tunes.get(0).getName())){
            return true;
        }return false;

    }

    public void updateLobby(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++){
            Player player = players.get(i);
            switch (i){
                case 0 : lblP1.setText(player.getName() + " : " + player.getPoints() + " points");
                    break;
                case 1 : lblP2.setText(player.getName() + " : " + player.getPoints() + " points");
                    break;
                case 2 : lblP3.setText(player.getName() + " : " + player.getPoints() + " points");
            }
        }
    }
}
