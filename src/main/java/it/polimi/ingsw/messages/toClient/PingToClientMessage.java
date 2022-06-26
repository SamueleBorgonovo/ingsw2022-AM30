package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

/**
 * Message of ping to the client
 */
public class PingToClientMessage extends MessageToClient{
    private final boolean isPing;
    //if isPing is false it's a "pong" message

    public PingToClientMessage(boolean isPing){
        this.isPing=isPing;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public boolean isPing(){
        return isPing;
    }
}
