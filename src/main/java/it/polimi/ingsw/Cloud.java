package it.polimi.ingsw;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Cloud {
    private ArrayList<Student> students = new ArrayList<>();
    private boolean choosen;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean isChoosen() {
        return choosen;
    }

    public void setChoosen(boolean choosen) {
        this.choosen = choosen;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
