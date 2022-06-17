package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.server.ClientHandlerInterface;

public class DisconnectionRespondMessage extends MessageToServer{

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getController().process(this, clientHandler);
    }
}
