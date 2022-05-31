package it.polimi.ingsw.client.View.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.InputMismatchException;

public class ConnectController {
    private GUI gui=null;
    @FXML
    public TextField ipWindows;
    @FXML
    public TextField portWindows;
    @FXML
    public Label connectionFailed;

    public void setGui(GUI gui) {
        this.gui = gui;
    }


    public void connect(ActionEvent actionEvent) {
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
