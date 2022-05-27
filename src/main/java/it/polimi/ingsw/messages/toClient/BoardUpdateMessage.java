package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.controller.virtualView.BoardView;

public class BoardUpdateMessage extends MessageToClient{
    private BoardView board;
    public BoardUpdateMessage(BoardView board){
        this.board=board;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }


    public BoardView getBoard(){return board;}

}
