package it.polimi.ingsw.client.View.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NicknameController {
    @FXML
    public TextField nicknameWindows;
    @FXML
    public Label wrongNickname;


    public void showNicknameScene(Stage primaryStage, boolean validNickname, boolean reconnect){
        try {
            FXMLLoader loader = new FXMLLoader(GUI.class.getResource("/Nickname_Dashboard.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setTitle("Eriantys");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e) {

        }
    }

    public void login(ActionEvent actionEvent) {
        String nickname;
        nickname = nicknameWindows.getText();
    }

    public void reconnect(ActionEvent actionEvent) {

    }
}
