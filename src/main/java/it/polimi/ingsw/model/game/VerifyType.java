package it.polimi.ingsw.model.game;

public class VerifyType {
    // It contains parameters that Effect6, Effect8 and Effect9 can modify
    // to change the normal operation of the verifyIslandInfluence method.
   private int twopoints = 0;
   private boolean notower = false;
   private boolean nocolor = false;
   private boolean professorcontroll = false;
   private Student student;
   private int numofislandstops = 4;

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



}
