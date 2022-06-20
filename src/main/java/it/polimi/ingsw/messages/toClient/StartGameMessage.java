package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

/**
 * Message to notify that the game is starting
 */
public class StartGameMessage extends MessageToClient{
    private final boolean restart;

    public StartGameMessage(Boolean restart){
        this.restart=restart;
    }

    public boolean isRestart(){ return restart;}

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
