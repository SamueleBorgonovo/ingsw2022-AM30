package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.WrongStudentEffectException;
import it.polimi.ingsw.model.game.Game;

public class Effect4 extends Effect{

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        game.getEffectHandler().setTwomoremoves(true);
        game.setCharacterInUse(null);
    }

    @Override
    public void inizialize(Game game) {}

    @Override
    public void secondPartEffect(Game game, int playerID) throws WrongStudentEffectException {}
}
