package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.exceptions.InvalidTurnException;
import it.polimi.ingsw.exceptions.WrongAssistantException;
import it.polimi.ingsw.server.ClientHandler;

import java.io.Serializable;

public abstract class MessageToServer implements Serializable {
    public void action(ClientHandler clientHandler) throws WrongAssistantException, InvalidTurnException {}
}
