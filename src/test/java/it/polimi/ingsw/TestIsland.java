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
        ArrayList<Student> students=  new ArrayList<>();
        ArrayList<Tower> towers = new ArrayList<>();
        ArrayList<Tower> towers2 = new ArrayList<>();
        boolean stop=false;
        int islandID= 3;
        students.add(Student.RED);
        students.add(Student.RED);
        students.add(Student.BLUE);

        towers.add(Tower.WHITE);
        towers.add(Tower.WHITE);
        towers.add(Tower.WHITE);

        towers2.add(Tower.BLACK);
        towers2.add(Tower.BLACK);
        towers2.add(Tower.BLACK);

        Island i = new Island(students, towers, stop, islandID);
        i.changeTowers(Tower.BLACK);
        towers= i.getTowers();
        assertEquals(towers, towers2);
    }

    @Test
    public void testAddTowers01()
    {
        ArrayList<Student> students=  new ArrayList<>();
        ArrayList<Tower> towers = new ArrayList<>();
        ArrayList<Tower> towers2 = new ArrayList<>();
        boolean stop=false;
        int islandID= 3;
        students.add(Student.RED);
        students.add(Student.RED);
        students.add(Student.BLUE);

        towers.add(Tower.WHITE);
        towers.add(Tower.WHITE);
        towers.add(Tower.WHITE);

        towers2.add(Tower.WHITE);
        towers2.add(Tower.WHITE);
        towers2.add(Tower.WHITE);
        towers2.add(Tower.WHITE);
        towers2.add(Tower.WHITE);
        towers2.add(Tower.WHITE);

        Island i = new Island(students, towers, stop, islandID);
        i.addTowers(towers);
        towers=i.getTowers();
        assertEquals(towers, towers2);
    }

}
