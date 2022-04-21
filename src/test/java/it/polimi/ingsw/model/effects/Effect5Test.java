package it.polimi.ingsw.model.effects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Effect5Test {

    Effect effect5 = new Effect5();

    @Test
    void getCost() {
        assertEquals(2,effect5.getCost());
    }

    @Test
    void effect() {
    }
}