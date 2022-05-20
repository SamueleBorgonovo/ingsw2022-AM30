package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Effect8Test {

    Effect effect8 = new Effect8();

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
        assertEquals(2,effect8.getCost());
    }

    @Test
    void effect() throws InvalidStopException {
        // Test 2 Players Game
        for(int i = 1; i < 3; i++) {
            effect8.effect(game2players, i);
            assertEquals(game2players.getEffectHandler().getTwopoints(),i);
        }

        // Test 3 Players Game
        for(int i = 1; i < 4; i++) {
            effect8.effect(game3players, i);
            assertEquals(game3players.getEffectHandler().getTwopoints(),i);
        }
    }
}