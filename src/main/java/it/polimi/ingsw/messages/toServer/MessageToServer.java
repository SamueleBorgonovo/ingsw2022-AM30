package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.server.ClientHandlerInterface;

import java.io.Serializable;

/**
 * Abstract class used to handle messages to server
 */
public abstract class MessageToServer implements Serializable {

    public void action(ClientHandlerInterface clientHandler) {}
}
