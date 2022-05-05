package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class ChooseIslandEffectMessage {
    int islandID;

    public ChooseIslandEffectMessage(int islandID){
        this.islandID = islandID;
    }

    public int getIslandID() {
        return islandID;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getController().process(this, clientHandler);
    }
}
