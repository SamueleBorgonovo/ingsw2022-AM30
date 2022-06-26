package it.polimi.ingsw.model.player;

import it.polimi.ingsw.controller.virtualView.PlanceView;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Plance class handles the action related to player's plance
 */
public class Plance {
    private final ArrayList<Student> entrance = new ArrayList<>();
    private final ArrayList<Professor> professors = new ArrayList<>();
    private final HashMap<Student,Integer> hall = new HashMap<>();
    private int numoftowers;
    private final Tower tower;

    /**
     * Constructor Plance initialize the player's plance
     *
     * @param tower the player's tower
     * @param numoftowers the number of towers of the player
     */
    public Plance(Tower tower, int numoftowers){
        this.tower = tower;
        this.numoftowers = numoftowers;
        hall.put(Student.RED,0);
        hall.put(Student.BLUE,0);
        hall.put(Student.YELLOW,0);
        hall.put(Student.PINK,0);
        hall.put(Student.GREEN,0);
    }

    /**
     * Method assStudentHall add a student in the hall of the plance
     *
     * @param student the student to add in the plance
     */
    public void addStudentHall(Student student) {
        hall.replace(student, hall.get(student) + 1);
    }

    /**
     * Method getNumberOfStudentHall returns the number of students of a specific color in the hall of the plance
     *
     * @param student the color of the student requested
     * @return the number of students of a specific color in the hall of the plance
     */
    public int getNumberOfStudentHall(Student student){
        return hall.get(student);
    }

    /**
     * Method getEntrance returns the students which are in the entrance of the plance
     *
     * @return the students which are in the entrance of the plance
     */
    public ArrayList<Student> getEntrance() {
        return new ArrayList<>(entrance);
    }

    /**
     * Method getProfessors return the professors which are in the plance
     *
     * @return the professors which are in the plance
     */
    public ArrayList<Professor> getProfessors() {
        return new ArrayList<>(professors);
    }

    /**
     * Method getNumOfTowers return the number of towers which are in the plance
     *
     * @return the number of towers which are in the plance
     */
    public int getNumOfTowers() {
        return numoftowers;
    }

    /**
     * Method addProfessors add a new professor in the plance
     *
     * @param professor the professor to add in the plance
     */
    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    /**
     * Method removeProfessors remove a new professor from the plance
     *
     * @param professor the professor to remove from the plance
     */
    public void removeProfessor(Professor professor) {
        professors.remove(professor);
    }

    /**
     * Method addTower add a tower in the plance
     */
    public void addTower() {
        numoftowers++;
    }

    /**
     * Method removeTower remove a tower from the plance
     */
    public void removeTower() {
        numoftowers--;
    }

    /**
     * Method getTower return the type of tower of the player's plance
     *
     * @return the type of tower of the player's plance
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * Method addStudentEntrance add a new professor in the Entrance of the Plance
     *
     * @param student the student to add in the plance
     */
    public void addStudentEntrance(Student student) {
        entrance.add(student);
    }

    /**
     * Method removeStudentEntrance remove a student from the Entrance of the Plance
     *
     * @param student the student to remove from the Plance
     */
    public void removeStudentEntrance(Student student) {
        entrance.remove(student);
    }

    /**
     * Method removeStudentFromHall remove a student from the Hall of the Plance
     *
     * @param student the student to remove from the Plance
     */
    public void removeStudentFromHall(Student student) {
        if(hall.get(student) >0)
            hall.replace(student, hall.get(student) - 1);
    }

    /**
     * Method getHall returns the students in the hall of the Plance
     *
     * @return the students in the hall of the Plance
     */
    public HashMap<Student, Integer> getHall() {
        return hall;
    }

    /**
     * Method getPlanceView returns the PlanceView instance associated to the Plance
     */
    public PlanceView getPlanceView(){
        return new PlanceView(entrance,professors,hall,numoftowers,tower);
    }
}
