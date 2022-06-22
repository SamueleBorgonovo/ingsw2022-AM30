package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

import java.io.Serializable;

/**
 * Abstract class used to handle messages to client
 */
public abstract class MessageToClient implements Serializable {
    public void action(Client client){}
}
