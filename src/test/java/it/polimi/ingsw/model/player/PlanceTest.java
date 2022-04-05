package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Plance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlanceTest {
    Plance p ;
    Student[][] hall;
    @BeforeEach
    void init() {
        hall = new Student[5][];
        hall[0] = new Student[10];
        hall[1] = new Student[10];
        hall[2] = new Student[10];
        hall[3] = new Student[10];
        hall[4] = new Student[10];
        p = new Plance();
    }

    @Test
    void getEntrance() {
    }

    @Test
    void getProfessors() {
    }

    @Test
    void getNumOfTowers() {
    }

    @Test
    void addProfessor() {
    }

    @Test
    void removeProfessor() {
    }

    @Test
    void addTower() {
    }

    @Test
    void removeTower() {
    }

    @Test
    void addStudentEntrance() {
    }

    @Test
    void removeStudentEntrance() {
    }

    @Test
    void addStudentHall() {
        Student[][] hallWanted = {
                {Student.GREEN,Student.GREEN,null,null,null,null,null,null,null,null},
                {Student.RED,null,null,null,null,null,null,null,null,null},
                {Student.YELLOW,null,null,null,null,null,null,null,null,null},
                {Student.PINK,Student.PINK,Student.PINK,null,null,null,null,null,null,null},
                {Student.BLUE,Student.BLUE,null,null,null,null,null,null,null,null}
        };

        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.YELLOW);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.BLUE);
        p.addStudentHall(Student.BLUE);

        hall= p.getStudentHall();

        assertTrue(Arrays.deepEquals(hall, hallWanted));

    }

    @Test
    void getStudentHall() {
    }

    @Test
    void removeStudentFromHall() {
        Student[][] hallWanted = {
                {Student.GREEN,Student.GREEN,null,null,null,null,null,null,null,null},
                {Student.RED,null,null,null,null,null,null,null,null,null},
                {Student.YELLOW,null,null,null,null,null,null,null,null,null},
                {Student.PINK,Student.PINK,null,null,null,null,null,null,null,null},
                {Student.BLUE,Student.BLUE,null,null,null,null,null,null,null,null}
        };

        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.YELLOW);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.BLUE);
        p.addStudentHall(Student.BLUE);

        p.removeStudentFromHall(Student.PINK);

        hall= p.getStudentHall();
        assertTrue(Arrays.deepEquals(hall, hallWanted));

    }

    @Test
    public void testRemoveStudent02() {
        Student[][] hallWanted = {
                {Student.GREEN, Student.GREEN, null, null, null, null, null, null, null, null},
                {Student.RED, Student.RED, Student.RED, Student.RED, Student.RED, Student.RED, Student.RED, Student.RED, Student.RED, null},
                {Student.YELLOW, null, null, null, null, null, null, null, null, null},
                {Student.PINK, Student.PINK, null, null, null, null, null, null, null, null},
                {Student.BLUE, Student.BLUE, null, null, null, null, null, null, null, null}
        };

        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.YELLOW);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.BLUE);
        p.addStudentHall(Student.BLUE);

        p.removeStudentFromHall(Student.RED);

        hall = p.getStudentHall();
        assertTrue(Arrays.deepEquals(hall, hallWanted));
    }

    @Test
    public void testRemoveStudent03()
    {
        Student[][] hallWanted = {
                {Student.GREEN,Student.GREEN,null,null,null,null,null,null,null,null},
                {Student.RED,Student.RED,Student.RED,Student.RED,Student.RED,Student.RED,Student.RED,Student.RED,Student.RED,null},
                {null,null,null,null,null,null,null,null,null,null},
                {Student.PINK,Student.PINK,null,null,null,null,null,null,null,null},
                {Student.BLUE,Student.BLUE,null,null,null,null,null,null,null,null}
        };

        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.BLUE);
        p.addStudentHall(Student.BLUE);

        p.removeStudentFromHall(Student.YELLOW);
        hall=p.getStudentHall();
        assertTrue(Arrays.deepEquals(hall, hallWanted));
    }

    @Test
    public void testRemoveStudent04()
    {
        Student[][] hallWanted = {
                {Student.GREEN,Student.GREEN,null,null,null,null,null,null,null,null},
                {Student.RED,Student.RED,Student.RED,Student.RED,Student.RED,Student.RED,Student.RED,Student.RED,Student.RED,null},
                {null,null,null,null,null,null,null,null,null,null},
                {Student.PINK,Student.PINK,null,null,null,null,null,null,null,null},
                {Student.BLUE,Student.BLUE,null,null,null,null,null,null,null,null}
        };

        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.YELLOW);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.BLUE);
        p.addStudentHall(Student.BLUE);

        p.removeStudentFromHall(Student.YELLOW);
        p.removeStudentFromHall(Student.YELLOW);

        hall=p.getStudentHall();
        assertTrue(Arrays.deepEquals(hall, hallWanted));
    }

    @Test
    void getNumberOfStudent() {
        int num=0;
        Student [][] hall;
        hall = new Student[5][];
        hall[0] = new Student[10];
        hall[1] = new Student[10];
        hall[2] = new Student[10];
        hall[3] = new Student[10];
        hall[4] = new Student[10];

        Plance p = new Plance();

        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.GREEN);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.RED);
        p.addStudentHall(Student.YELLOW);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.PINK);
        p.addStudentHall(Student.BLUE);
        p.addStudentHall(Student.BLUE);

        num=p.getNumberOfStudent(Student.PINK);
        assertEquals(num,2);
    }

    @Test
    public void getNumberOfStudent02(){
        int num=0;
        Student [][] hall;
        hall = new Student[5][];
        hall[0] = new Student[10];
        hall[1] = new Student[10];
        hall[2] = new Student[10];
        hall[3] = new Student[10];
        hall[4] = new Student[10];

        Plance p = new Plance();

        p.addStudentHall(Student.GREEN);

        num=p.getNumberOfStudent(Student.PINK);
        assertEquals(num,0);
    }

    @Test
    void getTowers() {
    }
}