package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.exceptions.InvalidTurnException;
import it.polimi.ingsw.exceptions.WrongStudentException;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class MoveStudentToIslandMessage extends MessageToServer {
    Student student;
    int islandID;

    public MoveStudentToIslandMessage(int islandID, Student student){
        this.islandID = islandID;
        this.student = student;
    }

    public void action(ClientHandlerInterface clienthandler) throws InvalidTurnException, WrongStudentException {
        clienthandler.getGameHandler().moveStudentToIsland(clienthandler,islandID, student);
    }
}
