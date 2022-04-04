package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlance {
        @Test
        public void testAddStudentToHall01()
        {
            Student[][] hallWanted = {
                    {Student.GREEN,Student.GREEN,null,null,null,null,null,null,null,null},
                    {Student.RED,null,null,null,null,null,null,null,null,null},
                    {Student.YELLOW,null,null,null,null,null,null,null,null,null},
                    {Student.PINK,Student.PINK,Student.PINK,null,null,null,null,null,null,null},
                    {Student.BLUE,Student.BLUE,null,null,null,null,null,null,null,null}
            };

            Student [][] hall;
            hall = new Student[5][];
            hall[0] = new Student[10];
            hall[1] = new Student[10];
            hall[2] = new Student[10];
            hall[3] = new Student[10];
            hall[4] = new Student[10];

            Plance p = new Plance();

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
    public void testRemoveStudent01()
    {
        Student[][] hallWanted = {
                {Student.GREEN,Student.GREEN,null,null,null,null,null,null,null,null},
                {Student.RED,null,null,null,null,null,null,null,null,null},
                {Student.YELLOW,null,null,null,null,null,null,null,null,null},
                {Student.PINK,Student.PINK,null,null,null,null,null,null,null,null},
                {Student.BLUE,Student.BLUE,null,null,null,null,null,null,null,null}
        };

        Student [][] hall;
        hall = new Student[5][];
        hall[0] = new Student[10];
        hall[1] = new Student[10];
        hall[2] = new Student[10];
        hall[3] = new Student[10];
        hall[4] = new Student[10];


        Plance p = new Plance();

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

        Student[][] hall;
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

        p.removeStudentFromHall(Student.YELLOW);
        p.removeStudentFromHall(Student.YELLOW);

       hall=p.getStudentHall();
       assertTrue(Arrays.deepEquals(hall, hallWanted));
    }

}