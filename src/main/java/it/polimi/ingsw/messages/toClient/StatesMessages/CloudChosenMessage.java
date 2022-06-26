package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;

/**
 * Message to notify that a player chose a cloud
 */
public class CloudChosenMessage extends MessageToClient {
    private final String nickname;
    private final int cloudID;
    public CloudChosenMessage(String nickname, int cloud){
        this.nickname=nickname;
        this.cloudID =cloud;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }

    public int getCloud() {
        return cloudID;
    }
}
