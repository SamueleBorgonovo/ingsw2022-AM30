package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;

public class Effect9 extends Effect {
    // Once a student color has been chosen, that color does not provide influence
    // in the influence calculation on the current turn.
    private int cost = 3;

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void effect(Game game, int playerID) {
        game.getEffectHandler().setNocolor(true);
        game.getEffectHandler().setStudent(game.chooseStudent());
    }

    @Override
    public void inizialize(Game game) {

    }

    @Override
    public void secondPartEffect(Game game, int playerID) {

    }
}
