package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.messages.toServer.MessageToServer;

import java.io.IOException;

public interface ClientHandlerInterface {

    public String getNickname();

    public void sendMessage(MessageToServer messageToServer) throws IOException;

    public GameHandler getGameHandler();
}
