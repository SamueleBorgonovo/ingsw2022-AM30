package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.messages.toServer.ChooseWizardMessage;
import it.polimi.ingsw.model.player.Wizard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Class WizardController controls the scene used to choice wizard
 */
public class WizardController {
    private GUI gui;

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

    /**
     * Method setWizards sets the list of available wizards received from the server
     * @param wizards list of available wizard received from the server
     */
    public void setWizards(ArrayList<Wizard> wizards) {
        visibleWizards(wizards);
    }

    /**
     * Method setGui sets an instance of the gui
     * @param gui instance of the gui to set
     */
    public void setGui(GUI gui){
        this.gui=gui;
    }

    /**
     * Method setWrongWizard displays the message of wrong wizard chosen
     */
    public void setWrongWizard(){
        wrongWizard.setVisible(true);
    }

    /**
     * Method visibleWizards displays the wizard that the player can choose
     * @param wizards list of wizards to display
     */
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

    /**
     * Method chosenWizardGreen is called when the player choice the Green wizard
     */
    public void chosenWizardGreen() {
        gui.getClient().setWizard(Wizard.WIZARD_GREEN);
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_GREEN);
        gui.getClient().sendMessage(message);
        wizardYellow.setDisable(true);
        wizardBlue.setDisable(true);
        wizardPink.setDisable(true);
    }

    /**
     * Method chosenWizardYellow is called when the player choice the Yellow wizard
     */
    public void chosenWizardYellow() {
        gui.getClient().setWizard(Wizard.WIZARD_YELLOW);
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_YELLOW);
        gui.getClient().sendMessage(message);
        wizardGreen.setDisable(true);
        wizardBlue.setDisable(true);
        wizardPink.setDisable(true);
    }

    /**
     * Method chosenWizardBlue is called when the player choice the Blue wizard
     */
    public void chosenWizardBlue() {
        gui.getClient().setWizard(Wizard.WIZARD_BLUE);
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_BLUE);
        gui.getClient().sendMessage(message);
        wizardYellow.setDisable(true);
        wizardGreen.setDisable(true);
        wizardPink.setDisable(true);
    }

    /**
     * Method chosenWizardPink is called when the player choice the Pink wizard
     */
    public void chosenWizardPink() {
        gui.getClient().setWizard(Wizard.WIZARD_PINK);
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_PINK);
        gui.getClient().sendMessage(message);
        wizardYellow.setDisable(true);
        wizardBlue.setDisable(true);
        wizardGreen.setDisable(true);
    }
}
