package it.polimi.ingsw.model.effects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Effect4Test {

    Effect effect4 = new Effect4();

    @Test
    void getCost() {
        assertEquals(1,effect4.getCost());
    }

    @Test
    void effect() {
    }
}