package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.exceptions.InvalidCharacterException;
import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidTurnException;
import it.polimi.ingsw.exceptions.OutOfCoinsException;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class ChooseCharacterMessage extends MessageToServer {
    Characters character;

    public ChooseCharacterMessage(Characters character){
        this.character = character;
    }

    public void action(ClientHandlerInterface clienthandler) throws InvalidStopException, InvalidTurnException, OutOfCoinsException, InvalidCharacterException {
        clienthandler.getGameHandler().chooseCharacter(clienthandler,character);
    }
}
