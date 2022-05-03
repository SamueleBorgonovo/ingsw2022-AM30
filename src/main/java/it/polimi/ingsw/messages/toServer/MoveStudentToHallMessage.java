package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.exceptions.InvalidTurnException;
import it.polimi.ingsw.exceptions.WrongStudentException;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class MoveStudentToHallMessage extends MessageToServer{
    Student student;

    public MoveStudentToHallMessage(Student student){
        this.student = student;
    }

    public void action(ClientHandlerInterface clienthandler) throws InvalidTurnException, WrongStudentException {
        clienthandler.getGameHandler().moveStudentToHall(clienthandler,student);
    }
}