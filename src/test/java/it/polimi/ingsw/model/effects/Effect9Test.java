package it.polimi.ingsw.model.effects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Effect9Test {

    Effect effect9 = new Effect9();

    @Test
    void getCost() {
        assertEquals(3,effect9.getCost());
    }

    @Test
    void effect() {
    }
}