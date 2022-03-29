package it.polimi.ingsw;
import java.util.ArrayList;

public class Plance {
    private final ArrayList<Student> entrance;
    private final ArrayList<Professor> tableProfessor;
    private final Student hall[][];
    private final ArrayList<Tower> towers;

    public Plance(ArrayList<Student> entrance, ArrayList<Professor> tableProfessor, Student[][] hall, ArrayList<Tower> towers) {
        this.entrance = entrance;
        this.tableProfessor = tableProfessor;
        this.hall = hall;
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
        tableProfessor.add(professor.ordinal(),professor);
    }

    public void removeProfessor(Professor professor){
        tableProfessor.remove(professor);
    }
}
