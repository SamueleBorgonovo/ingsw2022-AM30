package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Wizard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Effect6Test {

    Effect effect6 = new Effect6();

    Game game2players = new Game(GameMode.EXPERTMODE,2);
    Game game3players = new Game(GameMode.EXPERTMODE,3);

    @BeforeEach
    void init() {
        game2players.addPlayer("Daniele", Wizard.WIZARDYELLOW);
        game2players.addPlayer("Giuseppe", Wizard.WIZARDBLUE);
        game3players.addPlayer("Daniele", Wizard.WIZARDYELLOW);
        game3players.addPlayer("Giuseppe", Wizard.WIZARDBLUE);
        game3players.addPlayer("Samuele", Wizard.WIZARDGREEN);
    }

    @Test
    void getCost() {
        assertEquals(3,effect6.getCost());
    }

    @Test
    void effect() {
        // Test 2 Players Game
        assertFalse(game2players.getEffectHandler().isNotower());
        effect6.effect(game2players,1);
        assertTrue(game2players.getEffectHandler().isNotower());

        // Test 3 Players Game
        assertFalse(game3players.getEffectHandler().isNotower());
        effect6.effect(game3players,1);
        assertTrue(game3players.getEffectHandler().isNotower());
    }
}