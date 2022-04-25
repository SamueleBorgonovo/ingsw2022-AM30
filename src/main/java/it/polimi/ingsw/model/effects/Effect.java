package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.WrongStudentEffectException;
import it.polimi.ingsw.model.game.Game;

public abstract class Effect {

    public abstract int getCost();
    public abstract void effect(Game game, int playerID) throws InvalidStopException;
    public abstract void inizialize(Game game);
    public abstract void secondPartEffect(Game game, int playerID) throws WrongStudentEffectException;

}
