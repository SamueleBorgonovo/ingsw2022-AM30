package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;

import java.util.ArrayList;

public class Island {
    private ArrayList<Student> students = new ArrayList<Student>();
    private int numoftowers = 0;
    private Tower tower;
    private boolean stop;
    private int islandID;


    public Island(int islandID) {
        this.islandID = islandID;
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public Tower getTowerColor() {
        return tower;
    }

    public int getNumOfTowers() {
        return numoftowers;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public int getIslandID() {
        return islandID;
    }

    //IslandID goes from 1 to 12
    public void setIslandID(int islandID) {
        this.islandID = islandID;
    }

    public void setTowerColor(Tower tower) {
        if(this.tower == null) {
            this.tower = tower;
            numoftowers = 1;
        }
        else
            this.tower=tower;
    }

   public void addTower() {
       //if the tower is initialized
            numoftowers++;
    }

    public void removeAllTowers() {
        numoftowers = 0;
    }

}