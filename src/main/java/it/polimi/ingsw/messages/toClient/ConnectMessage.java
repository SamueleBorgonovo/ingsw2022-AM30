package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

public class ConnectMessage extends MessageToClient{
    private boolean reconnect;
    private String nickname;

    public ConnectMessage(String nickname,boolean reconnect){
        this.reconnect=reconnect;
        this.nickname=nickname;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);

    }

    public String getNickname() {
        return nickname;
    }

    public boolean getReconnect(){
        return reconnect;
    }
}
