package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.messages.toServer.ChooseNicknameMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NicknameController {
    @FXML
    private TextField nicknameWindows;
    @FXML
    private Label wrongNickname;

    @FXML
    private Button loginButton;

    @FXML
    private Button reconnectButton;

    private GUI gui;
    private boolean newGame=false;

    public void setGui(GUI gui){
        this.gui=gui;
    }

    public void login(ActionEvent actionEvent) {
        String nickname;
        nickname = nicknameWindows.getText();
        gui.getClient().sendMessage(new ChooseNicknameMessage(nickname,false, newGame));
        gui.setNickname(nickname);
    }

    public void reconnect(ActionEvent actionEvent) {
        String nickname = nicknameWindows.getText();
        gui.getClient().sendMessage(new ChooseNicknameMessage(nickname,true, false));
        gui.setNickname(nickname);
        gui.instantiateDashBoardScene();
    }


    public void setReconnectButton(boolean visible){
        if(visible)
            newGame=true;
        wrongNickname.setVisible(visible);
        wrongNickname.setWrapText(true);
        if(visible)
            wrongNickname.setText("Press 'Reconnect' if you want to reconnect to the game\n" +  "       Press 'Login' if you want to start a new game");
        reconnectButton.setVisible(visible);
    }

    public void setWrongNickname(boolean visible){
        wrongNickname.setVisible(visible);
    }

    public void setLoginButton(double move){
        loginButton.setLayoutX(move);
    }
}
