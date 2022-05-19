package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;

import java.io.Serializable;
import java.util.ArrayList;

public class IslandView implements Serializable {
    private ArrayList<Student> students = new ArrayList<>();
    private int numOfTowers;
    private Tower tower;
    private boolean stop;
    private int islandID;

    public IslandView(ArrayList<Student> students,int numOfTowers,Tower tower,boolean stop,int islandID){
        this.students=students;
        this.numOfTowers=numOfTowers;
        this.tower=tower;
        this.stop=stop;
        this.islandID=islandID;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public int getNumOfTowers() {
        return numOfTowers;
    }

    public Tower getTower() {
        return tower;
    }

    public boolean isStop() {
        return stop;
    }

    public int getIslandID() {
        return islandID;
    }
}
