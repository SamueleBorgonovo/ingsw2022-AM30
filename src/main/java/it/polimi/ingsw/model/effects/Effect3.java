package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.player.PlayerState;

public class Effect3 extends Effect {
    // Choose an island and calculate the influence as if
    // Mother Nature had finished her movement there.
    private PlayerState prevPlayerState;

    @Override
    public int getCost(){ return 3;}

    @Override
    public String getName(){ return "HERALD";}

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        prevPlayerState = game.getPlayer(playerID).getPlayerState();
        game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERISLANDPHASE);
    }

    @Override
    public void inizialize(Game game) {}

    @Override
    public void secondPartEffect(Game game, int playerID)  throws InvalidStudentEffectException {
        game.verifyIslandInfluence(game.getBoard().getArchipelago().getSingleIsland(game.getEffectHandler().getIslandIDchoose()).getIslandID());
        game.setCharacterInUse(null);
        game.getPlayer(playerID).setPlayerState(prevPlayerState);
    }

    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return TypeOfInputCharacter.ISLAND;
    }
}
