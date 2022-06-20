package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

/**
 * Message to notify that a player disconnected from the game
 */
public class DisconnectMessage extends MessageToClient{
    private final String nickname;
    private final boolean timeout;
    private final boolean win;
    public DisconnectMessage(String nickname,boolean timeout,boolean win){
        this.nickname=nickname;
        this.timeout=timeout;
        this.win=win;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isTimeout() {
        return timeout;
    }

    public boolean isWin(){return win;}
}
