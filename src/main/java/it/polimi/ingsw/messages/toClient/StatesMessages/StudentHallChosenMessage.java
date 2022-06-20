package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.model.game.Student;

/**
 * Message to notify that a player moved a student to his hall
 */
public class StudentHallChosenMessage extends MessageToClient {
    private final Student student;
    private final String nickname;
    public StudentHallChosenMessage(String nickname, Student student){
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
