package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidTurnException;
import it.polimi.ingsw.exceptions.WrongValueException;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Effect4Test {

    Effect effect4 = new Effect4();

    @Test
    void getCost() {
        assertEquals(1,effect4.getCost());
    }

    @Test
    void effect() throws InvalidStopException, InvalidTurnException, WrongValueException {

        Game game = new Game(GameMode.EXPERTMODE,2);
        game.addPlayer("kek",null);
        game.getPlayer(1).setPlayerState(PlayerState.MOTHERNATUREPHASE);
        int mtpos = game.getBoard().getArchipelago().getMothernature().isOn();
        game.getPlayer(1).setLastassistantplayed(Assistant.CAT);
        Characters character = new Characters(effect4);
        game.setCharacterInUse(character);
        effect4.effect(game,1);
        game.moveMotherNature(1,4);
        assertEquals(mtpos+4,game.getBoard().getArchipelago().getMothernature().isOn());
    }
}