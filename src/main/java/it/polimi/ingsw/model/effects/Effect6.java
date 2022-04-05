package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;

public class Effect6 extends Effect {
    // When counting the influence on an Island (or group of Islands),
    // the Towers present are not counted.
    @Override
    public void effect(Game game, int playerID) {
        game.getVerifyType().setNotower(true);
    }
}