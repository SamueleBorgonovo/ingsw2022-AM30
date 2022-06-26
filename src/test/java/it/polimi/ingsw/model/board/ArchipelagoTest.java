package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Tower;
import it.polimi.ingsw.model.game.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArchipelagoTest {
    Board board2Players = new Board(GameMode.EXPERTMODE,2);
    Board board3Players = new Board(GameMode.EXPERTMODE,3);

  //  Archipelago archipelago = new Archipelago();

    @Test
    void getMothernature(){
        Archipelago archipelago=board2Players.getArchipelago();
        archipelago.getMotherNature().setMotherNature(1);
        assertEquals(1,archipelago.getMotherNature().isOn());
    }

    @Test
    void getIslands() {
        Archipelago archipelago2= new Archipelago();
        archipelago2= board2Players.getArchipelago();
        assertEquals(archipelago2,board2Players.getArchipelago());
    }

    @Test
    void getStudentIslands() {
        ArrayList<Student> studentWanted= new ArrayList<>();
        ArrayList<Student> student;
        int num = board2Players.getArchipelago().getMotherNature().isOn();
        if(num==12)
            num=0;
        studentWanted.add(board2Players.getArchipelago().getSingleIsland(num+1).getStudents().get(0));
        studentWanted.add(Student.RED);
        studentWanted.add(Student.RED);
        studentWanted.add(Student.YELLOW);
        studentWanted.add(Student.BLUE);
        board2Players.getArchipelago().getSingleIsland(num+1).addStudent(Student.RED);
        board2Players.getArchipelago().getSingleIsland(num+1).addStudent(Student.RED);
        board2Players.getArchipelago().getSingleIsland(num+1).addStudent(Student.YELLOW);
        board2Players.getArchipelago().getSingleIsland(num+1).addStudent(Student.BLUE);
        student=board2Players.getArchipelago().getStudentIslands(num+1);
        assertEquals(student, studentWanted);

        student=board2Players.getArchipelago().getStudentIslands(13);
        assertNull(student);
    }

    @Test
    void getTowersTypeIsland() {
        board2Players.getArchipelago().getIslands().get(1).setTowerColor(Tower.WHITE);
        assertEquals(Tower.WHITE, board2Players.getArchipelago().getTowerTypeIsland(2));

        Tower tower;
        tower=board2Players.getArchipelago().getTowerTypeIsland(13);
        assertNull(tower);
    }

    @Test
    void mergeIslands(){
        Archipelago archipelago=board2Players.getArchipelago();
        archipelago.getIslands().get(0).getStudents().clear();
        archipelago.getIslands().get(1).getStudents().clear();
        archipelago.getIslands().get(0).addStudent(Student.RED);
        archipelago.getIslands().get(1).addStudent(Student.BLUE);
        archipelago.getIslands().get(0).setStop(true);
        archipelago.getIslands().get(1).setTowerColor(Tower.BLACK);
        archipelago.getIslands().get(0).setTowerColor(Tower.BLACK);
        archipelago.getIslands().get(1).addTower();
        archipelago.getIslands().get(1).addTower();
        archipelago.getIslands().get(0).addTower();
        archipelago.mergeIslands(1,2);

        int value=1;
        if(archipelago.getIslands().size()!=11)
            value=-1;
        if(!archipelago.getIslands().get(0).isStop())
            value=-2;
        int redcount=0;
        int bluecount=0;
        for(Student student : archipelago.getIslands().get(0).getStudents()){
            if(student==Student.RED)
                redcount++;
            if(student==Student.BLUE)
                bluecount++;
        }
        if(redcount!=1)
            value=-3;
        if(bluecount!=1)
            value=-4;
        if(archipelago.getIslands().get(0).getTowerColor()!=Tower.BLACK)
            value=-5;
        if(archipelago.getIslands().get(0).getNumOfTowers()!=5)
            value=-6;

        //assertEquals(1,value);
    }

    @Test
    void getNumOfIslands() {
        assertEquals(12,board2Players.getArchipelago().getNumOfIslands());
        board2Players.getArchipelago().mergeIslands(2,3);
        assertEquals(11, board2Players.getArchipelago().getNumOfIslands());
        board2Players.getArchipelago().mergeIslands(1,11);
        assertEquals(10,board2Players.getArchipelago().getNumOfIslands());
    }

    @Test
    void getSingleIlsnad(){
        Archipelago archipelago=board2Players.getArchipelago();
        archipelago.getIslands().get(0).getStudents().clear();
        archipelago.getIslands().get(0).addStudent(Student.BLUE);
        archipelago.getIslands().get(0).addStudent(Student.BLUE);
        archipelago.getIslands().get(0).addStudent(Student.BLUE);
        archipelago.getIslands().get(0).addStudent(Student.BLUE);
        archipelago.getIslands().get(0).setStop(true);
        Island island=archipelago.getIslands().get(0);

        int value=1;
        int bluecount1=0;
        int bluecount2=0;
        for(Student student: archipelago.getIslands().get(0).getStudents())
            if(student==Student.BLUE)
                bluecount1++;
        for(Student student: island.getStudents())
            if(student==Student.BLUE)
                bluecount2++;

        if(bluecount1!=bluecount2)
            value=-1;
        if(archipelago.getIslands().get(0).getIslandID()!=island.getIslandID())
            value=-2;
        if(island.isStop()!=archipelago.getIslands().get(0).isStop())
            value=-3;

        assertEquals(1,value);

        island=board2Players.getArchipelago().getSingleIsland(13);
        assertNull(island);
    }

    @Test
    void verifyMergeableIsland() {
        //also test mergeIsland
        board2Players.getArchipelago().getSingleIsland(4).setTowerColor(Tower.WHITE);
        board2Players.getArchipelago().getSingleIsland(5).setTowerColor(Tower.WHITE);
        board2Players.getArchipelago().verifyMergeableIsland();
        assertEquals(11, board2Players.getArchipelago().getNumOfIslands());
        assertEquals(Tower.WHITE,board2Players.getArchipelago().getSingleIsland(4).getTowerColor());
        board2Players.getArchipelago().getSingleIsland(1).setTowerColor(Tower.WHITE);
        board2Players.getArchipelago().getSingleIsland(11).setTowerColor(Tower.WHITE);
        board2Players.getArchipelago().verifyMergeableIsland();
        assertEquals(10, board2Players.getArchipelago().getNumOfIslands());
        assertEquals(Tower.WHITE,board2Players.getArchipelago().getSingleIsland(1).getTowerColor());
    }
}