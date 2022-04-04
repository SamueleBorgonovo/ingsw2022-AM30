package it.polimi.ingsw.model;

public class Character {
    private int cost;
    private boolean used = false;
    private final Effect effect;

    public Character(int cost, Effect effect) {
        this.cost = cost;

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
