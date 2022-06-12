package it.polimi.ingsw.model.game;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EffectHandlerTest {
    EffectHandler effectHandler = new EffectHandler();

    @Test
    void getNumofislandstops() {
        assertEquals(4, effectHandler.getNumofislandstops());
        for(int i=3;i>=0;i--) {
            effectHandler.removeislandstop();
            assertEquals(i, effectHandler.getNumofislandstops());
        }
        for(int i=0;i<5;i++){
            assertEquals(i, effectHandler.getNumofislandstops());
            effectHandler.addislandstop();
        }
    }

    @Test
    void addislandstop() {
        assertEquals(4, effectHandler.getNumofislandstops());
        for(int i=3;i>=0;i--) {
            effectHandler.removeislandstop();
            assertEquals(i, effectHandler.getNumofislandstops());
        }
        for(int i=0;i<5;i++){
            assertEquals(i, effectHandler.getNumofislandstops());
            effectHandler.addislandstop();
        }
    }

    @Test
    void removeislandstop() {
        assertEquals(4, effectHandler.getNumofislandstops());
        for(int i=3;i>=0;i--) {
            effectHandler.removeislandstop();
            assertEquals(i, effectHandler.getNumofislandstops());
        }
        for(int i=0;i<5;i++){
            assertEquals(i, effectHandler.getNumofislandstops());
            effectHandler.addislandstop();
        }
    }

    @Test
    void isProfessorcontroll() {
        effectHandler.setProfessorcontroll(false);
        assertFalse(effectHandler.isProfessorcontroll());
        effectHandler.setProfessorcontroll(true);
        assertTrue(effectHandler.isProfessorcontroll());
    }

    @Test
    void setProfessorcontroll() {
        effectHandler.setProfessorcontroll(false);
        assertFalse(effectHandler.isProfessorcontroll());
        effectHandler.setProfessorcontroll(true);
        assertTrue(effectHandler.isProfessorcontroll());
    }

    @Test
    void getTwopoints() {
        assertEquals(0, effectHandler.getTwopoints());
        effectHandler.setTwopoints(1);
        assertEquals(1, effectHandler.getTwopoints());
        effectHandler.setTwopoints(0);
    }

    @Test
    void setTwopoints() {
        effectHandler.setNotower(false);
        assertEquals(0, effectHandler.getTwopoints());
        effectHandler.setTwopoints(1);
        assertEquals(1, effectHandler.getTwopoints());
        effectHandler.setTwopoints(0);
    }

    @Test
    void isNotower() {
        effectHandler.setNotower(false);
        assertFalse(effectHandler.isNotower());
        effectHandler.setNotower(true);
        assertTrue(effectHandler.isNotower());
    }

    @Test
    void setNotower() {
        effectHandler.setNotower(false);
        assertFalse(effectHandler.isNotower());
        effectHandler.setNotower(true);
        assertTrue(effectHandler.isNotower());
    }

    @Test
    void isNocolor() {
        effectHandler.setNocolor(false);
        assertFalse(effectHandler.isNocolor());
        effectHandler.setNocolor(true);
        assertTrue(effectHandler.isNocolor());
    }

    @Test
    void setNocolor() {
        effectHandler.setNocolor(false);
        assertFalse(effectHandler.isNocolor());
        effectHandler.setNocolor(true);
        assertTrue(effectHandler.isNocolor());
    }

    @Test
    void getStudent() {
        for(int i=0; i<5;i++) {
            effectHandler.setStudent(Student.values()[i]);
            assertEquals(Student.values()[i], effectHandler.getStudent());
        }
    }

    @Test
    void setStudent() {
        for(int i=0; i<5;i++) {
            effectHandler.setStudent(Student.values()[i]);
            assertEquals(Student.values()[i], effectHandler.getStudent());
        }
    }

    @Test
    void studentsOnCardTest(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(Student.RED);
        students.add(Student.PINK);
        students.add(Student.YELLOW);

        effectHandler.setEffect1students(students);
        assertEquals(3,effectHandler.getEffect1students().size());
        assertTrue(effectHandler.getEffect1students().containsAll(students));
        effectHandler.setEffect7students(students);
        assertEquals(3,effectHandler.getEffect7students().size());
        assertTrue(effectHandler.getEffect7students().containsAll(students));
        effectHandler.setEffect11students(students);
        assertEquals(3,effectHandler.getEffect11students().size());
        assertTrue(effectHandler.getEffect11students().containsAll(students));

        effectHandler.removeStudentFromEffect1students(Student.RED);
        effectHandler.removeStudentFromEffect7(Student.PINK);
        effectHandler.removeStudentFromEffect11students(Student.YELLOW);
        assertFalse(effectHandler.getEffect1students().contains(Student.RED));
        assertFalse(effectHandler.getEffect7students().contains(Student.PINK));
        assertFalse(effectHandler.getEffect11students().contains(Student.YELLOW));

        effectHandler.addStudentInEffect1students(Student.BLUE);
        effectHandler.addStudentInEffect7(Student.BLUE);
        effectHandler.addStudentInEffect11students(Student.BLUE);
        assertTrue(effectHandler.getEffect1students().contains(Student.BLUE));
        assertTrue(effectHandler.getEffect7students().contains(Student.BLUE));
        assertTrue(effectHandler.getEffect11students().contains(Student.BLUE));
    }

    @Test
    void studentChooseTest(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(Student.RED);
        students.add(Student.BLUE);
        effectHandler.setStudentschoose(students);
        assertTrue(effectHandler.getStudentschoose().containsAll(students));
    }
}