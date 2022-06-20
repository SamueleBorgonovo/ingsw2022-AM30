package it.polimi.ingsw.messages.toClient.InvalidMoveMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;

/**
 * Message to notify that there are not enough stops in the character card
 */
public class InvalidStopMessage extends MessageToClient {

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
