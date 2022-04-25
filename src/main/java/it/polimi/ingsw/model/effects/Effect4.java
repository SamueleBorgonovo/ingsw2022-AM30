package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;

public class Effect4 extends Effect{

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public void effect(Game game, int playerID) {
        game.getEffectHandler().setTwomoremoves(true);

    }

    @Override
    public void inizialize(Game game) {

    }

    @Override
    public void secondPartEffect(Game game, int playerID) {

    }
}
