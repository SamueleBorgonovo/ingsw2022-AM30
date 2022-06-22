package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.server.ClientHandlerInterface;

/**
 * Message to send the student moved to island by the player
 */
public class MoveStudentToIslandMessage extends MessageToServer {
    Student student;
    int islandID;

    public MoveStudentToIslandMessage(int islandID, Student student){
        this.islandID = islandID;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public int getIslandID() {
        return islandID;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getMessageHandler().process(this, clientHandler);
    }
}
