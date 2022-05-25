package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.model.player.Wizard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class WizardDController {
    @FXML
    ImageView wizardGreen;

    @FXML
    ImageView wizardYellow;

    @FXML
    ImageView wizardBlue;

    @FXML
    ImageView wizardPink;

    public void choosenWizardGreen(MouseEvent mouseEvent) {

    }

    public void choosenWizardYellow(MouseEvent mouseEvent) {

    }

    public void choosenWizardBlue(MouseEvent mouseEvent) {

    }

    public void choosenWizardPink(MouseEvent mouseEvent) {

    }

    public void showWizardScene(Stage primaryStage, ArrayList<Wizard> availableWizards){
        try {
            FXMLLoader loader = new FXMLLoader(GUI.class.getResource("/Wizard_Dashboard.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setTitle("Eriantys");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e) {

        }
    }
}
