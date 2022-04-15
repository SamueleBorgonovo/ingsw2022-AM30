package it.polimi.ingsw.model.effects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Effect3Test {

    Effect effect3 = new Effect3();

    @Test
    void getCost() {
        assertEquals(3,effect3.getCost());
    }

    @Test
    void effect() {
    }
}