package it.polimi.ingsw.client.View.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class NicknameController {
    @FXML
    public TextField nicknameWindows;
    @FXML
    public Label wrongNickname;
    @FXML
    public Pane reconnectPane;
    @FXML
    public CheckBox reconnectField;
    @FXML
    public Button loginButton;

    public void login(ActionEvent actionEvent) {

    }
}
