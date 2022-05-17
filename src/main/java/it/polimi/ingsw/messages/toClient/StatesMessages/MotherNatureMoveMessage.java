package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;

public class MotherNatureMoveMessage extends MessageToClient {
    private String nickname;
    private int islandId;
    public MotherNatureMoveMessage(String nickname,int islandId){
        this.nickname=nickname;
        this.islandId=islandId;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }

    public int getIslandId() {
        return islandId;
    }
}
