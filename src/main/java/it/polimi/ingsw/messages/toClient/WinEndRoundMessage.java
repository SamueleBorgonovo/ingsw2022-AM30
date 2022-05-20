package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;

import java.util.ArrayList;

public class WinEndRoundMessage extends MessageToClient{
    private ArrayList<String> nickname;
    private int winType;

    public WinEndRoundMessage(ArrayList<String> nickname,int winType){
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
