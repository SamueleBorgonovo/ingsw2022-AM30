package it.polimi.ingsw.client.View.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.InputMismatchException;

/**
 * Class ConnectController controls the scene used to set the settings to connect to the server
 */
public class ConnectController {
    private GUI gui=null;
    @FXML
    public TextField ipWindows;
    @FXML
    public TextField portWindows;
    @FXML
    public Label connectionFailed;

    /**
     * Method setGui sets an instance of the gui
     * @param gui the instance of the gui to set
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     *Method connect is called when the player clicks the Connect Button. It tries to connect
     * to the server, if the connection is accepted it calls the method to upload the next scene.
     */
    public void connect() {
        int a;
            try{
                a = Integer.parseInt(portWindows.getText());
                if(!gui.createClient(ipWindows.getText(), a))
                    connectionFailed.setVisible(true);
                else{
                    gui.instantiateNicknameScene();
                }
            }catch (NumberFormatException | InputMismatchException e) {
                connectionFailed.setVisible(true);
            }

    }
}
