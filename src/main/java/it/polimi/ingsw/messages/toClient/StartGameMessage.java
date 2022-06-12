package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

public class StartGameMessage extends MessageToClient{
    private boolean restart;

    public StartGameMessage(Boolean restart){
        this.restart=restart;
    }

    public boolean isRestart(){ return restart;}

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
