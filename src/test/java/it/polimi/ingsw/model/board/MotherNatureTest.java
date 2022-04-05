package it.polimi.ingsw.model.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotherNatureTest {

    @Test
    void isOn() {
    }

    @Test
    void move() {
        int islandID=12;
        MotherNature mothernature = new MotherNature(islandID);
        mothernature.move(13);
        assertEquals(12,mothernature.isOn());
    }
}