package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.messages.toServer.ChooseWizardMessage;
import it.polimi.ingsw.model.player.Wizard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class WizardController {
    private GUI gui;

    private ArrayList<Wizard> wizards;

    @FXML
    public Label wrongWizard;

    @FXML
    ImageView wizardGreen;

    @FXML
    ImageView wizardYellow;

    @FXML
    ImageView wizardBlue;

    @FXML
    ImageView wizardPink;


    public void setWizards(ArrayList<Wizard> wizards) {
        this.wizards = wizards;
        visibleWizards(wizards);
    }

    public void setGui(GUI gui){
        this.gui=gui;
    }

    public void setWrongWizard(){
        wrongWizard.setVisible(true);
    }

    public void visibleWizards(ArrayList<Wizard> wizards){
        if(!wizards.contains(Wizard.WIZARD_GREEN)){
            wizardGreen.setDisable(true);
            wizardGreen.setOpacity(0.3);
        }
        if(!wizards.contains(Wizard.WIZARD_YELLOW)) {
            wizardYellow.setDisable(true);
            wizardYellow.setOpacity(0.3);
        }
        if(!wizards.contains(Wizard.WIZARD_BLUE)) {
            wizardBlue.setDisable(true);
            wizardBlue.setOpacity(0.3);
        }
        if(!wizards.contains(Wizard.WIZARD_PINK)) {
            wizardPink.setDisable(true);
            wizardPink.setOpacity(0.3);
        }
    }

    public void choosenWizardGreen(MouseEvent mouseEvent) {
        gui.getClient().setWizard(Wizard.WIZARD_GREEN);
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_GREEN);
        gui.getClient().sendMessage(message);
        wizardYellow.setDisable(true);
        wizardBlue.setDisable(true);
        wizardPink.setDisable(true);
    }

    public void choosenWizardYellow(MouseEvent mouseEvent) {
        gui.getClient().setWizard(Wizard.WIZARD_YELLOW);
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_YELLOW);
        gui.getClient().sendMessage(message);
        wizardGreen.setDisable(true);
        wizardBlue.setDisable(true);
        wizardPink.setDisable(true);
    }

    public void choosenWizardBlue(MouseEvent mouseEvent) {
        gui.getClient().setWizard(Wizard.WIZARD_BLUE);
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_BLUE);
        gui.getClient().sendMessage(message);
        wizardYellow.setDisable(true);
        wizardGreen.setDisable(true);
        wizardPink.setDisable(true);
    }

    public void choosenWizardPink(MouseEvent mouseEvent) {
        gui.getClient().setWizard(Wizard.WIZARD_PINK);
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_PINK);
        gui.getClient().sendMessage(message);
        wizardYellow.setDisable(true);
        wizardBlue.setDisable(true);
        wizardGreen.setDisable(true);
    }
}
