package it.polimi.ingsw.model;

public class VerifyType {
    // It contains parameters that Effect6, Effect8 and Effect9 can modify
    // to change the normal operation of the verifyIslandInfluence method.
   private boolean twopoints;
   private boolean notower;
   private boolean nocolor;
   private boolean professorcontroll;
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

    public boolean isTwopoints() {
        return twopoints;
    }

    public void setTwopoints(boolean twopoints) {
        this.twopoints = twopoints;
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
