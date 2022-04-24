package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.player.PlayerState;

public class Effect9 extends Effect {
    // Once a student color has been chosen, that color does not provide influence
    // in the influence calculation on the current turn.
    private PlayerState prevPlayerState;

    @Override
    public int getCost() { return 3;}

    @Override
    public void effect(Game game, int playerID) {
        prevPlayerState = game.getListOfPlayers().get(playerID).getPlayerState();
        game.getListOfPlayers().get(playerID).setPlayerState(PlayerState.CHARACTHERSTUDENTSPHASE);
    }

    @Override
    public void inizialize(Game game) {}

    @Override
    public void secondPartEffect(Game game, int playerID) {
        game.getEffectHandler().setNocolor(true);
        game.getEffectHandler().setStudent(game.getEffectHandler().getStudentschoose().get(0));
        game.setCharacterInUse(null);
        game.getListOfPlayers().get(playerID).setPlayerState(prevPlayerState);
    }
}
