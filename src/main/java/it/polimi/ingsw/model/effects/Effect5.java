package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.player.PlayerState;

/**
 * Class used to place a ban on an island of your choice.
 * The first time Mother Nature moves there the ban falls and does not calculate the influence there.
 * The bans can be a maximum of 4
 */
public class Effect5 extends Effect {
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
    public String getName(){ return "GRANDMA";}

    /**
     * Method used to save the player's current state and change it so that
     * he can choose an island
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStopException if the four stops have already been used
     */
    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException{
        if(game.getEffectHandler().getNumOfIslandStops()>0) {
            prevPlayerState = game.getPlayer(playerID).getPlayerState();
            game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERISLANDPHASE);
        }
        else
             throw new InvalidStopException();
    }

    /**
     * Method used by Effect1, Effect7 and Effect11 to initialize the
     * corresponding Array in EffectHandler class
     * @param game main class where there are EffectHandler class and Board class
     */
    @Override
    public void initialize(Game game) {}

    /**
     * Method used to place a stop on an island chosen by the player.
     * Then the player's previous state is restored
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStudentEffectException if the chosen students are not valid
     */
    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {
        game.getBoard().getArchipelago().getSingleIsland(game.getEffectHandler().getIslandIDchoose()).setStop(true);
        game.getEffectHandler().removeIslandStop();
        game.setCharacterInUse(null);
        game.getPlayer(playerID).setPlayerState(prevPlayerState);
    }

    /**
     * Method used to get the type of input required after using a character
     * @return the type of input required after using a character
     */
    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return TypeOfInputCharacter.ISLAND;
    }
}
