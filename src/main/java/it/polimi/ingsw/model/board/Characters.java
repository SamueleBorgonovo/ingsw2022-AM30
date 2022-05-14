package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.effects.Effect;
import it.polimi.ingsw.model.game.Student;

public class Characters {
    private int cost;
    private boolean used = false;
    private final Effect effect;

    public Characters(Effect effect) {
        cost = effect.getCost();
        this.effect = effect;
    }

    public int getCost() {
        return cost;
    }

    public boolean isUsed() {
        return used;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setUsed(boolean used) {
        this.used = used;
        cost++;
    }
}
