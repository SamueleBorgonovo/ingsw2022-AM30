package it.polimi.ingsw;

import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.Tower;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIsland {
    @Test
    public void testChangeTowers01()
    {
        ArrayList<Tower> towers;
        ArrayList<Tower> towers2 = new ArrayList<>();
        int islandID = 3;

        towers2.add(Tower.BLACK);
        towers2.add(Tower.BLACK);
        towers2.add(Tower.BLACK);

        Island i = new Island(islandID);
        i.addTower(Tower.WHITE);
        i.addTower(Tower.WHITE);
        i.addTower(Tower.WHITE);
        i.changeTowers(Tower.BLACK);
        towers= i.getTowers();
        assertEquals(towers, towers2);
    }

    @Test
    public void testAddTowers01()
    {
        ArrayList<Tower> towers;
        ArrayList<Tower> towers2 = new ArrayList<>();
        int islandID= 3;

        towers2.add(Tower.WHITE);
        towers2.add(Tower.WHITE);

        Island i = new Island(islandID);
        i.addTower(Tower.WHITE);
        i.addTower(Tower.WHITE);
        towers=i.getTowers();
        assertEquals(towers, towers2);
    }

    @Test
    public void testAddStudent01(){
        ArrayList<Student> studentsWanted = new ArrayList<>();

        ArrayList<Student> students=  new ArrayList<>();

        int islandID= 3;
        studentsWanted.add(Student.RED);
        studentsWanted.add(Student.RED);
        studentsWanted.add(Student.BLUE);
        studentsWanted.add(Student.YELLOW);

        Island i = new Island(islandID);
        i.addStudent(Student.RED);
        i.addStudent(Student.RED);
        i.addStudent(Student.BLUE);
        i.addStudent(Student.YELLOW);

        students=i.getStudents();

        assertEquals(students,studentsWanted);
    }
}
