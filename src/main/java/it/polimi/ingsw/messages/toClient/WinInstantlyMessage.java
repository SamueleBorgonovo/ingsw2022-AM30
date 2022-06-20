package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

import java.util.ArrayList;

/**
 * Message to notify that a player won in the middle of his turn
 */
public class WinInstantlyMessage extends MessageToClient {
    private final ArrayList<String> nickname;
    private final int winType;

    public WinInstantlyMessage(ArrayList<String> nickname, int winType){
        this.nickname=nickname;
        this.winType=winType;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public ArrayList<String> getNickname() {
        return nickname;
    }

    public int getWinType() {
        return winType;
    }
}
