package org.example;

import java.util.ArrayList;

public class Island {
    private int islandID;
    private boolean stop;
    private boolean motherNature;
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Tower> towers = new ArrayList<>();

    public Island(int islandID, boolean stop,ArrayList<Student> students,ArrayList<Tower> towers) {
        this.islandID = islandID;
        this.stop = stop;
        this.students = students;
        this.towers = towers;

    }

    public int getIslandID() {
        return islandID;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public boolean isStop() {
        return stop;
    }

    public boolean isMotherNature() {
        return motherNature;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setIslandID(int islandID) {
        this.islandID = islandID;
    }

    public void addTowers(ArrayList<Tower> towers) {
        towers.add(towers.get(0));
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void setMotherNature(boolean motherNature) {
        this.motherNature = motherNature;
    }

    public void addStudents(Student student) {
        students.add(student);
    }
}
