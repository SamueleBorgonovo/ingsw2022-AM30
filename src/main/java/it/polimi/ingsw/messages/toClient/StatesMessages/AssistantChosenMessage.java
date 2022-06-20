package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.model.player.Assistant;

/**
 * Message to notify that a player chose an assistant
 */
public class AssistantChosenMessage extends MessageToClient {
    private final String nickname;
    private final Assistant assistant;
    public AssistantChosenMessage(String nickname, Assistant assistant){
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
