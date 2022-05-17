package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.model.player.Assistant;

public class AssistantChoosedMessage extends MessageToClient {
    private String nickname;
    private Assistant assistant;
    public AssistantChoosedMessage(String nickname, Assistant assistant){
        this.nickname=nickname;
        this.assistant=assistant;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }

    public Assistant getAssistant() {
        return assistant;
    }
}
