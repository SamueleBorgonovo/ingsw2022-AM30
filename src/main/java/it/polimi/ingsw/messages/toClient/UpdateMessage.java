package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerInterface;

import java.util.ArrayList;

public class UpdateMessage extends MessageToClient{
    private ArrayList<PlayerInterface> players;
    private Board board;
    private boolean actionAccepted;
    public UpdateMessage(ArrayList<PlayerInterface> players, Board board, boolean actionAccepted){
        this.players=players;
        this.board=board;
        this.actionAccepted=actionAccepted;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public ArrayList<PlayerInterface> getPlayers(){
        return players;
    }

    public Board getBoard(){return board;}

    public boolean isActionAccepted() {
        return actionAccepted;
    }
}
