package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.messages.toServer.ChooseWizardMessage;
import it.polimi.ingsw.model.player.Wizard;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class WizardController {
    private ArrayList<Wizard> wizards;
    @FXML
    ImageView wizardGreen;

    @FXML
    ImageView wizardYellow;

    @FXML
    ImageView wizardBlue;

    @FXML
    ImageView wizardPink;

    private GUI gui;

    public void setWizards(ArrayList<Wizard> wizards) {
        this.wizards = wizards;
        visibleWizards(wizards);
    }

    public void setGui(GUI gui){
        this.gui=gui;
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
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_GREEN);
        gui.getClient().sendMessage(message);
        wizardYellow.setDisable(true);
        wizardBlue.setDisable(true);
        wizardPink.setDisable(true);
    }

    public void choosenWizardYellow(MouseEvent mouseEvent) {
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_YELLOW);
        gui.getClient().sendMessage(message);
        wizardGreen.setDisable(true);
        wizardBlue.setDisable(true);
        wizardPink.setDisable(true);
    }

    public void choosenWizardBlue(MouseEvent mouseEvent) {
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_BLUE);
        gui.getClient().sendMessage(message);
        wizardYellow.setDisable(true);
        wizardGreen.setDisable(true);
        wizardPink.setDisable(true);
    }

    public void choosenWizardPink(MouseEvent mouseEvent) {
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_PINK);
        gui.getClient().sendMessage(message);
        wizardYellow.setDisable(true);
        wizardBlue.setDisable(true);
        wizardGreen.setDisable(true);
    }
}
