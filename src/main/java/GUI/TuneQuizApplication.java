package GUI;

import Controllers.GameController;
import Controllers.LobbyController;
import Controllers.AccountController;
import Controllers.WebSocketController;
import Models.*;
import com.google.gson.Gson;
import communicatorclient.Communicator;
import communicatorclient.CommunicatorClientWebSocket;
import communicatorclient.CommunicatorMessage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Observable;
import java.util.Observer;

public class TuneQuizApplication extends Application implements Observer {
    AccountController accountController;
    LobbyController lobbyController;
    GameController gameController;
    WebSocketController wsController;
    Stage primaryStage;
    LobbyVBox lobbyVBox;
    GameVBox gameVBox;
    boolean gameStarted = false;
    public Communicator communicator = null;
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
    private final String[] scenes = {
            "login",
            "lobby",
            "game"
    };

    public TuneQuizApplication() throws IOException {
        accountController = new AccountController();
        lobbyController = new LobbyController(accountController);
        gameController  = new GameController();
        communicator = CommunicatorClientWebSocket.getInstance();
        communicator.addObserver(this);
        wsController = WebSocketController.getInstance();
    }


    @Override
    public void start(Stage _primaryStage) throws Exception {
        this.primaryStage = _primaryStage;
        setScene(scenes[0]);
    }

    public void setScene(String Scene) throws MalformedURLException {
        Scene scene;
        switch (Scene){
            case "login" :
                LoginVBox loginVBox = new LoginVBox(accountController, this);
                scene = new Scene(loginVBox.getBox(), 500, 300);
                primaryStage.setScene(scene);
                primaryStage.show();
                break;
            case "lobby" :
                setupWebSocket();
                lobbyVBox = new LobbyVBox(lobbyController, this);
                scene = new Scene(lobbyVBox.getBox(), 500, 300);
                primaryStage.setScene(scene);
                primaryStage.show();
                break;
            case "game" :
                gameVBox = new GameVBox(gameController, this, lobbyController);
                scene = new Scene(gameVBox.getBox(), 500, 300);
                primaryStage.setScene(scene);
                primaryStage.show();

        }
    }
    MediaPlayer player;
    public void playTune(String location) throws MalformedURLException {
        System.out.println("Play tune func");
//        URL resource = getClass().getResource(location);
       // Media media = new Media(resource.toString());
        //System.out.println(media);
        Media media = new Media(new File(location).toURI().toURL().toString());
        player = new MediaPlayer(media);
        Platform.runLater(()->{
            player.play();
        });

    }
    public void stopTune(){
        player.stop();
    }
    public void setupWebSocket(){
        ConnectToServer(accountController.getPlayer());
//        communicator.register(properties[0]);
//        communicator.subscribe(properties[0]);
//        communicator.register(properties[1]);
//        communicator.subscribe(properties[1]);
//        communicator.register(properties[2]);
//        communicator.subscribe(properties[2]);
//        communicator.register(properties[3]);
//        communicator.subscribe(properties[3]);
//        communicator.register(properties[4]);
//        communicator.subscribe(properties[4]);
//        communicator.register(properties[5]);
//        communicator.subscribe(properties[5]);
    }

    @Override
    public void update(Observable o, Object arg) {
        CommunicatorMessage message = (CommunicatorMessage) arg;
        String property = message.getProperty();
        String jsoncontent = message.getContent();

        if (property.equals(properties[4])){
            LobbyWrapper wrapper = gson.fromJson(jsoncontent, LobbyWrapper.class);
            lobbyController.setPlayers(wrapper.getPlayers());
            for (Player player : wrapper.getPlayers()) {
                System.out.println(player.getName());
            }
            Platform.runLater(() -> {
                lobbyVBox.refreshList(lobbyController.GetPlayers());
            });


        }else if (property.equals(properties[6])){
            GameStateWrapper wrapper = gson.fromJson(jsoncontent, GameStateWrapper.class);
            lobbyController.setGameState(wrapper.isGameStarted());
            gameStarted = wrapper.isGameStarted();

        } else if(property.equals(properties[7])){
            TunePackWrapper wrapper = gson.fromJson(jsoncontent, TunePackWrapper.class);
            lobbyController.setTunes(wrapper.getTunes());
            Platform.runLater(() -> {
                try {
                    setScene("game");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            });
        }else if(property.equals(properties[9])){
            LobbyWrapper wrapper = gson.fromJson(jsoncontent, LobbyWrapper.class);
            lobbyController.setPlayers(wrapper.getPlayers());
            Platform.runLater(() -> {
             gameVBox.updateLobby(lobbyController.getPlayers());
            });

        }
    }
    public void ConnectToServer(Player player){
        wsController.ConnectToServer(player);
//        PlayerWrapper wrapper = new PlayerWrapper(properties[0], new Player(player.getName()));
//        communicator.start();
//        CommunicatorMessage message = new CommunicatorMessage();
//        message.setProperty(properties[0]);
//        message.setContent(gson.toJson(wrapper, PlayerWrapper.class));
//        communicator.update(message);
//        System.shared.out.println("CONNECTED");
    }
    public void sendMessage(String property, String Content ){
        CommunicatorMessage message = new CommunicatorMessage();
        message.setProperty(property);
        message.setContent(Content);
        communicator.update(message);
    }
}
