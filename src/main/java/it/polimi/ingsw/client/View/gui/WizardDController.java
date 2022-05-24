package it.polimi.ingsw.client.View.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WizardDController {
    @FXML
    ImageView wizardGreen;

    @FXML
    ImageView wizardYellow;

    @FXML
    ImageView wizardBlue;

    @FXML
    ImageView wizardPink;

    public void showWizardScene(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("/Wizard_Dashboard.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Eriantys");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void choosenWizardGreen(MouseEvent mouseEvent) {

    }

    public void choosenWizardYellow(MouseEvent mouseEvent) {

    }

    public void choosenWizardBlue(MouseEvent mouseEvent) {

    }

    public void choosenWizardPink(MouseEvent mouseEvent) {

    }

    /*
    public void showWizards(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Wizard_Dashboard.fxml"));
        primaryStage.setTitle("Eriantys");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    */
}
