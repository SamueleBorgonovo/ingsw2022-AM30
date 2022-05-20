package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

public class WaitingForPlayersMessage extends MessageToClient{
    private boolean lobby;
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
