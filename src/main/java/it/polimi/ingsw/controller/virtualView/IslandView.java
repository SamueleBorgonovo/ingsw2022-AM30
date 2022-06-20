package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class IslandView is used to send the updated islands to the client from the server
 */
public class IslandView implements Serializable {
    private final ArrayList<Student> students;
    private final int numOfTowers;
    private final Tower tower;
    private final boolean stop;
    private final int islandID;

    /**
     * Constructor IslandView sets the instances of all attributes of the island
     * @param students island's students
     * @param numOfTowers island's num of tower
     * @param tower island's type of tower
     * @param stop true if the island is stopped
     * @param islandID island's id
     */
    public IslandView(ArrayList<Student> students,int numOfTowers,Tower tower,boolean stop,int islandID){
        this.students=students;
        this.numOfTowers=numOfTowers;
        this.tower=tower;
        this.stop=stop;
        this.islandID=islandID;
    }

    /**
     * Method getStudents returns the island's students
     * @return the island's students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Method getNumOfTowers returns the island's num of tower
     * @return the island's num of tower
     */
    public int getNumOfTowers() {
        return numOfTowers;
    }

    /**
     * Method getTower returns the island's type of tower
     * @return the island's type of tower
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * Method isStop returns if the island is stopped
     * @return true if the island is stopped, false otherwise
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * Method getIslandID returns the island's id
     * @return the island's id
     */
    public int getIslandID() {
        return islandID;
    }
}
