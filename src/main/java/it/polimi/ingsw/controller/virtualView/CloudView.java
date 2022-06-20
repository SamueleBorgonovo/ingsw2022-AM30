package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.game.Student;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  Class CloudView is used to send the updated clouds to the client from the server
 */
public class CloudView implements Serializable {
    private final ArrayList<Student> students;
    private final boolean chosen;
    private final int cloudID;

    /**
     * Method CloudView sets the instances of all attributes of the cloud
     * @param students cloud's student
     * @param chosen true if the cloud is chosen, false otherwise
     * @param cloudID cloud's id
     */
    public CloudView(ArrayList<Student> students, boolean chosen, int cloudID) {
        this.students = students;
        this.chosen = chosen;
        this.cloudID = cloudID;
    }

    /**
     * Method getStudents returns the cloud's students
     * @return the cloud's students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Method isChosen returns if the cloud has been chosen in this round
     * @return true if the cloud has been chosen, false otherwise
     */
    public boolean isChosen() {
        return chosen;
    }

    /**
     * Method getCloudID returns the cloud's id
     * @return the cloud's id
     */
    public int getCloudID() {
        return cloudID;
    }
}
