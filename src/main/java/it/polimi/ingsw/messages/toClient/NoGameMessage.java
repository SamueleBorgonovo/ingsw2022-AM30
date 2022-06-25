package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

/**
 * Message to notify that the player can't reconnect to the gam anymore
 */
public class NoGameMessage extends MessageToClient{

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
