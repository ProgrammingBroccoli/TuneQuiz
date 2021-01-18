package GUI;

import Controllers.AccountController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.MalformedURLException;

public class LoginVBox {
    AccountController accountController;
    TuneQuizApplication tuneQuizApplication;
    public LoginVBox(AccountController accountController, TuneQuizApplication tuneQuizApplication){
        this.accountController = accountController;
        this.tuneQuizApplication = tuneQuizApplication;
    }
    public VBox getBox(){
        VBox returningBox = new VBox();
        Label lblTitle = new Label("Tune Quiz");

        TextField txtFieldUsername = new TextField();
        Label lblUsername = new Label("Username");

        PasswordField passwordField = new PasswordField();
        Label lblPassword = new Label("Password");

        Button button = new Button("Login");



        lblTitle.setLayoutX(250);
        lblTitle.setLayoutY(100);
        lblTitle.setTextFill(Color.web("#03dac6"));
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 24; -fx-padding: 20;");

//        lblUsername.setLayoutX(250);
//        lblUsername.setLayoutY(120);
        lblUsername.setTextFill(Color.web("#bb86fc"));
        lblUsername.setStyle("-fx-font-weight: bold;");

        txtFieldUsername.setMaxWidth(120);

        lblPassword.setTextFill(Color.web("#bb86fc"));
        lblPassword.setStyle("-fx-font-weight: bold;");

        passwordField.setMaxWidth(120);
        passwordField.setStyle("-fx-start-margin: 0, 50, 0, 0");

        button.setOnAction(
                (EventHandler)(actionEvent -> {
                    boolean result = false;
                    try {
                        result = accountController.LoginPlayer(txtFieldUsername.getText(), passwordField.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (result){
                        try {
                            tuneQuizApplication.setScene("lobby");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                })
        );

        returningBox.setBackground(new Background(new BackgroundFill(Color.web("121212"), null, null)));
        returningBox.getChildren().add(lblTitle);
        returningBox.getChildren().add(lblUsername);
        returningBox.getChildren().add(txtFieldUsername);
        returningBox.getChildren().add(lblPassword);
        returningBox.getChildren().add(passwordField);
        returningBox.getChildren().add(button);
        returningBox.setAlignment(Pos.CENTER);
        return returningBox;
    }

    public void update(String txt){
        System.out.println(txt);

    }
}
