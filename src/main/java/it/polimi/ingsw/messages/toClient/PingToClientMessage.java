package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

public class PingToClientMessage extends MessageToClient{
    private boolean isping;
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
