package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.player.PlayerInterface;

import java.util.ArrayList;

public class BoardUpdateMessage extends MessageToClient{
    private Board board;
    private boolean actionAccepted;
    public BoardUpdateMessage(Board board, boolean actionAccepted){
        this.board=board;
        this.actionAccepted=actionAccepted;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }


    public Board getBoard(){return board;}

    public boolean isActionAccepted() {
        return actionAccepted;
    }
}
