package it.polimi.ingsw;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Plance {
    private ArrayList<Student> entrance = new ArrayList<>();
    private ArrayList<Professor> tableProfessor = new ArrayList<>();
    ArrayList<ArrayList<Student>> Hall = new ArrayList<>();
    private ArrayList<Tower> towers = new ArrayList<>();

    public Plance(ArrayList<Student> entrance, ArrayList<Professor> tableProfessor, ArrayList<ArrayList<Student>> hall, ArrayList<Tower> towers) {
        this.entrance = entrance;
        this.tableProfessor = tableProfessor;
        Hall = hall;
        this.towers = towers;
    }

    public ArrayList<Student> getEntrance() {
        return entrance;
    }

    public ArrayList<Professor> getProfessor() {
        return tableProfessor;
    }

    public int getNumOfTowers(){
        return towers.size();
    }

    public void addProfessor(Professor professor){
        tableProfessor.add(professor);
    }

    public void removeProfessor(Professor professor){
        tableProfessor.remove(professor);

    }

    public void addTower(Tower tower){
        towers.add(tower);
    }

    public void removeTower(){
        towers.remove(towers.size()-1);
    }

    public void addStudentEntrance(Student student){
        entrance.add(student);
    }

    public void removeStudentEntrance(Student student){
        entrance.remove(student);
    }

    public void addStudentHall(@NotNull Student student){
        Hall.get(student.ordinal()).add(student);
    }

    public ArrayList<ArrayList<Student>> getStudentHall(){
        return Hall;
    }

}
