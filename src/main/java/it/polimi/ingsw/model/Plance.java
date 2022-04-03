package it.polimi.ingsw.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Plance {
    private ArrayList<Student> entrance = new ArrayList<>();
    private ArrayList<Professor> tableProfessor = new ArrayList<>();
    private final Student[][] hall = new Student[5][10];
    private ArrayList<Tower> towers = new ArrayList<>();

    public Plance(ArrayList<Student> entrance, ArrayList<Professor> tableProfessor, ArrayList<Tower> towers) {
        this.entrance = entrance;
        this.tableProfessor = tableProfessor;
        this.towers = towers;
    }

    public ArrayList<Student> getEntrance() {
        return entrance;
    }

    public ArrayList<Professor> getProfessor() {
        return tableProfessor;
    }

    public int getNumOfTowers() {
        return towers.size();
    }

    public void addProfessor(Professor professor) {
        tableProfessor.add(professor);
    }

    public void removeProfessor(Professor professor) {
        tableProfessor.remove(professor);

    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void removeTower() {
        towers.remove(towers.size() - 1);
    }

    public void addStudentEntrance(Student student) {
        entrance.add(student);
    }

    public void removeStudentEntrance(Student student) {
        entrance.remove(student);
    }

    public void addStudentHall(@NotNull Student student) {
        for (int i = 0; i < 10; i++)
            if (hall[student.ordinal()][i] == null) {
                hall[student.ordinal()][i] = student;
                i = 10;
            }
    }

    public Student[][] getStudentHall() {
        return hall;
    }

    public Student removeStudentFromHall(@NotNull Student student) {
        if (hall[student.ordinal()][0] == null)
            return null;
        else {
            for (int i = 0; i < 10; i++)
                if (i == 9) {
                    hall[student.ordinal()][i] = null;
                    break;
                } else if (hall[student.ordinal()][i + 1] == null) {
                    hall[student.ordinal()][i] = null;
                    break;
                }
            return student;
        }
    }

    public int getNumberOfStudent(Student student){
        int count=0;
        for(int i=0;i<10;i++)
            if(hall[student.ordinal()][i] == student)
                count ++;
        return count;
    }

}
