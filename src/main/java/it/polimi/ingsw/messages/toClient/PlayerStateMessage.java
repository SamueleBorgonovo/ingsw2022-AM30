package it.polimi.ingsw.messages.toClient;


import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.player.PlayerState;

public class PlayerStateMessage extends MessageToClient {
    private PlayerState state;

    public PlayerStateMessage(PlayerState playerState){
        state=playerState;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public PlayerState getState() {
        return state;
    }
}
