package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.board.Archipelago;
import it.polimi.ingsw.model.board.Island;
import it.polimi.ingsw.model.board.MotherNature;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArchipelagoTest {

    @Test
    void getIslands() {
    }

    @Test
    void getStudentIslands() {
        ArrayList<Student> studentWanted= new ArrayList<>();
        ArrayList<Student> student;
        studentWanted.add(Student.RED);
        studentWanted.add(Student.RED);
        studentWanted.add(Student.YELLOW);
        studentWanted.add(Student.BLUE);
        Island i1 = new Island(1);
        Island i2 = new Island(2);
        Island i3 = new Island(3);
        MotherNature motherNature = new MotherNature(2);
        ArrayList<Island> islands = new ArrayList<>();
        i1.addStudent(Student.RED);
        i1.addStudent(Student.RED);
        i1.addStudent(Student.YELLOW);
        i1.addStudent(Student.BLUE);
        i2.addStudent(Student.RED);
        i3.addStudent(Student.RED);
        islands.add(i1);
        islands.add(i2);
        islands.add(i3);
        Archipelago archipelago = new Archipelago(islands, motherNature);
        student=archipelago.getStudentIslands(1);
        assertEquals(student, studentWanted);
    }

    @Test
    void getTowersIsland() {
        ArrayList<Tower> towersWanted= new ArrayList<>();
        ArrayList<Tower> towers;
        towersWanted.add(Tower.WHITE);
        towersWanted.add(Tower.WHITE);
        towersWanted.add(Tower.WHITE);
        Island i1 = new Island(1);
        Island i2 = new Island(2);
        Island i3 = new Island(3);
        MotherNature motherNature = new MotherNature(2);
        ArrayList<Island> islands = new ArrayList<>();
        i2.addTower(Tower.WHITE);
        i2.addTower(Tower.WHITE);
        i2.addTower(Tower.WHITE);
        i2.addTower(Tower.GREY);
        islands.add(i1);
        islands.add(i2);
        islands.add(i3);
        Archipelago archipelago = new Archipelago(islands, motherNature);
        towers=archipelago.getTowersIsland(2);
        assertEquals(towers, towersWanted);
    }

    @Test
    void setMotherNatureIsland() {
    }

    @Test
    void mergeIslands() {
    }

    @Test
    void getNumOfIslands() {
    }
}