package it.polimi.ingsw.client.View.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

    public void showNicknameScene(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("/Nickname_Dashboard.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Eriantys");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String login(ActionEvent actionEvent) {
        return nicknameWindows.toString();
    }
}
