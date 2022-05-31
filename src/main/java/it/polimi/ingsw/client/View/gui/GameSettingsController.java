package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.messages.toServer.CreatePlayerInGameMessage;
import it.polimi.ingsw.model.game.GameMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

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

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public void createGame(ActionEvent actionEvent) {
            if ((simpleField.isSelected() && expertField.isSelected()) || twoField.isSelected() && threeField.isSelected()) {
                //messaggio di errore
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
                gui.setGameMode(gameMode);
                gui.setNumOfPlayers(numberOfPlayers);
                gui.getClient().sendMessage(message);
            }
    }
}