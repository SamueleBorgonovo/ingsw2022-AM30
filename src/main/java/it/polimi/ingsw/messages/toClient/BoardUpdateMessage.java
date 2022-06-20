package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.controller.virtualView.BoardView;

/**
 * Message to send the board update to the client
 */
public class BoardUpdateMessage extends MessageToClient{
    private final BoardView board;
    public BoardUpdateMessage(BoardView board){
        this.board=board;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }


    public BoardView getBoard(){return board;}

}
