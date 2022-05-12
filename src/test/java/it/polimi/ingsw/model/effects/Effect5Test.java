package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Tower;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.PlayerState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Effect5Test {

    Effect effect5 = new Effect5();

    @Test
    void getCost() {
        assertEquals(2,effect5.getCost());
    }

    @Test
    void effect() throws InvalidStopException, InvalidStudentEffectException, InvalidTurnException, InvalidIslandException, InvalidValueException {
        //This test checks effect and also secondparteffect

        Game game = new Game(GameMode.EXPERTMODE,2, null);
        game.addPlayer("kek");
        game.addPlayer("kek2");
        game.getPlayer(1).setPlayerState(PlayerState.MOTHERNATUREPHASE);
        int mtpos = game.getBoard().getArchipelago().getMothernature().isOn();
        game.getPlayer(1).setLastassistantplayed(Assistant.CAT);
        if(mtpos==12) mtpos=0;
        game.getBoard().getArchipelago().getSingleIsland(mtpos + 1).setTowerColor(Tower.BLACK);
        effect5.effect(game,1);
        Characters character = new Characters(effect5);
        game.setCharacterInUse(character);
        game.CharacterIslandPhase(1,mtpos+1);
        int var=1;
        if(!game.getBoard().getArchipelago().getSingleIsland(mtpos+1).isStop())
            var=-1;

        game.moveMotherNature(1,1);

        if(game.getBoard().getArchipelago().getSingleIsland(mtpos+1).getTowerColor()!=Tower.BLACK)
            var=-2;
        if(game.getBoard().getArchipelago().getSingleIsland(mtpos+1).isStop())
            var=-3;

        assertEquals(1,var);



    }
}