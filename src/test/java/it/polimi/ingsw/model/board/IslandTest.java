package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.board.Island;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IslandTest {
    Island i ;
    @BeforeEach
        void init(){
        i= new Island(3);
        i.addStudent(Student.RED);
        i.addStudent(Student.RED);
        i.addStudent(Student.BLUE);
        i.addStudent(Student.YELLOW);
    }

    @Test
    void getStudents() {
    }

    @Test
    void getTowers() {
    }

    @Test
    void setTowers() {
    }

    @Test
    void addStudent() {
        ArrayList<Student> studentsWanted = new ArrayList<>();
        studentsWanted.add(Student.RED);
        studentsWanted.add(Student.RED);
        studentsWanted.add(Student.BLUE);
        studentsWanted.add(Student.YELLOW);
        assertTrue(studentsWanted.equals(i.getStudents()));
    }

    @Test
    void isStop() {
    }

    @Test
    void setStop() {
    }

    @Test
    void getIslandID() {
    }

    @Test
    void setIslandID() {
    }

    @Test
    void changeTowers() {
        ArrayList<Tower> towers;
        ArrayList<Tower> towers2 = new ArrayList<>();
        int islandID = 3;

        towers2.add(Tower.BLACK);
        towers2.add(Tower.BLACK);
        towers2.add(Tower.BLACK);

        Island i = new Island(islandID);
        i.setTowerColor(Tower.WHITE);
        i.addTower();
        i.addTower();
        i.addTower();
        i.setTowerColor(Tower.BLACK);
        int num;
        int type;
        //assertEquals(towers, towers2);
    }

    @Test
    void addTower() {
        ArrayList<Tower> towers;
        ArrayList<Tower> towers2 = new ArrayList<>();
        int islandID= 3;

        towers2.add(Tower.WHITE);
        towers2.add(Tower.WHITE);

        Island i = new Island(islandID);
      //  i.addTower(Tower.WHITE);
      //  i.addTower(Tower.WHITE);
     //   towers=i.getTowers();
       // assertEquals(towers, towers2);
    }

    @Test
    void removeAllTowers() {
        ArrayList<Tower> towers;
        ArrayList<Tower> towersExpected = new ArrayList<>();
        int islandID= 3;

        Island i = new Island(islandID);
      //  i.addTower(Tower.WHITE);
        // i.addTower(Tower.WHITE);
        i.removeAllTowers();
        //towers=i.getTowers();
       // assertEquals(towers, towersExpected);
    }
}