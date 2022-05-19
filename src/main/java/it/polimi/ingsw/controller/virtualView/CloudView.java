package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.game.Student;

import java.io.Serializable;
import java.util.ArrayList;

public class CloudView implements Serializable {
    private ArrayList<Student> students = new ArrayList<>();
    private boolean choosen = false;
    private int cloudID;

    public CloudView(ArrayList<Student> students, boolean choosen, int cloudID) {
        this.students = students;
        this.choosen = choosen;
        this.cloudID = cloudID;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean isChoosen() {
        return choosen;
    }

    public int getCloudID() {
        return cloudID;
    }
}
