package it.polimi.ingsw.model.effects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Effect2Test {

    Effect effect2 = new Effect2();

    @Test
    void getCost() {
        assertEquals(2,effect2.getCost());
    }

    @Test
    void effect() {
    }
}