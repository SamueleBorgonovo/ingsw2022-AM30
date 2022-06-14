package it.polimi.ingsw.model.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getText() {
        Student student;
        student = Student.GREEN;
        assertEquals("Green", student.getText(student));
        student = Student.RED;
        assertEquals("Red", student.getText(student));
        student = Student.YELLOW;
        assertEquals("Yellow", student.getText(student));
        student = Student.PINK;
        assertEquals("Pink", student.getText(student));
        student = Student.BLUE;
        assertEquals("Blue", student.getText(student));
    }
}