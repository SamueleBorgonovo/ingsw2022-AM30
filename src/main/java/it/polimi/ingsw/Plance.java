package it.polimi.ingsw;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Plance {
    private ArrayList<Student> entrance = new ArrayList<>();
    private ArrayList<Professor> tableProfessor = new ArrayList<>();
    private ArrayList<ArrayList<Student>> hall = new ArrayList<>();
    private ArrayList<Tower> towers = new ArrayList<>();
    private Player player;

    public Plance(ArrayList<Student> entrance, ArrayList<Professor> tableProfessor, ArrayList<ArrayList<Student>> hall, ArrayList<Tower> towers, Player player) {
        this.entrance = entrance;
        this.tableProfessor = tableProfessor;
        this.hall = hall;
        this.towers = towers;
        this.player= player;
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
        hall.get(student.ordinal()).add(student);
        if(hall.get(student.ordinal()).size() % 3 == 0)
            player.addCoins();
    }

    public ArrayList<ArrayList<Student>> getStudentHall(){
        return hall;
    }

}
