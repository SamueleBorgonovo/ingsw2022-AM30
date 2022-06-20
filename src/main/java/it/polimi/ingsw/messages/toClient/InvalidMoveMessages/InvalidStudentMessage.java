package it.polimi.ingsw.messages.toClient.InvalidMoveMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;

/**
 * Message to notify that the student chosen is invalid
 */
public class InvalidStudentMessage extends MessageToClient {

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
