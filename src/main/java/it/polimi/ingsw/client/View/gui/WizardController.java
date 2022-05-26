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
        if(!wizards.contains(Wizard.WIZARD_GREEN))
            wizardGreen.setVisible(false);
        if(!wizards.contains(Wizard.WIZARD_YELLOW))
            wizardYellow.setVisible(false);
        if(!wizards.contains(Wizard.WIZARD_BLUE))
            wizardBlue.setVisible(false);
        if(!wizards.contains(Wizard.WIZARD_PINK))
            wizardPink.setVisible(false);
    }

    public void choosenWizardGreen(MouseEvent mouseEvent) {
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_GREEN);
        gui.getClient().sendMessage(message);
    }

    public void choosenWizardYellow(MouseEvent mouseEvent) {
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_YELLOW);
        gui.getClient().sendMessage(message);
    }

    public void choosenWizardBlue(MouseEvent mouseEvent) {
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_BLUE);
        gui.getClient().sendMessage(message);
    }

    public void choosenWizardPink(MouseEvent mouseEvent) {
        ChooseWizardMessage message = new ChooseWizardMessage(Wizard.WIZARD_PINK);
        gui.getClient().sendMessage(message);
    }
}
