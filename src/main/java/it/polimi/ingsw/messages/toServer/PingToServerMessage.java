package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.server.ClientHandlerInterface;

public class PingToServerMessage extends MessageToServer{
    public boolean isping;
    //if isping is false it's a "pong" message

    public PingToServerMessage(boolean isping){
        this.isping=isping;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getController().process(this,clientHandler);
    }

    public boolean isPing(){
        return isping;
    }
}
