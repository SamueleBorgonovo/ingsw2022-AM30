package it.polimi.ingsw;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Cloud {
    private ArrayList<Student> students = new ArrayList<>();
    private boolean choosen;
    private int cloudID;

    public Cloud(ArrayList<Student> students, boolean choosen, int cloudID) {
        this.students = students;
        this.choosen = choosen;
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
