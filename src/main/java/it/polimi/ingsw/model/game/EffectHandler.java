package it.polimi.ingsw.model.game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * EffectHandler class handles character's information
 */
public class EffectHandler implements EffectHandlerInterface, Serializable {
    // It contains parameters that Effect6, Effect8 and Effect9 can modify
    // to change the normal operation of the verifyIslandInfluence method.
   private int twopoints;
   private boolean notower;
   private boolean nocolor;
   private boolean professorcontroll;
   private Student student;
   private int numofislandstops;
   private int islandIDchoose;
   private ArrayList<Student> studentschoose = new ArrayList<>();
   private ArrayList<Student> effect1students = new ArrayList<>();
   private ArrayList<Student> effect11students = new ArrayList<>();
   private ArrayList<Student> effect7students = new ArrayList<>();
   private boolean twomoremoves;

   public EffectHandler(){
       twopoints = 0;
       notower = false;
       nocolor = false;
       professorcontroll = false;
       numofislandstops = 4;
       twomoremoves = false;
   }

    /**
     * Method getEffect7Students return the students on character number 7
     *
     * @return the students on character number 7
     */
    public ArrayList<Student> getEffect7students() { return effect7students;}

    /**
     * Method setEffect7Students set the students on character number 7
     *
     * @param effect7students the students to set on character number 7
     */
    public void setEffect7students(ArrayList<Student> effect7students) {this.effect7students = effect7students;}

    /**
     * Method removeStudentFromEffect7 remove some students from character number 7
     *
     * @param student the students to remove from character number 7
     */
    public void removeStudentFromEffect7(Student student){ effect7students.remove(student);}

    /**
     * Method addStudentInEffect7 add some student in character number 7
     *
     * @param student the students to add in character number 7
     */
    public void addStudentInEffect7(Student student){ effect7students.add(student);}

    /**
     * Method getEffect1Students return the students on character number 1
     *
     * @return the students on character number 1
     */
    public ArrayList<Student> getEffect1students() {
        return effect1students;
    }

    /**
     * Method removeStudentFromEffect1Student remove some students from character number 1
     *
     * @param student the students to remove from character number 1
     */
    public void removeStudentFromEffect1students(Student student) {
        effect1students.remove(student);
    }

    /**
     * Method addStudentInEffect1students add some student in character number 1
     *
     * @param student the students to add in character number 1
     */
    public void addStudentInEffect1students(Student student) {
        effect1students.add(student);
    }

    /**
     * Method setEffect1Students set the students on character number 1
     *
     * @param effect1students the students to set on character number 1
     */
    public void setEffect1students(ArrayList<Student> effect1students) { this.effect1students = effect1students; }

    /**
     * Method getEffect11Students return the students on character number 11
     *
     * @return the students on character number 11
     */
    public ArrayList<Student> getEffect11students() {
        return effect11students;
    }

    /**
     * Method removeStudentFromEffect11Student remove some students from character number 11
     *
     * @param student the students to remove from character number 11
     */
    public void removeStudentFromEffect11students(Student student) {
        effect11students.remove(student);
    }

    /**
     * Method addStudentInEffect11students add some student in character number 11
     *
     * @param student the students to add in character number 11
     */
    public void addStudentInEffect11students(Student student) {
        effect11students.add(student);
    }

    /**
     * Method setEffect11Students set the students on character number 11
     *
     * @param effect11students the students to set on character number 11
     */
    public void setEffect11students(ArrayList<Student> effect11students) {
        this.effect11students = effect11students;
    }

    /**
     * Method getIslandIDchoose return the id of the island chosen by the player
     *
     * @return the id of the island chosen by the player
     */
    public int getIslandIDchoose() {
        return islandIDchoose;
    }

    /**
     * Method getStudentschoose return the students chosen by the player
     *
     * @return the students chosen by the player
     */
    public ArrayList<Student> getStudentschoose() {
        return studentschoose;
    }

    /**
     * Method setStudentschoose set the student chosen by the player
     *
     * @param studentschoose the student chosen by the player
     */
    public void setStudentschoose(ArrayList<Student> studentschoose) {
        this.studentschoose = studentschoose;
    }

    /**
     * Method setIslandIDchoose set the island chosen by the player
     *
     * @param islandIDchoose the islans chosen by the player
     */
    public void setIslandIDchoose(int islandIDchoose) {
        this.islandIDchoose = islandIDchoose;
    }

    public int getNumofislandstops() {
        return numofislandstops;
    }

    public void addislandstop(){
        numofislandstops++;
    }

    public void removeislandstop(){
        numofislandstops--;
    }

    public boolean isProfessorcontroll() {
        return professorcontroll;
    }

    public void setProfessorcontroll(boolean professorcontroll) {
        this.professorcontroll = professorcontroll;
    }

    public int getTwopoints() {
        return twopoints;
    }

    public void setTwopoints(int playerID) {
        twopoints = playerID;
    }

    public boolean isNotower() {
        return notower;
    }

    public void setNotower(boolean notower) {
        this.notower = notower;
    }

    public boolean isNocolor() {
        return nocolor;
    }

    public void setNocolor(boolean nocolor) {
        this.nocolor = nocolor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setTwomoremoves(boolean choose){ twomoremoves=choose;}

    public boolean getTwomoremoves(){ return twomoremoves;}

}
