package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.server.ClientHandlerInterface;

/**
 * Message to send the player's assistant choose
 */
public class ChooseAssistantMessage extends MessageToServer {
    Assistant assistant;

    public ChooseAssistantMessage(Assistant assistant){
        this.assistant=assistant;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getMessageHandler().process(this, clientHandler);
    }


}
