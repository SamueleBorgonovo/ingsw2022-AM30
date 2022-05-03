package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.exceptions.InvalidTurnException;
import it.polimi.ingsw.exceptions.WrongAssistantException;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class ChooseAssistantMessage extends MessageToServer {
    Assistant assistant;
    public void ChooseAssistantMessage(Assistant assistant){
        this.assistant=assistant;
    }

    public void action(ClientHandlerInterface clienthandler) throws InvalidTurnException, WrongAssistantException {
        clienthandler.getGameHandler().chooseAssistant(clienthandler,assistant);
    }


}
