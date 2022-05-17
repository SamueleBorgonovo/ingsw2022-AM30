package it.polimi.ingsw.model.player;

import it.polimi.ingsw.exceptions.OutOfCoinsException;

import java.io.Serializable;
import java.util.ArrayList;

public interface PlayerInterface{
    public Plance getPlance();

    public int getCoins();

    public void addCoins();

    public void removeCoins(int numOfCoins) throws OutOfCoinsException;

    public String getNickname();

    public Wizard getWizard();

    public ArrayList<Assistant> getAssistantCards();

    public PlayerState getPlayerState();

    public Assistant getLastassistantplayed();

}
