package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.messages.toClient.MessageToClient;

/**
 * Message to notify that a player chose a character
 */
public class CharacterChosenMessage extends MessageToClient {
    private final String nickname;
    private final CharacterView character;
    public CharacterChosenMessage(String nickname, CharacterView character){
        this.nickname=nickname;
        this.character=character;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }

    public CharacterView getCharacter() {
        return character;
    }
}
