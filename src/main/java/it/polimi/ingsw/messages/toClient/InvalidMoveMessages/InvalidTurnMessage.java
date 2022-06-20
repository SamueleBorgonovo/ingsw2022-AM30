package it.polimi.ingsw.messages.toClient.InvalidMoveMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;

/**
 * Message to notify that the action chosen is not possible because is not the player's turn
 */
public class InvalidTurnMessage extends MessageToClient {

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
