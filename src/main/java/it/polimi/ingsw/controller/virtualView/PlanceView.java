package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;
import it.polimi.ingsw.model.player.Professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class PlanceView is used to send the updated plance of a player to the client from the server
 */
public class PlanceView implements Serializable {
    private final ArrayList<Student> entrance;
    private final ArrayList<Professor> professors;
    private final HashMap<Student,Integer> hall;
    private final int numOfTowers;
    private final Tower tower;

    /**
     * Constructor PlanceView sets the instances of all attributes of the plance
     * @param entrance plance's entrance
     * @param professors plance's professors
     * @param hall plance's hall
     * @param numOfTowers plance's num of towers
     * @param tower plance's type of tower
     */
    public PlanceView(ArrayList<Student> entrance,ArrayList<Professor> professors,HashMap<Student,Integer> hall,int numOfTowers,Tower tower){
        this.entrance=entrance;
        this.professors=professors;
        this.hall=hall;
        this.numOfTowers=numOfTowers;
        this.tower=tower;
    }

    /**
     * Method getEntrance returns the plance's entrance
     * @return the plance's entrance
     */
    public ArrayList<Student> getEntrance() {
        return entrance;
    }

    /**
     * Method getProfessors returns the plance's professors
     * @return the plance's professors
     */
    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    /**
     * Method getHall returns plance's hall
     * @return the plance's hall
     */
    public HashMap<Student, Integer> getHall() {
        return hall;
    }

    /**
     * Method getNumOfTowers returns plance's num of towers
     * @return the plance's num of towers
     */
    public int numOfTowers() {
        return numOfTowers;
    }

    /**
     * Method getTower returns plance's type of tower
     * @return the plance's type of tower
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * Method getNumberOfStudentHall returns the number of a student type present in the hall
     * @param student student type
     * @return the number of a student type present in the hall
     */
    public int getNumberOfStudentHall(Student student){
        return hall.get(student);
    }
}
