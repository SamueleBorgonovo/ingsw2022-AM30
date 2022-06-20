package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

/**
 * Message to notify the correct connection to the game
 */
public class CorrectlyConnectedMessage extends MessageToClient{

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
