package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class MoveStudentToHallMessage extends MessageToServer{
    Student student;

    public MoveStudentToHallMessage(Student student){
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getMessageHandler().process(this, clientHandler);
    }
}