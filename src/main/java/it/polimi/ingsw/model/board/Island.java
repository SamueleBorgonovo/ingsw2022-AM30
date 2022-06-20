package it.polimi.ingsw.model.board;

import it.polimi.ingsw.controller.virtualView.IslandView;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Island class handles the action related to the island
 */
public class Island {
    private final ArrayList<Student> students = new ArrayList<>();
    private int numOfTowers;
    private Tower tower;
    private boolean stop;
    private int islandID;

    /**
     * Constructor Island creates a new Island in the game
     *
     * @param islandID the id of the island
     */
    public Island(int islandID) {
        this.islandID = islandID;
        numOfTowers = 0;
        stop = false;
        tower = null;
    }

    /**
     * Method getStudents returns the students on the island
     *
     * @return the students on the island
     */
    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Method getTowerColor returns the Tower of the island
     *
     * @return the Tower of the island
     */
    public Tower getTowerColor() {
        return tower;
    }

    /**
     * Method getNumOfTowers return the number of towers in the island
     *
     * @return the number of towers in the island
     */
    public int getNumOfTowers() {
        return numOfTowers;
    }

    /**
     * Method addStudent add a student in the island
     *
     * @param student add a student in the island
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Method isStop return true if the island has a stop, else false
     *
     * @return true if the island has a stop, else false
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * Method setStop set an island as stopped or not
     *
     * @param stop true if the island is stopped, else false
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * Method getIslandID returns the unique id of the island
     *
     * @return the id of the island
     */
    public int getIslandID() {
        return islandID;
    }

    //IslandID goes from 1 to 12

    /**
     * Method setIslandID set the unique id of the island
     *
     * @param islandID the id of the island
     */
    public void setIslandID(int islandID) {
        this.islandID = islandID;
    }

    /**
     * Method setTowerColor set the Tower of the island
     *
     * @param tower set the tower of the island
     */
    public void setTowerColor(Tower tower) {
        if(this.tower == null) {
            this.tower = tower;
            numOfTowers = 1;
        }
        else
            this.tower = tower;
    }

    /**
     * Method addTower add a Tower to the island
     */
   public void addTower() { numOfTowers++; }

    /**
     * Method removeAllTowers remove all the towers from the island
     */
    public void removeAllTowers() {
        numOfTowers = 0;
    }

    /**
     * Method getIslandView return the IslandView instance associated to the island
     *
     * @return the IslandView instance associated to the island
     */
    public IslandView getIslandView(){
        return new IslandView(students,numOfTowers,tower,stop,islandID);
    }

}