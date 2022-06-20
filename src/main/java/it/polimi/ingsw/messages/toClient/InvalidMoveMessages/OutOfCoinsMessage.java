package it.polimi.ingsw.messages.toClient.InvalidMoveMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;

/**
 * Message to notify that the character can't be played because the player has not enough coins
 */
public class OutOfCoinsMessage extends MessageToClient {

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}