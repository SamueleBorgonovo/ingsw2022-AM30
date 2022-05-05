package it.polimi.ingsw.messages.toClient;


public class NicknameMessage extends MessageToClient {
    boolean check;

    public NicknameMessage(boolean check){
        this.check = check;
    }

    public boolean getCheck(){return check;}

}
