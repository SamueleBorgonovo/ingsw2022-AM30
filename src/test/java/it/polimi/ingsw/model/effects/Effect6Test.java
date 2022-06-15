package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Effect6Test {

    Effect effect6 = new Effect6();

    Game game2players = new Game(GameMode.EXPERTMODE,2);
    Game game3players = new Game(GameMode.EXPERTMODE,3);

    @BeforeEach
    void init() {
        game2players.addPlayer("Daniele");
        game2players.addPlayer("Giuseppe");
        game3players.addPlayer("Daniele");
        game3players.addPlayer("Giuseppe");
        game3players.addPlayer("Samuele");
    }

    @Test
    void getCost() {
        assertEquals(3,effect6.getCost());
    }

    @Test
    void effect() throws InvalidStopException {
        // Test 2 Players Game
        assertFalse(game2players.getEffectHandler().isNotower());
        effect6.effect(game2players,1);
        assertTrue(game2players.getEffectHandler().isNotower());

        // Test 3 Players Game
        assertFalse(game3players.getEffectHandler().isNotower());
        effect6.effect(game3players,1);
        assertTrue(game3players.getEffectHandler().isNotower());
    }

    @Test
    void getName(){
        assertEquals("CENTAUR",effect6.getName());
    }
}