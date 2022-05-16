package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

public class ConnectMessage extends MessageToClient{
    private boolean reconnect;

    public ConnectMessage(boolean reconnect){
        this.reconnect=reconnect;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);

    }

    public boolean getReconnect(){
        return reconnect;
    }
}
