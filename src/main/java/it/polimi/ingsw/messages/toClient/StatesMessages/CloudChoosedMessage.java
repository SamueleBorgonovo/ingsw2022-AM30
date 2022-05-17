package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.model.board.Cloud;

public class CloudChoosedMessage extends MessageToClient {
    private String nickname;
    private int cloudid;
    public CloudChoosedMessage(String nickname,int cloud){
        this.nickname=nickname;
        this.cloudid=cloud;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }

    public int getCloud() {
        return cloudid;
    }
}
