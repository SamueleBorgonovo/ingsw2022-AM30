package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;

public class Effect6 extends Effect {
    // When counting the influence on an Island (or group of Islands),
    // the Towers present are not counted.
    private final TypeOfInputCharacter typeOfInputCharacter = TypeOfInputCharacter.NOTHING;
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
        return this.typeOfInputCharacter;
    }
}