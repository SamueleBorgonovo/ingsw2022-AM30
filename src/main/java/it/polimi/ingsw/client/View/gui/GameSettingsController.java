package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.messages.toServer.CreatePlayerInGameMessage;
import it.polimi.ingsw.model.game.GameMode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * Class GameSettingsController controls the scene used to choice the settings of the game
 */
public class GameSettingsController {

    private GUI gui;
    private GameMode gameMode;
    private int numberOfPlayers;

    @FXML
    public CheckBox simpleField;

    @FXML
    public Button gameSettingsButton;

    @FXML
    public CheckBox twoField;

    @FXML
    public CheckBox expertField;

    @FXML
    public CheckBox threeField;

    @FXML
    public Label wrongGameSettings;

    /**
     * Method setGui sets an instance of the gui
     * @param gui instance of the gui to set
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     *Method createGame is called when the player clicks the Create button. It checks the selected field and sends the
     * message with settings to the server.
     */
    public void createGame() {
            if ((simpleField.isSelected() && expertField.isSelected()) || twoField.isSelected() && threeField.isSelected()) {
                simpleField.setSelected(false);
                expertField.setSelected(false);
                twoField.setSelected(false);
                threeField.setSelected(false);
            } else {
                if (simpleField.isSelected() && !expertField.isSelected()) {
                    this.gameMode = GameMode.SIMPLEMODE;
                } else if (expertField.isSelected() && !simpleField.isSelected()) {
                    this.gameMode = GameMode.EXPERTMODE;
                }

                if (twoField.isSelected() && !threeField.isSelected()) {
                    this.numberOfPlayers = 2;
                } else if (threeField.isSelected() && !twoField.isSelected()) {
                    this.numberOfPlayers = 3;
                }
            }

            if(this.gameMode!=null && this.numberOfPlayers!=0) {
                CreatePlayerInGameMessage message = new CreatePlayerInGameMessage(gui.getNickname(),gameMode,numberOfPlayers);
                gui.getClient().setGamemode(gameMode);
                gui.getClient().setNumofPlayers(numberOfPlayers);
                gui.getClient().sendMessage(message);
            }
            else {
                wrongGameSettings.setText("You must select one option for each question");
                wrongGameSettings.setVisible(true);
            }
    }
}