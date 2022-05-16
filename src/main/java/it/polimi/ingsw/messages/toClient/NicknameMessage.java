package it.polimi.ingsw.messages.toClient;


import it.polimi.ingsw.client.Client;

public class NicknameMessage extends MessageToClient {
    boolean check;
    boolean reconnect;

    public NicknameMessage(boolean check, boolean reconnect){
        this.check = check;
        this.reconnect=reconnect;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public boolean getCheck(){return check;}
    //If check is true, name choosed is available

    public boolean getReconnect(){return reconnect;}
    //If reconnect is true, client can reconnect to a game



}
