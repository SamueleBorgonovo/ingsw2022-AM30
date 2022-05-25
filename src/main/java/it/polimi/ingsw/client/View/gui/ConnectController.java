package it.polimi.ingsw.client.View.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnectController {
    private GUI gui;
    @FXML
    public TextField ipWindows;
    @FXML
    public TextField portWindows;
    @FXML
    public Label connectionFailed;

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public void showConnectScene(Stage primaryStage){
        try {
            FXMLLoader loader = new FXMLLoader(GUI.class.getResource("/Connect_Dashboard.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setTitle("Eriantys");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e) {

        }
    }
    public void connect(ActionEvent actionEvent) {
        if(!gui.createClient(ipWindows.getText(), Integer.parseInt(portWindows.getText())))
            connectionFailed.setVisible(true);
    }
}
