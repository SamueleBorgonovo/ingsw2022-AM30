package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

public class DisconnectMessage extends MessageToClient{
    private String nickname;
    private boolean timeout;
    public DisconnectMessage(String nickname,boolean timeout){
        this.nickname=nickname;
        this.timeout=timeout;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isGameEnded() {
        return timeout;
    }
}
