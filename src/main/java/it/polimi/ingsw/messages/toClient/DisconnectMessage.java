package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

public class DisconnectMessage extends MessageToClient{
    private String nickname;
    private boolean gameEnded;
    public DisconnectMessage(String nickname,boolean gameEnded){
        this.nickname=nickname;
        this.gameEnded=gameEnded;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }
}
