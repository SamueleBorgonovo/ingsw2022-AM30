package it.polimi.ingsw.model.effects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Effect8Test {

    Effect effect8 = new Effect8();

    @Test
    void getCost() {
        assertEquals(2,effect8.getCost());
    }

    @Test
    void effect() {
    }
}