package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.model.board.Characters;

public class CharacterChoosedMessage extends MessageToClient {
    private String nickname;
    private Characters character;
    public CharacterChoosedMessage(String nickname,Characters character){
        this.nickname=nickname;
        this.character=character;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }

    public Characters getCharacter() {
        return character;
    }
}
