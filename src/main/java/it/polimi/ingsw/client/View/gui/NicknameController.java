package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.messages.toServer.ChooseNicknameMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * NicknameController class controls the scene used to choice the nickname and to
 *  reconnect to a game
 */
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

    /**
     * Method setGui set an instance of the gui
     * @param gui instance of the gui to set
     */
    public void setGui(GUI gui){
        this.gui=gui;
    }

    /**
     * Method login is called after the click of Login button. It sends a message to the server with
     *  the nickname chosen
     */
    public void login() {
        String nickname;
        nickname = nicknameWindows.getText();
        gui.getClient().sendMessage(new ChooseNicknameMessage(nickname,false, newGame));
        gui.setNickname(nickname);
    }

    /**
     * Method reconnect is called after the click of Reconnect button. It sends a message to the server
     *  with the chosen option
     */
    public void reconnect() {
        String nickname = nicknameWindows.getText();
        gui.getClient().sendMessage(new ChooseNicknameMessage(nickname,true, false));
        gui.setNickname(nickname);
        gui.instantiateDashBoardScene();
    }

    /**
     * Method setReconnectButton set the Reconnect button.
     * @param visible if is true set Reconnect button visible, it set Reconnect button not visible otherwise.
     */
    public void setReconnectButton(boolean visible){
        if(visible)
            newGame=true;
        wrongNickname.setVisible(visible);
        wrongNickname.setWrapText(true);
        if(visible)
            wrongNickname.setText("Press 'Reconnect' if you want to reconnect to the game\n" +  "       Press 'Login' if you want to start a new game");
        reconnectButton.setVisible(visible);
    }

    /**
     * Method setWrongNickname set the text in the label wrongNickname
     * @param visible if is true set it visible, it set not visible otherwise
     */
    public void setWrongNickname(boolean visible){
        wrongNickname.setVisible(visible);
    }

    /**
     * Method setLoginButton set the position of the Login button.
     * @param move the position in which the Login button should move to
     */
    public void setLoginButton(double move){
        loginButton.setLayoutX(move);
    }
}
