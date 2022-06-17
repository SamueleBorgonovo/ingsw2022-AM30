package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class ChooseCharacterMessage extends MessageToServer {
    CharacterView character;

    public ChooseCharacterMessage(CharacterView character){
        this.character = character;
    }

    public CharacterView getCharacter() {
        return character;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getMessageHandler().process(this, clientHandler);
    }
}
