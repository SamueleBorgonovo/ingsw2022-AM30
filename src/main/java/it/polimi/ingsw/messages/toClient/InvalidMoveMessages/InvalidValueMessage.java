package it.polimi.ingsw.messages.toClient.InvalidMoveMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;

/**
 * Message to notify that the island id where to move MotherNature chosen by the player is invalid
 */
public class InvalidValueMessage extends MessageToClient {

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
