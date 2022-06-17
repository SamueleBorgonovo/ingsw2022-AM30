package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.MessageHandler;
import it.polimi.ingsw.messages.toClient.MessageToClient;


public interface ClientHandlerInterface {

    String getNickname();

    MessageHandler getMessageHandler();

    void sendMessageToClient(MessageToClient message);

    void stopTimer();

    void setNickname(String nickname);

    void handleSocketDisconnection(boolean timeout,boolean gameEnded);

    void closePinger();
}
