package it.polimi.ingsw.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Plance {
    private ArrayList<Student> entrance = new ArrayList<>();
    private ArrayList<Professor> tableProfessor = new ArrayList<>();
    private Student[][] hall = new Student[5][5];
    private ArrayList<Tower> towers = new ArrayList<>();

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
       // hall.get(student.ordinal()).add(student);
        //ArrayList<Student> s = new ArrayList<>();
        //s.add(student);
        //hall.add(student.ordinal(),s);
        for(int i=0;i<10;i++)
            if(hall[student.ordinal()][i] == null)
                hall[student.ordinal()][i] = student;

    }

    public Student[][] getStudentHall(){
        return hall;
    }

    public Student removeStudentFromHall(Student student){
        int i=0;
        if(hall[student.ordinal()][0]==null)
            return null;
        else
            while(hall[student.ordinal()][i+1]!=null)
                i++;
            hall[student.ordinal()][i]=null;
            return student;
    }

}
