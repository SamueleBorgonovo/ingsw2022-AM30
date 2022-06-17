package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;

/**
 * Class used to counting the influence on an Island (or group of Islands)
 * without counting the Towers present on island
 */
public class Effect6 extends Effect {
    @Override
    public int getCost(){ return 3;}

    @Override
    public String getName(){ return "CENTAUR";}

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        game.getEffectHandler().setNotower(true);
        game.setCharacterInUse(null);
    }

    @Override
    public void initialize(Game game) {}

    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {}

    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return TypeOfInputCharacter.NOTHING;
    }
}