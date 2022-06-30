package it.polimi.ingsw.model.game;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EffectHandlerTest {
    EffectHandler effectHandler = new EffectHandler();

    @Test
    void getNumofislandstops() {
        assertEquals(4, effectHandler.getNumOfIslandStops());
        for(int i=3;i>=0;i--) {
            effectHandler.removeIslandStop();
            assertEquals(i, effectHandler.getNumOfIslandStops());
        }
        for(int i=0;i<5;i++){
            assertEquals(i, effectHandler.getNumOfIslandStops());
            effectHandler.addIslandStop();
        }
    }

    @Test
    void addislandstop() {
        assertEquals(4, effectHandler.getNumOfIslandStops());
        for(int i=3;i>=0;i--) {
            effectHandler.removeIslandStop();
            assertEquals(i, effectHandler.getNumOfIslandStops());
        }
        for(int i=0;i<5;i++){
            assertEquals(i, effectHandler.getNumOfIslandStops());
            effectHandler.addIslandStop();
        }
    }

    @Test
    void removeislandstop() {
        assertEquals(4, effectHandler.getNumOfIslandStops());
        for(int i=3;i>=0;i--) {
            effectHandler.removeIslandStop();
            assertEquals(i, effectHandler.getNumOfIslandStops());
        }
        for(int i=0;i<5;i++){
            assertEquals(i, effectHandler.getNumOfIslandStops());
            effectHandler.addIslandStop();
        }
    }

    @Test
    void setProfessorControl() {
        int i;

        for(i=1; i<4; i++) {
            effectHandler.setProfessorControl(i);
            assertEquals(i, effectHandler.getProfessorControl());
        }
    }

    @Test
    void getTwopoints() {
        assertEquals(0, effectHandler.getTwoPoints());
        effectHandler.setTwoPoints(1);
        assertEquals(1, effectHandler.getTwoPoints());
        effectHandler.setTwoPoints(0);
    }

    @Test
    void setTwopoints() {
        effectHandler.setNoTower(false);
        assertEquals(0, effectHandler.getTwoPoints());
        effectHandler.setTwoPoints(1);
        assertEquals(1, effectHandler.getTwoPoints());
        effectHandler.setTwoPoints(0);
    }

    @Test
    void isNotower() {
        effectHandler.setNoTower(false);
        assertFalse(effectHandler.isNoTower());
        effectHandler.setNoTower(true);
        assertTrue(effectHandler.isNoTower());
    }

    @Test
    void setNotower() {
        effectHandler.setNoTower(false);
        assertFalse(effectHandler.isNoTower());
        effectHandler.setNoTower(true);
        assertTrue(effectHandler.isNoTower());
    }

    @Test
    void isNocolor() {
        effectHandler.setNoColor(false);
        assertFalse(effectHandler.isNoColor());
        effectHandler.setNoColor(true);
        assertTrue(effectHandler.isNoColor());
    }

    @Test
    void setNocolor() {
        effectHandler.setNoColor(false);
        assertFalse(effectHandler.isNoColor());
        effectHandler.setNoColor(true);
        assertTrue(effectHandler.isNoColor());
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