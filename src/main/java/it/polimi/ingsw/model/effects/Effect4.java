package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;

public class Effect4 extends Effect{

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public String getName(){ return "POSTMAN";}

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        game.getEffectHandler().setTwomoremoves(true);
        game.setCharacterInUse(null);
    }

    @Override
    public void inizialize(Game game) {}

    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {}

    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return null;
    }
}
