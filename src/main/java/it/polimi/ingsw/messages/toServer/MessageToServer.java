package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.server.ClientHandlerInterface;

import java.io.Serializable;

public abstract class MessageToServer implements Serializable {

    public void action(ClientHandlerInterface clientHandler) {}
}
