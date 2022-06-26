package it.polimi.ingsw.model.board;

import it.polimi.ingsw.controller.virtualView.CloudView;
import it.polimi.ingsw.model.game.Student;
import java.util.ArrayList;

/**
 * Cloud class handles the action related to the cloud
 */
public class Cloud{
    private ArrayList<Student> students = new ArrayList<>();
    private boolean chosen = false;
    private final int cloudID;

    /**
     * Constructor Cloud creates a new Cloud in the game
     * @param cloudID cloud's id
     */
    public Cloud(int cloudID) {
        this.cloudID = cloudID;
    }

    /**
     * Method getStudents return the students on the cloud
     *
     * @return the students on the cloud
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Method setStudents set the students on the cloud
     *
     * @param students the students on the cloud
     */
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * Method isChosen is used to check if a cloud has been chosen
     *
     * @return true if the cloud has been chosen, else false
     */
    public boolean isChosen() {
        return chosen;
    }

    /**
     * Method setChosen set a cloud as chosen or not chosen
     *
     * @param chosen true if the cloud has been chosen, else false
     */
    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    /**
     * Method getCloudID return the id of the cloud
     *
     * @return the id of the cloud
     */
    public int getCloudID() {
        return cloudID;
    }

    /**
     * Method getCloudView returns the CloudView instance associated to the cloud
     */
    public CloudView getCloudView(){
        return new CloudView(this.students,this.chosen, this.cloudID);
    }

}
