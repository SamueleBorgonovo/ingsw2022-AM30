package it.polimi.ingsw.messages;

import it.polimi.ingsw.server.ClientHandler;

import java.io.Serializable;

public abstract class Message implements Serializable {
    public void action(String nickname){}
}
