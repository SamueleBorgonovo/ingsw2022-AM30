package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class ChooseCharacterMessage extends MessageToServer {
    Characters character;

    public ChooseCharacterMessage(Characters character){
        this.character = character;
    }

    public Characters getCharacter() {
        return character;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getController().process(this, clientHandler);
    }
}
