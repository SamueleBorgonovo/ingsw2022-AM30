package it.polimi.ingsw.messages.toClient.InvalidMoveMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;

/**
 * Message to notify that the cloud chosen is invalid
 */
public class InvalidCloudMessage extends MessageToClient {

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
