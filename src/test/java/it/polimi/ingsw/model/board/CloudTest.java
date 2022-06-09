package it.polimi.ingsw.model.board;

import it.polimi.ingsw.controller.virtualView.CloudView;
import it.polimi.ingsw.model.game.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CloudTest {
    ArrayList<Student> students;
    Cloud cloud;
    @BeforeEach
        void init(){
            students = new ArrayList<>();
            students.add(Student.RED);
            students.add(Student.PINK);
            students.add(Student.BLUE);

            cloud = new Cloud(3);
            cloud.setStudents(students);
    }

    @Test
    void getStudents() {
        assertEquals(students,cloud.getStudents());
    }

    @Test
    void setStudents() {
        ArrayList<Student> students2 = new ArrayList<>();
        students2.add(Student.GREEN);
        students2.add(Student.YELLOW);
        students2.add(Student.YELLOW);
        cloud.setStudents(students2);
        assertEquals(students2,cloud.getStudents());
    }

    @Test
    void isChoosen() {
        //tests also setChoosen
        assertFalse(cloud.isChoosen());
        cloud.setChoosen(true);
        assertTrue(cloud.isChoosen());
    }

    @Test
    void setChoosen(){
        cloud.setChoosen(true);
        assertTrue(cloud.isChoosen());
    }

    @Test
    void getCloudID() {
        assertEquals(3,cloud.getCloudID());
    }

    @Test
    void getCloudView(){
        cloud.setChoosen(true);
        CloudView cloudView=cloud.getCloudView();
        assertEquals(3,cloudView.getStudents().size());
        assertTrue(cloudView.getStudents().containsAll(students));

        assertTrue(cloudView.isChoosen());
        assertEquals(3,cloudView.getCloudID());
    }
}