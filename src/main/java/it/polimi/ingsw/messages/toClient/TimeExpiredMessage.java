package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

/**
 * Message to notify that the timer of reconnection of other players is expired
 */
public class TimeExpiredMessage extends MessageToClient{

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
