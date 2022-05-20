package it.polimi.ingsw.messages.toClient.InvalidMoveMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;

public class InvalidStopMessage extends MessageToClient {

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
