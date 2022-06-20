package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

/**
 * Message to notify that the game is in pause because there are not enough players
 */
public class WaitingForPlayersMessage extends MessageToClient{
    private final boolean lobby;
    public WaitingForPlayersMessage(boolean lobby){
        this.lobby=lobby;
    }
    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public boolean isLobby() {
        return lobby;
    }
}
