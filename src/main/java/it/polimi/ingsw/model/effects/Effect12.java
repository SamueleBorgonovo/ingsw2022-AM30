package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;

import java.util.ArrayList;

/**
 * Class used to put in the bag three students in the hall of the color of the student
 * chosen by the player using the effect. If any player has fewer than three students of that
 * color then he will have to put them all back in the bag
 */
public class Effect12 extends Effect{
    private PlayerState prevPlayerState;

    /**
     * Method used to get the cost of the Effect
     * @return the cost of the Effect
     */
    @Override
    public int getCost(){ return 3;}

    /**
     * Method used to get the name of the Effect
     * @return the name of the Effect
     */
    @Override
    public String getName(){ return "PICAROON";}

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
     * Method used by Effect1, Effect7 and Effect11 to initialize the
     * corresponding Array in EffectHandler class
     * @param game main class where there are EffectHandler class and Board class
     */
    @Override
    public void initialize(Game game) {}

    /**
     * Method used to put in the bag three students in the hall of the color of the
     * student chosen by the player using the effect. Then the player's previous state
     * is restored
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStudentEffectException if the chosen students are not valid
     */
    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {
        ArrayList<Student> studentToBag = new ArrayList<>();
        Student chosenColor;

        chosenColor = game.getEffectHandler().getStudentschoose().get(0);
        for(Player player : game.getListOfPlayers())
            for(int i = 0; i < 3 && player.getPlance().getNumberOfStudentHall(chosenColor)>0; i++){
                player.getPlance().removeStudentFromHall(chosenColor);
                studentToBag.add(chosenColor);
            }
        game.getBoard().addStudentBag(studentToBag);
        game.setCharacterInUse(null);
        game.getPlayer(playerID).setPlayerState(prevPlayerState);
    }

    /**
     * Method used to get the type of input required after using a character
     * @return the type of input required after using a character
     */
    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return TypeOfInputCharacter.STUDENT;
    }
}
