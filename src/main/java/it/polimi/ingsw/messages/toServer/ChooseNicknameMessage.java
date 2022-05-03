package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.server.ClientHandlerInterface;

import java.io.IOException;

public class ChooseNicknameMessage extends MessageToServer{
    String nickname;
    public ChooseNicknameMessage(String nickname){
        this.nickname=nickname;
    }
    public void action(ClientHandlerInterface clientHandler) throws IOException {
        clientHandler.getGameHandler().checkNickname(clientHandler,nickname);
    }
}
