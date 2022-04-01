package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Island {
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Tower> towers = new ArrayList<>();
    private boolean motherNature;
    private boolean stop;
    private int islandID;

    public Island(ArrayList<Student> students, ArrayList<Tower> towers, boolean motherNature, boolean stop, int islandID) {
        this.students = students;
        this.towers = towers;
        this.motherNature = motherNature;
        this.stop = stop;
        this.islandID = islandID;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void setTowers(Tower tower) {
        towers.add(tower);
    }

    public boolean isMotherNature() {
        return motherNature;
    }

    public void setMotherNature(boolean motherNature) {
        this.motherNature = motherNature;
    }

    public void addStudents(Student student) {
        students.add(student);
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public int getIslandID() {
        return islandID;
    }

    public void setIslandID(int islandID) {
        this.islandID = islandID;
    }

    public void changeTowers(ArrayList<Tower> towers, Tower tower){
        int i=towers.size();
        for(int j=0; j<i ; j++)
            towers.set(i, tower);
    }

    public void addTowers(ArrayList<Tower> towers) {
        towers.add(towers.get(0));
    }

}
