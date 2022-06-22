package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.server.ClientHandlerInterface;

/**
 * Message to send the player's nickname choose
 */
public class ChooseNicknameMessage extends MessageToServer{
    String nickname;
    boolean reconnect;
    boolean newGame;

    public ChooseNicknameMessage(String nickname, boolean reconnect, boolean newGame){
        this.nickname=nickname;
        this.reconnect=reconnect;
        this.newGame=newGame;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getMessageHandler().process(this, clientHandler);
    }

    public String getNickname(){return nickname;}

    public boolean getReconnect(){return reconnect;}

    public boolean getNewGame(){return newGame;}
    //if reconnect is true, player is reconnecting
}
