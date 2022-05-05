package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

public class InvalidIslandMessage extends MessageToClient{

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
