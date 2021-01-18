package sample;

import Controllers.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    public Main() {

    }


    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/java  /sample/loginPage.fxml"));
//        Parent root = loader.load();
//        LoginController loginController = loader.<LoginController>getController();
        URL url = new File("src/main/java/sample/loginPage.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        double width = 600;
        double height = 400;
        final Scene scene = new Scene( root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
        ScreenController controller = ScreenController.getInstance();
        controller.setup();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
