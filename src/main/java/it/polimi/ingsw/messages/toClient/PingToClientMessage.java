package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

/**
 * Message of ping to the client
 */
public class PingToClientMessage extends MessageToClient{
    private final boolean isping;
    //if isping is false it's a "pong" message

    public PingToClientMessage(boolean isping){
        this.isping=isping;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public boolean isPing(){
        return isping;
    }
}
