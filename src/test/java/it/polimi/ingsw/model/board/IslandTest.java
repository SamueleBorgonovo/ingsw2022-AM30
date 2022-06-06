package it.polimi.ingsw.model.board;

import it.polimi.ingsw.controller.virtualView.IslandView;
import it.polimi.ingsw.model.board.Island;
import it.polimi.ingsw.model.game.GameMode;
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
    }

    @Test
    void getStudents() {
        i.getStudents().clear();
        i.addStudent(Student.RED);
        i.addStudent(Student.RED);
        i.addStudent(Student.BLUE);
        i.addStudent(Student.YELLOW);
        int redcount=0;
        int bluecount=0;
        int yellowcount=0;
        for(Student student: i.getStudents()){
            if(student==Student.RED)
                redcount++;
            if(student==Student.BLUE)
                bluecount++;
            if(student==Student.YELLOW)
                yellowcount++;
        }

        int value=1;
        if(i.getStudents().size()!=4)
            value=-1;
        if(redcount!=2)
            value=-2;
        if(bluecount!=1)
            value=-3;
        if(yellowcount!=1)
            value=-4;

        assertEquals(1,value);
    }

    @Test
    void getTowerColor(){
        i.setTowerColor(Tower.BLACK);
        Tower tower=i.getTowerColor();

        int value=1;
        if(tower!=Tower.BLACK)
            value=-1;

        assertEquals(1,value);
    }

    @Test
    void getNumOfTowers(){
        i.setTowerColor(Tower.BLACK);
        i.addTower();

        assertEquals(2,i.getNumOfTowers());
    }

    @Test
    void addStudent() {
        i.getStudents().clear();
        i.addStudent(Student.RED);
        i.addStudent(Student.RED);
        i.addStudent(Student.BLUE);
        i.addStudent(Student.YELLOW);

        ArrayList<Student> studentsWanted = new ArrayList<>();
        studentsWanted.add(Student.RED);
        studentsWanted.add(Student.RED);
        studentsWanted.add(Student.BLUE);
        studentsWanted.add(Student.YELLOW);
        assertTrue(studentsWanted.equals(i.getStudents()));
    }

    @Test
    void isStop() {
        i.setStop(true);

        assertTrue(i.isStop());
    }

    @Test
    void setStop() {
        i.setStop(true);

        assertTrue(i.isStop());
    }

    @Test
    void getIslandID() {
        Board board=new Board(GameMode.SIMPLEMODE,2);

        assertEquals(2,board.getArchipelago().getIslands().get(1).getIslandID());
    }

    @Test
    void setIslandID() {
        i.setIslandID(5);

        assertEquals(5,i.getIslandID());
    }

    @Test
    void setTowerColor(){
        i.setTowerColor(Tower.BLACK);

        int value=1;
        if(i.getTowerColor()!=Tower.BLACK)
            value=-1;
        if(i.getNumOfTowers()!=1)
            value=-2;

        assertEquals(1,value);
    }


    @Test
    void addTower() {
        i.setTowerColor(Tower.BLACK);
        int value=1;
        if(i.getNumOfTowers()!=1)
            value=-1;
        i.addTower();
        if(i.getNumOfTowers()!=2)
            value=-2;

        assertEquals(1,value);
    }

    @Test
    void removeAllTowers() {
        i.setTowerColor(Tower.BLACK);
        i.addTower();
        int value=1;
        if(i.getNumOfTowers()!=2)
            value=-1;
        i.removeAllTowers();
        if(i.getNumOfTowers()!=0)
            value=-2;

        assertEquals(1,value);
    }

    @Test
    void getIslandView(){
        i.setTowerColor(Tower.BLACK);
        i.addTower();
        i.getStudents().clear();
        i.addStudent(Student.RED);
        i.addStudent(Student.RED);
        i.addStudent(Student.YELLOW);
        i.setStop(true);
        i.setIslandID(2);

        IslandView island=i.getIslandView();

        int value=1;
        if(!island.isStop())
            value=-1;
        if(island.getTower()!=Tower.BLACK)
            value=-2;
        if(island.getNumOfTowers()!=2)
            value=-3;
        if(island.getIslandID()!=2)
            value=-4;
        int redcount=0;
        int yellowcount=0;
        for(Student student: island.getStudents()){
            if(student==Student.RED)
                redcount++;
            if(student==Student.YELLOW)
                yellowcount++;
        }
        if(redcount!=2)
            value=-5;
        if(yellowcount!=1)
            value=-6;

        assertEquals(1,value);
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
}