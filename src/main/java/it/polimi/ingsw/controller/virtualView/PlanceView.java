package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;
import it.polimi.ingsw.model.player.Professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PlanceView implements Serializable {
    private ArrayList<Student> entrance = new ArrayList<>();
    private ArrayList<Professor> professors = new ArrayList<>();
    private HashMap<Student,Integer> hall = new HashMap<>();
    private int numoftowers;
    private  Tower tower;

    public PlanceView(ArrayList<Student> entrance,ArrayList<Professor> professors,HashMap<Student,Integer> hall,int numoftowers,Tower tower){
        this.entrance=entrance;
        this.professors=professors;
        this.hall=hall;
        this.numoftowers=numoftowers;
        this.tower=tower;
    }

    public ArrayList<Student> getEntrance() {
        return entrance;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public HashMap<Student, Integer> getHall() {
        return hall;
    }

    public int getNumoftowers() {
        return numoftowers;
    }

    public Tower getTower() {
        return tower;
    }

    public int getNumberOfStudentHall(Student student){
        return hall.get(student).intValue();
    }
}
