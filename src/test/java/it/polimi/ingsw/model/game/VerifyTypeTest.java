package it.polimi.ingsw.model.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifyTypeTest {
    VerifyType verifyType= new VerifyType();

    @Test
    void getNumofislandstops() {
        assertEquals(4,verifyType.getNumofislandstops());
        for(int i=3;i>=0;i--) {
            verifyType.removeislandstop();
            assertEquals(i, verifyType.getNumofislandstops());
        }
        for(int i=0;i<5;i++){
            assertEquals(i,verifyType.getNumofislandstops());
            verifyType.addislandstop();
        }
    }

    @Test
    void addislandstop() {
        assertEquals(4,verifyType.getNumofislandstops());
        for(int i=3;i>=0;i--) {
            verifyType.removeislandstop();
            assertEquals(i, verifyType.getNumofislandstops());
        }
        for(int i=0;i<5;i++){
            assertEquals(i,verifyType.getNumofislandstops());
            verifyType.addislandstop();
        }
    }

    @Test
    void removeislandstop() {
        assertEquals(4,verifyType.getNumofislandstops());
        for(int i=3;i>=0;i--) {
            verifyType.removeislandstop();
            assertEquals(i, verifyType.getNumofislandstops());
        }
        for(int i=0;i<5;i++){
            assertEquals(i,verifyType.getNumofislandstops());
            verifyType.addislandstop();
        }
    }

    @Test
    void isProfessorcontroll() {
        verifyType.setProfessorcontroll(false);
        assertFalse(verifyType.isProfessorcontroll());
        verifyType.setProfessorcontroll(true);
        assertTrue(verifyType.isProfessorcontroll());
    }

    @Test
    void setProfessorcontroll() {
        verifyType.setProfessorcontroll(false);
        assertFalse(verifyType.isProfessorcontroll());
        verifyType.setProfessorcontroll(true);
        assertTrue(verifyType.isProfessorcontroll());
    }

    @Test
    void isTwopoints() {
        verifyType.setTwopoints(false);
        assertFalse(verifyType.isTwopoints());
        verifyType.setTwopoints(true);
        assertTrue(verifyType.isTwopoints());
    }

    @Test
    void setTwopoints() {verifyType.setNotower(false);
        verifyType.setTwopoints(false);
        assertFalse(verifyType.isTwopoints());
        verifyType.setTwopoints(true);
        assertTrue(verifyType.isTwopoints());
    }

    @Test
    void isNotower() {
        verifyType.setNotower(false);
        assertFalse(verifyType.isNotower());
        verifyType.setNotower(true);
        assertTrue(verifyType.isNotower());
    }

    @Test
    void setNotower() {
        verifyType.setNotower(false);
        assertFalse(verifyType.isNotower());
        verifyType.setNotower(true);
        assertTrue(verifyType.isNotower());
    }

    @Test
    void isNocolor() {
        verifyType.setNocolor(false);
        assertFalse(verifyType.isNocolor());
        verifyType.setNocolor(true);
        assertTrue(verifyType.isNocolor());
    }

    @Test
    void setNocolor() {
        verifyType.setNocolor(false);
        assertFalse(verifyType.isNocolor());
        verifyType.setNocolor(true);
        assertTrue(verifyType.isNocolor());
    }

    @Test
    void getStudent() {
        for(int i=0; i<5;i++) {
            verifyType.setStudent(Student.values()[i]);
            assertEquals(Student.values()[i], verifyType.getStudent());
        }
    }

    @Test
    void setStudent() {
        for(int i=0; i<5;i++) {
            verifyType.setStudent(Student.values()[i]);
            assertEquals(Student.values()[i], verifyType.getStudent());
        }
    }
}