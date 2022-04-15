package it.polimi.ingsw.model.effects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Effect6Test {

    Effect effect6 = new Effect6();

    @Test
    void getCost() {
        assertEquals(3,effect6.getCost());
    }

    @Test
    void effect() {
    }
}