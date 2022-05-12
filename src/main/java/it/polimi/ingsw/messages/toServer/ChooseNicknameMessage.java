package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.server.ClientHandlerInterface;

public class ChooseNicknameMessage extends MessageToServer{
    String nickname;
    boolean reconnect;

    public ChooseNicknameMessage(String nickname, boolean reconnect){
        this.nickname=nickname;
        this.reconnect=reconnect;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getController().process(this, clientHandler);
    }

    public String getNickname(){return nickname;}

    public boolean getReconnect(){return reconnect;}
    //if reconnect is true, player is reconnecting
}
