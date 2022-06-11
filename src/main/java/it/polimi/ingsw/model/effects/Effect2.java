package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;

public class Effect2 extends Effect {
    // Take control of the professors even if you have the same number of students
    // in the hall as the player currently controlling them.
    private final TypeOfInputCharacter typeOfInputCharacter = TypeOfInputCharacter.NOTHING;

    @Override
    public int getCost(){ return 2;}

    @Override
    public String getName(){ return "FARMER";}

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        game.getEffectHandler().setProfessorcontroll(true);
        game.verifyProfessorControl();
        game.setCharacterInUse(null);
    }

    @Override
    public void initialize(Game game) {}

    @Override
    public void secondPartEffect(Game game, int playerID)  throws InvalidStudentEffectException {}

    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return this.typeOfInputCharacter;
    }
}
