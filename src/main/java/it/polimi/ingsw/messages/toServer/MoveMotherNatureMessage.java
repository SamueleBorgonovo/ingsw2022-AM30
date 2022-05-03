package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.exceptions.InvalidTurnException;
import it.polimi.ingsw.exceptions.WrongValueException;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class MoveMotherNatureMessage extends MessageToServer{
    int movement;

    public MoveMotherNatureMessage(int movement){
        this.movement = movement;
    }

    public void action(ClientHandlerInterface clienthandler) throws InvalidTurnException, WrongValueException {
        clienthandler.getGameHandler().moveMotherNature(clienthandler,movement);
    }
}
