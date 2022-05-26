package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class GameSettingsController {
    @FXML
    public CheckBox simpleField;
    @FXML
    public Button gameSettingsButton;
    @FXML
    public CheckBox twoField;

    private GUI gui;


    public void setGui(GUI gui){
        this.gui=gui;
    }

    public void createGame(ActionEvent actionEvent) {

    }
}
