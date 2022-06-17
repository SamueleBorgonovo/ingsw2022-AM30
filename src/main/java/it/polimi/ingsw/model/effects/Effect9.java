package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.player.PlayerState;

/**
 * Class used to choose a student color. Once a student color has been chosen,
 * that color does not provide influence in the influence calculation on the current turn
 */
public class Effect9 extends Effect {
    private PlayerState prevPlayerState;

    @Override
    public int getCost() { return 3;}

    @Override
    public String getName(){ return "FUNGARO";}

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        prevPlayerState = game.getPlayer(playerID).getPlayerState();
        game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERSTUDENTSPHASE);
    }

    @Override
    public void initialize(Game game) {}

    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {
        game.getEffectHandler().setNocolor(true);
        game.getEffectHandler().setStudent(game.getEffectHandler().getStudentschoose().get(0));
        game.setCharacterInUse(null);
        game.getPlayer(playerID).setPlayerState(prevPlayerState);
    }

    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return TypeOfInputCharacter.STUDENT;
    }
}
