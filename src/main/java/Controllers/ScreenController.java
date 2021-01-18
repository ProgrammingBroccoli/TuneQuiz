package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ScreenController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private static ScreenController instance;


    public ScreenController() throws IOException {
//        URL url = new File("src/main/resources/HomeScreen.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        addScreen("Home", FXMLLoader.load(url));
    }

    public void setup() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/HomeScreen.fxml"));
//        LobbyController controller = loader.getController();
//        WebSocketController wsController = WebSocketController.getInstance(controller);

        URL url = new File("src/main/resources/HomeScreen.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        addScreen("Home", FXMLLoader.load(url));


//        URL url = new File("src/main/resources/HomeScreen.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        addScreen("Home", FXMLLoader.load(url));
    }

    public void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    public void activate(String name, javafx.event.ActionEvent actionEvent){
        Parent NewScreenParent = screenMap.get(name);
        Scene NewScreenScene = new Scene(NewScreenParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(NewScreenScene);
        window.show();
    }
    public static ScreenController getInstance() throws IOException {
        if (instance == null){
            instance = new ScreenController();

        }
        return instance;
    }
}