package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Plance implements Serializable {
    private ArrayList<Student> entrance = new ArrayList<>();
    private ArrayList<Professor> professors = new ArrayList<>();
    private final HashMap<Student,Integer> hall = new HashMap<>();
    private int numoftowers;
    private final Tower tower;

    public Plance(Tower tower, int numoftowers){
        this.tower = tower;
        this.numoftowers = numoftowers;
        hall.put(Student.RED,0);
        hall.put(Student.BLUE,0);
        hall.put(Student.YELLOW,0);
        hall.put(Student.PINK,0);
        hall.put(Student.GREEN,0);
    }

    public void addStudentHall(Student student) {
        hall.replace(student, Integer.valueOf(hall.get(student).intValue() + 1));
    }

    public int getNumberOfStudentHall(Student student){
        return hall.get(student).intValue();
    }

    public ArrayList<Student> getEntrance() {
        return new ArrayList<>(entrance);
    }

    public ArrayList<Professor> getProfessors() {
        return new ArrayList<>(professors);
    }

    public int getNumOfTowers() {
        return numoftowers;
    }

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    public void removeProfessor(Professor professor) {
        professors.remove(professor);
    }

    public void addTower() {
        numoftowers++;
    }

    public void removeTower() {
        numoftowers--;
    }
    public Tower getTower() {
        return tower;
    }

    public void addStudentEntrance(Student student) {
        entrance.add(student);
    }

    public void removeStudentEntrance(Student student) {
        entrance.remove(student);
    }
    public void removeStudentFromHall(Student student) {
        if(hall.get(student).intValue()>0)
            hall.replace(student, Integer.valueOf(hall.get(student).intValue() - 1));
    }

    public HashMap<Student, Integer> getHall() {
        return hall;
    }
}
