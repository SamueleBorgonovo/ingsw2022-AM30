package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.effects.Effect;

public class Character {
    private int cost;
    private boolean used = false;
    private final Effect effect;

    public Character(Effect effect) {
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
