package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;

public class Effect8 extends Effect {
    // On the current turn, you have two more points available when calculating the influence.
    private final TypeOfInputCharacter typeOfInputCharacter = TypeOfInputCharacter.NOTHING;
    @Override
    public int getCost(){ return 2;}

    @Override
    public String getName(){ return "KNIGHT";}

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        game.getEffectHandler().setTwopoints(playerID);
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
