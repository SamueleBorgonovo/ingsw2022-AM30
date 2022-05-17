package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.model.game.Student;

public class StudentIslandChoosedMessage extends MessageToClient {
    private String nickname;
    private Student student;
    private int islandID;

    public StudentIslandChoosedMessage(String nickname,Student student,int islandID){
        this.nickname=nickname;
        this.student=student;
        this.islandID=islandID;
    }
    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public String getNickname() {
        return nickname;
    }

    public Student getStudent() {
        return student;
    }

    public int getIslandID() {
        return islandID;
    }
}
