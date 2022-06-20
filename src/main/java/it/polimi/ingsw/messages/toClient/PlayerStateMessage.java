package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.player.PlayerState;

/**
 * Message to send the player's state of a client
 */
public class PlayerStateMessage extends MessageToClient {
    private final PlayerState state;

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
