package it.polimi.ingsw.model.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssistantTest {

    @Test
    void getValue() {
        int value = 1;

        for (Assistant assistant : Assistant.values()){
            assertEquals(value, assistant.getValue());
            value++;
        }
    }

    @Test
    void getMovement() {
        int movement = 2;

        for (Assistant assistant : Assistant.values()){
            assertEquals(movement/2,assistant.getMovement());
            movement++;
        }
    }
}