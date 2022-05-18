package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.model.game.Student;

public class StudentHallChoosedMessage extends MessageToClient {
    private Student student;
    private String nickname;
    public StudentHallChoosedMessage(String nickname,Student student){
        this.student=student;
        this.nickname=nickname;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public Student getStudent() {
        return student;
    }

    public String getNickname() {
        return nickname;
    }
}
