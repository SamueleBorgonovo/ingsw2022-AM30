package src.main.java.org.example;

import java.util.ArrayList;

public class Island {
    private int islandID;
    private Tower Tower;
    private boolean stop;
    private boolean mothernature;
    private final ArrayList<Student> students = new ArrayList<Student>();

    public Island(int islandID, Tower tower, boolean stop, boolean mothernature) {
        this.islandID = islandID;
        Tower = tower;
        this.stop = stop;
        this.mothernature = mothernature;
    }

    public int getIslandID() {
        return islandID;
    }

    public Tower getTower() {
        return Tower;
    }

    public boolean isStop() {
        return stop;
    }

    public boolean isMothernature() {
        return mothernature;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setIslandID(int islandID) {
        this.islandID = islandID;
    }

    public void setTower(Tower tower) {
        Tower = tower;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void setMothernature(boolean mothernature) {
        this.mothernature = mothernature;
    }

    public void addStudents(Student student) {
        students.add(student);
    }
}
