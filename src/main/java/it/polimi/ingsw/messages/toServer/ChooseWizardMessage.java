package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Wizard;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class ChooseWizardMessage {
    Wizard wizard;

    public ChooseWizardMessage(Wizard wizard) {
        this.wizard = wizard;
    }

    public void action (ClientHandlerInterface clientHandler){
      //  clienthandler.getGameHandler().chooseWiz(clienthandler,assistant);
    }

}
