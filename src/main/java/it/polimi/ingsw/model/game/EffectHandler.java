package it.polimi.ingsw.model.game;

import java.io.Serializable;
import java.util.ArrayList;

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
       Student student = null;
       numofislandstops = 4;
       twomoremoves = false;
   }

    public ArrayList<Student> getEffect7students() { return effect7students;}

    public void setEffect7students(ArrayList<Student> effect7students) {this.effect7students = effect7students;}

    public void removeStudentFromEffect7(Student student){ effect7students.remove(student);}

    public void addStudentInEffect7(Student student){ effect7students.add(student);}

    public ArrayList<Student> getEffect1students() {
        return effect1students;
    }

    public void removeStudentFromEffect1students(Student student) {
        effect1students.remove(student);
    }

    public void addStudentInEffect1students(Student student) {
        effect1students.add(student);
    }

    public void setEffect1students(ArrayList<Student> effect1students) { this.effect1students = effect1students; }

    public ArrayList<Student> getEffect11students() {
        return effect11students;
    }

    public void removeStudentFromEffect11students(Student student) {
        effect11students.remove(student);
    }

    public void addStudentInEffect11students(Student student) {
        effect11students.add(student);
    }

    public void setEffect11students(ArrayList<Student> effect11students) {
        this.effect11students = effect11students;
    }

    public int getIslandIDchoose() {
        return islandIDchoose;
    }

    public ArrayList<Student> getStudentschoose() {
        return studentschoose;
    }

    public void setStudentschoose(ArrayList<Student> studentschoose) {
        this.studentschoose = studentschoose;
    }

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
