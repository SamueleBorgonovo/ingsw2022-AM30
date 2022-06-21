package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;

/**
 * Class used to take control of the professors even if you have the same number of students
 * in the hall as the player currently controlling them
 */
public class Effect2 extends Effect {

    /**
     * Method used to get the cost of the Effect
     * @return the cost of the Effect
     */
    @Override
    public int getCost(){ return 2;}

    /**
     * Method used to get the name of the Effect
     * @return the name of the Effect
     */
    @Override
    public String getName(){ return "FARMER";}

    /**
     * Method used to gain control of the professors even if a player has
     * the same number of students in the hall
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStopException if the four stops of Effect5 have already been used
     */
    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        game.getEffectHandler().setProfessorcontroll(true);
        game.verifyProfessorControl();
        game.setCharacterInUse(null);
    }

    /**
     * Method used by Effect1, Effect7 and Effect11 to initialize the
     * corresponding Array in EffectHandler class
     * @param game main class where there are EffectHandler class and Board class
     */
    @Override
    public void initialize(Game game) {}

    /**
     * Method used by other subclasses of Effect in the event that the user is required to choose
     * an island or students
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStudentEffectException if the chosen students are not valid
     */
    @Override
    public void secondPartEffect(Game game, int playerID)  throws InvalidStudentEffectException {}

    /**
     * Method used to get the type of input required after using a character
     * @return the type of input required after using a character
     */
    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return TypeOfInputCharacter.NOTHING;
    }
}
