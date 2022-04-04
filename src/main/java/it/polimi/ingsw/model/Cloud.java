package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Cloud {
    private ArrayList<Student> students = new ArrayList<>();
    private boolean choosen;
    private int cloudID;

    public Cloud(int cloudID) {
        this.cloudID = cloudID;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public boolean isChoosen() {
        return choosen;
    }

    public void setChoosen(boolean choosen) {
        this.choosen = choosen;
    }

    public int getCloudID() {
        return cloudID;
    }
}
