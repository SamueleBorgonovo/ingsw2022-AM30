package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.server.ClientHandlerInterface;

import java.util.ArrayList;

public class ChooseStudentsEffectMessage extends MessageToServer{
    ArrayList<Student> students;

    public ChooseStudentsEffectMessage(ArrayList<Student> students){
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getMessageHandler().process(this, clientHandler);
    }
}

