package it.polimi.ingsw.model;

public class VerifyType {
    //It contains parameters that Effect6, Effect8 and Effect9 can modify
    // to change the normal operation of the verifyIslandInfluence method.
   private boolean twopoints;
   private boolean notower;
   private boolean nocolor;
   private Student student;

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
