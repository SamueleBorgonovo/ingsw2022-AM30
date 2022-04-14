package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;

public class Effect6 extends Effect {
    // When counting the influence on an Island (or group of Islands),
    // the Towers present are not counted.
    private int cost = 3;

    @Override
    public int getCost(){ return cost;}
    @Override
    public void effect(Game game, int playerID) {
        game.getVerifyType().setNotower(true);
    }
}