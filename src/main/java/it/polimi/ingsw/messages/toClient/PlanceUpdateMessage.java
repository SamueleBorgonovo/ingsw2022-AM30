package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.model.player.Plance;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

/**
 * Message to send the players' plance update to the client
 */
public class PlanceUpdateMessage extends MessageToClient{
    private final ArrayList<PlayerView> players;

    public PlanceUpdateMessage(ArrayList<PlayerView> players){
        this.players=players;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public ArrayList<PlayerView> getPlayers() {
        return players;
    }
}
