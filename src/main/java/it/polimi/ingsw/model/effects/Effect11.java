package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;

/**
 * Class used to put a student of the card chosen by the player on the player's hall
 */
public class Effect11 extends Effect{
    private PlayerState prevPlayerState;

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
    public String getName(){ return "PRINCESS";}

    /**
     * Method used to save the player's current state and change it so that
     * he can choose a student
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStopException if the four stops of Effect5 have already been used
     */
    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        prevPlayerState = game.getPlayer(playerID).getPlayerState();
        game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERSTUDENTSPHASE);
    }

    /**
     * Method used to take and remove 4 students from the Bag and use them to fill
     * the effect11Students array in EffectHandler
     * @param game main class where there are EffectHandler class and Board class
     */
    @Override
    public void initialize(Game game) {
        game.getEffectHandler().setEffect11students(game.getBoard().getAndRemoveRandomBagStudent(4));
    }

    /**
     * Method used to put a student of the card chosen by the player on the player's
     * hall. The chosen student of the card is then replaced with a random student
     * of the bag and finally the player's previous state is restored
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStudentEffectException if the chosen students are not valid
     */
    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {
        Student chosenStudent = game.getEffectHandler().getStudentschoose().get(0);

        if(game.getEffectHandler().getEffect11students().contains(chosenStudent)){
            game.getPlayer(playerID).getPlance().addStudentHall(chosenStudent);
            game.getEffectHandler().removeStudentFromEffect11students(chosenStudent);
            game.getEffectHandler().addStudentInEffect11students(game.getBoard().getAndRemoveRandomBagStudent(1).get(0));
            game.setCharacterInUse(null);
            game.getPlayer(playerID).setPlayerState(prevPlayerState);
        }
        else
            throw new InvalidStudentEffectException();
    }

    /**
     * Method used to get the type of input required after using a character
     * @return the type of input required after using a character
     */
    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return TypeOfInputCharacter.EFFECT11INPUT;
    }
}
