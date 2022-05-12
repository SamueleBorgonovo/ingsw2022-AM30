package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidTurnException;
import it.polimi.ingsw.exceptions.InvalidIslandException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Professor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Effect3Test {

    Effect effect3 = new Effect3();

    @Test
    void getCost() {
        assertEquals(3,effect3.getCost());
    }

    @Test
    void effect() throws InvalidStopException, InvalidStudentEffectException, InvalidTurnException, InvalidIslandException {
        //This test check effect and also secondparteffect

        Game game = new Game(GameMode.EXPERTMODE,2, null);
        game.addPlayer("kek");
        Player player = game.getPlayer(1);
        player.setPlayerState(PlayerState.STUDENTPHASE);
        effect3.effect(game,player.getPlayerID());

        game.getBoard().getArchipelago().getSingleIsland(1).addStudent(Student.RED);
        player.getPlance().addProfessor(Professor.RED_DRAGON);
        Characters character = new Characters(effect3);
        game.setCharacterInUse(character);
        game.CharacterIslandPhase(player.getPlayerID(),1);

        int var=1;
        if(player.getPlayerState()!=PlayerState.STUDENTPHASE)
            var=-1;
        if(game.getBoard().getArchipelago().getSingleIsland(1).getTowerColor()!= Tower.WHITE)
            var=-2;

        assertEquals(1,var);
    }
}