package it.polimi.ingsw.model.player;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfessorTest {

    @Test
    void getText(){
        Professor prof=Professor.GREEN_FROG;
        assertEquals("Green",prof.getText(prof));
        prof=Professor.RED_DRAGON;
        assertEquals("Red",prof.getText(prof));
        prof=Professor.YELLOW_ELF;
        assertEquals("Yellow",prof.getText(prof));
        prof=Professor.PINK_FAIRY;
        assertEquals("Pink",prof.getText(prof));
        prof=Professor.BLUE_UNICORN;
        assertEquals("Blue",prof.getText(prof));
    }
}
