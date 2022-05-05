package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.MessageHandler;
import it.polimi.ingsw.messages.toClient.MessageToClient;

import java.io.IOException;

public interface ClientHandlerInterface {

    public String getNickname();

    public MessageHandler getController();

    public void sendMessageToClient(MessageToClient message) throws IOException;
}
