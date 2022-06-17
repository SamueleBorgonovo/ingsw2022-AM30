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

    @Override
    public int getCost(){ return 2;}

    @Override
    public String getName(){ return "GRANDMA";}

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException{
        if(game.getEffectHandler().getNumofislandstops()>0) {
            prevPlayerState = game.getPlayer(playerID).getPlayerState();
            game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERISLANDPHASE);
        }
        else
             throw new InvalidStopException();
    }

    @Override
    public void initialize(Game game) {

    }

    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {
        game.getBoard().getArchipelago().getSingleIsland(game.getEffectHandler().getIslandIDchoose()).setStop(true);
        game.getEffectHandler().removeislandstop();
        game.setCharacterInUse(null);
        game.getPlayer(playerID).setPlayerState(prevPlayerState);
    }

    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return TypeOfInputCharacter.ISLAND;
    }
}
