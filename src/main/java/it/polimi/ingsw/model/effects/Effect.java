package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;

import java.io.Serializable;

/**
 * Abstract Class used to define the methods of the Effect subclasses
 */
public abstract class Effect implements Serializable {

    /**
     * Method used to get the cost of the Effect
     * @return the cost of the Effect
     */
    public abstract int getCost();

    /**
     * Method used to get the name of the Effect
     * @return the name of the Effect
     */
    public abstract String getName();

    /**
     * Method used by subclasses to implement the effect of the character card
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStopException if the four stops of Effect5 have already been used
     */
    public abstract void effect(Game game, int playerID) throws InvalidStopException;

    /**
     * Method used by Effect1, Effect7 and Effect11 to initialize the
     * corresponding Array in EffectHandler class
     * @param game main class where there are EffectHandler class and Board class
     */
    public abstract void initialize(Game game);

    /**
     * Method used by subclasses in the event that the user is required to choose
     * an island or students
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStudentEffectException if the chosen students are not valid
     */
    public abstract void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException;

    /**
     * Method used to get the type of input required after using a character
     * @return the type of input required after using a character
     */
    public abstract TypeOfInputCharacter getTypeOfInputCharacter();
}
