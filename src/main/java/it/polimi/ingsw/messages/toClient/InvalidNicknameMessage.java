package it.polimi.ingsw.messages.toClient;


public class InvalidNicknameMessage extends MessageToClient {
    boolean check;

    public InvalidNicknameMessage(boolean check){
        this.check = check;
    }

}
