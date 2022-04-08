package it.polimi.ingsw.model.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotherNatureTest {

    @Test
    void isOn() {
        int islandID=3;
        MotherNature mothernature = new MotherNature((islandID));
        assertEquals(3,mothernature.isOn());
    }

    @Test
    void move01() {
        int islandID=12;
        MotherNature mothernature = new MotherNature(islandID);
        int numberofmovement = 12;
        for(int count=0;count<numberofmovement;count++)
            mothernature.move();
        assertEquals(12,mothernature.isOn());
    }
    @Test
    void move02(){
        int islandID=5;
        MotherNature mothernature = new MotherNature(islandID);
        int numberofmovement = 3;
        for(int count=0;count<numberofmovement;count++)
            mothernature.move();
        assertEquals(8,mothernature.isOn());
    }

    @Test
    void setMotherNature01(){
        int islandID=2;
        MotherNature mothernature = new MotherNature(islandID);
        int toset = 7;
        mothernature.setMotherNature(toset);
        assertEquals(7,mothernature.isOn());
    }
}