package it.polimi.ingsw;

import it.polimi.ingsw.model.MotherNature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMotherNature {

    @Test
    public void Testmove(){
        int islandID=12;
        MotherNature mothernature = new MotherNature(islandID);
        mothernature.move(13);
        assertEquals(12,mothernature.isOn());


    }
}
