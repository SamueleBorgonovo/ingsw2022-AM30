package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.MessageHandler;
import it.polimi.ingsw.messages.toClient.MessageToClient;


public interface ClientHandlerInterface {

    public String getNickname();

    public MessageHandler getController();

    public void sendMessageToClient(MessageToClient message);

    public void stopTimer();

    public void setNickname(String nickname);
}
