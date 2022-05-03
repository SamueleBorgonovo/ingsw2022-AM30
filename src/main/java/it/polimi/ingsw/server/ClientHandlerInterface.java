package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.messages.toServer.MessageToServer;

import java.io.IOException;

public interface ClientHandlerInterface {

    public String getNickname();

    public GameHandler getGameHandler();

    public void sendMessage(MessageToClient message) throws IOException;
}
