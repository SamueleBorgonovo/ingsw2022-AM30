package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.server.ClientHandlerInterface;

public class MoveMotherNatureMessage extends MessageToServer{
    int movement;

    public MoveMotherNatureMessage(int movement){
        this.movement = movement;
    }

    public int getMovement() {
        return movement;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getMessageHandler().process(this, clientHandler);
    }
}
