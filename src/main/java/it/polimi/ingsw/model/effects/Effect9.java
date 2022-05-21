package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.player.PlayerState;

public class Effect9 extends Effect {
    // Once a student color has been chosen, that color does not provide influence
    // in the influence calculation on the current turn.
    private PlayerState prevPlayerState;
    private final TypeOfInputCharacter typeOfInputCharacter = TypeOfInputCharacter.STUDENT;

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
    public void inizialize(Game game) {}

    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {
        game.getEffectHandler().setNocolor(true);
        game.getEffectHandler().setStudent(game.getEffectHandler().getStudentschoose().get(0));
        game.setCharacterInUse(null);
        game.getPlayer(playerID).setPlayerState(prevPlayerState);
    }

    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return this.typeOfInputCharacter;
    }
}
