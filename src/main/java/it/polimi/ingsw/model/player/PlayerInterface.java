package it.polimi.ingsw.model.player;

import it.polimi.ingsw.exceptions.OutOfCoinsException;

import java.util.ArrayList;

/**
 * Method used to control player's methods calls
 */
public interface PlayerInterface{
    Plance getPlance();

    int getCoins();

    void addCoins();

    void removeCoins(int numOfCoins) throws OutOfCoinsException;

    String getNickname();

    Wizard getWizard();

    ArrayList<Assistant> getAssistantCards();

    PlayerState getPlayerState();

    Assistant getLastassistantplayed();


}
