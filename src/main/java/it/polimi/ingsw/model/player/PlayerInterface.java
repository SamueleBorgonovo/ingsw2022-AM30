package it.polimi.ingsw.model.player;

import it.polimi.ingsw.exceptions.OutOfCoinsException;

public interface PlayerInterface {
    public Plance getPlance();

    public int getCoins();

    public void addCoins();

    public void removeCoins(int numOfCoins) throws OutOfCoinsException;

    public String getNickname();

    public Wizard getWizard();
}
