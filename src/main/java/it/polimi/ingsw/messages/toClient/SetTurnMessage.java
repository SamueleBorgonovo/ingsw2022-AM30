package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

public class SetTurnMessage extends MessageToClient{
    private String nickname;

    public SetTurnMessage(String nickname){
        this.nickname=nickname;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }
}
