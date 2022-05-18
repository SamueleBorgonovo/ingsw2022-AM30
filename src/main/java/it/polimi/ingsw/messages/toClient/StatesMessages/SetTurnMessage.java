package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;

public class SetTurnMessage extends MessageToClient {
    private String nickname;
    private boolean assistantPhase;

    public SetTurnMessage(String nickname, boolean assistantPhase){
        this.nickname=nickname;
        this.assistantPhase=assistantPhase;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isassistantPhase(){return assistantPhase;}
}
