package it.polimi.ingsw.messages.toClient.StatesMessages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.model.game.Student;

public class StudentHallChoosedMessage extends MessageToClient {
    private Student student;
    private String nickname;
    public StudentHallChoosedMessage(Student student,String nickname){
        this.student=student;
        this.nickname=nickname;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }
}
