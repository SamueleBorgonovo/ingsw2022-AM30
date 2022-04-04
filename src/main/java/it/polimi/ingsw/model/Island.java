package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Island {
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Tower> towers = new ArrayList<>();
    private boolean stop;
    private int islandID;


    public Island( int islandID) {
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


    public void addStudent(Student student) {
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

    public void changeTowers(Tower tower){
        for(int j=0; j<towers.size() ; j++)
            towers.set(j, tower);
    }

    public void addTower(Tower towersToAdd) {
        if(towers.size()==0 || towers.get(0).equals(towersToAdd))
            towers.add(towersToAdd);
    }

    public void removeAllTowers(){
        towers.clear();
    }
}
