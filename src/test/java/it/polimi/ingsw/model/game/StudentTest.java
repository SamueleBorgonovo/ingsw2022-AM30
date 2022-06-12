package it.polimi.ingsw.model.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    @Test
    void getText(){
        Student student = Student.BLUE;
        assertEquals("Blue",student.getText(student));
        student = Student.YELLOW;
        assertEquals("Yellow",student.getText(student));
        student = Student.RED;
        assertEquals("Red",student.getText(student));
        student = Student.PINK;
        assertEquals("Pink",student.getText(student));
        student = Student.GREEN;
        assertEquals("Green",student.getText(student));

    }

}
