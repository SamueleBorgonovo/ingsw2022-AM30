package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.model.board.Cloud;

public class CloudChoosedMessage extends MessageToClient {
    private String nickname;
    private int cloud;
    public CloudChoosedMessage(String nickname,int cloud){
        this.nickname=nickname;
        this.cloud=cloud;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
