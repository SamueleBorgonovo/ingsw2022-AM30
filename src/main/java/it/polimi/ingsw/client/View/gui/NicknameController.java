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
    private GUI gui;

    public void setGui(GUI gui){
        this.gui=gui;
    }

    public void login(ActionEvent actionEvent) {
        String nickname;
        nickname = nicknameWindows.getText();
    }

    public void reconnect(ActionEvent actionEvent) {

    }
}
