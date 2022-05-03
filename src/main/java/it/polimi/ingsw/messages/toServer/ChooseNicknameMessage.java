package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.server.ClientHandlerInterface;

public class ChooseNicknameMessage extends MessageToServer{
    String nickname;
    ChooseNicknameMessage(String nickname){
        this.nickname=nickname;
    }
    public void action(ClientHandlerInterface clientHandler){
        clientHandler.getGameHandler().checkNickname(clientHandler,nickname);
    }
}
