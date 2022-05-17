package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;

public class CharacterChoosedMessage extends MessageToClient {
    private String nickname;
    private int character;
    public CharacterChoosedMessage(String nickname,int character){
        this.nickname=nickname;
        this.character=character;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
