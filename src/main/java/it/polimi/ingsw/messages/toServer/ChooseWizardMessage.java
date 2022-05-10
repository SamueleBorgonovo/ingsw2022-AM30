package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.model.player.Wizard;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class ChooseWizardMessage extends MessageToServer {
    Wizard wizard;

    public ChooseWizardMessage(Wizard wizard) {
        this.wizard = wizard;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getController().process(this, clientHandler);
    }

}
