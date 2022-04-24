package it.polimi.ingsw.model.game;

import org.junit.jupiter.api.Test;

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
}