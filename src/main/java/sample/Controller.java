package sample;

import javafx.fxml.FXML;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class Controller {
    @FXML
    private Button closeButton;

    @FXML
    void handleCloseButtonAction(ActionEvent event) {

    }

    @FXML
    void onMouseClickedCancelBtn(MouseEvent event) {

    }
    public void ConnectToServer(){
        System.out.println("CONNECTED");
    }
}
