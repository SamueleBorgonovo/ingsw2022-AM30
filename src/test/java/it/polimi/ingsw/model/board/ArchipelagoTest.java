package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Tower;
import it.polimi.ingsw.model.game.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArchipelagoTest {
    Board board2Players = new Board(GameMode.EXPERTMODE,2);
    Board board3Players = new Board(GameMode.EXPERTMODE,3);

  //  Archipelago archipelago = new Archipelago();

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
        int num = board2Players.getArchipelago().getMothernature().isOn();
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
    }

    @Test
    void getTowersTypeIsland() {
        board2Players.getArchipelago().getIslands().get(1).setTowerColor(Tower.WHITE);
        assertEquals(Tower.WHITE, board2Players.getArchipelago().getTowerTypeIsland(2));
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