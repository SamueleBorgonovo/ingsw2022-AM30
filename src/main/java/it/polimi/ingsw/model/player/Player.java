package it.polimi.ingsw.model.player;

import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.exceptions.OutOfCoinsException;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class used to initialize and manage the player in the game
 */
public class Player implements PlayerInterface{
    private final String nickname;
    private int playerID;
    private PlayerState playerState;
    private Plance plance;
    private Wizard wizard;
    private int coins;
    private final ArrayList<Assistant> assistantCards = new ArrayList<>();
    private Assistant lastassistantplayed;
    private boolean characterPlayed;
    private boolean assistantPlayed;

    /**
     * Constructor Player instantiates first player's setup
     * @param nickname player's nickname
     */
    public Player(String nickname){
        this.nickname = nickname;
        lastassistantplayed = null;
        coins = -100;
        playerState = PlayerState.WAITING;
        Collections.addAll(assistantCards, Assistant.values());
        characterPlayed = false;
    }

    /**
     * Method setCoins sets the player's coins to a number
     * @param coins coins to set
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * Method setWizard sets player's wizard
     * @param wizard wizard chosen
     */
    public void setWizard(Wizard wizard){this.wizard=wizard;}

    /**
     * Method getCharacterPlayed returns if the player played a character in this turn
     * @return true if the player played a character in this turn, false otherwise
     */
    public boolean getCharacterPlayed() {return characterPlayed;}

    /**
     * Method setCharacterPlayed sets if the player played a character in this turn
     * @param characterPlayed true if the player played a character in this turn, false otherwise
     */
    public void setCharacterPlayed(boolean characterPlayed) {this.characterPlayed = characterPlayed;}

    /**
     * Method setPlayerID sets the player's id
     * @param playerID player's id
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * Method setPlance sets the player's plance
     * @param plance player's plance
     */
    public void setPlance(Plance plance) {
        this.plance = plance;
    }

    /**
     * Method getNickanme returns the player's nickname
     * @return the player's nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Method getPlayerID returns the player's id
     * @return the player's id
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Method getPlayerState return the player's current state
     * @return the player's current state
     */
    public PlayerState getPlayerState() {
        return playerState;
    }

    /**
     * Method setPlayerState sets the player's current state
     * @param playerState player's current state
     */
    public void setPlayerState(PlayerState playerState) { this.playerState = playerState;}

    /**
     * Method getPlance returns the player's plance
     * @return player's plance
     */
    public Plance getPlance() {
        return plance;
    }

    /**
     * Method getWizard returns the player's wizard
     * @return player's wizard
     */
    public Wizard getWizard() {
        return wizard;
    }

    /**
     * Method getCoins returns the player's coins
     * @return player's coins
     */
    public int getCoins(){
        return coins;
    }

    /**
     * Method addCoins add one coin to the player's coins
     */
    public void addCoins(){
        coins++;
    }

    /**
     * Method removeCoins removes coins from the player's coins
     * @param numOfCoins number of coins to remove
     * @throws OutOfCoinsException if numOfCoins is higher of current coins
     */
    public void removeCoins(int numOfCoins) throws OutOfCoinsException {
        if(coins>=numOfCoins)
            coins = coins - numOfCoins;
        else throw new OutOfCoinsException();
    }

    /**
     * Method getAssistantCards returns the player's available assistants
     * @return player's available assistants
     */
    public ArrayList<Assistant> getAssistantCards() {return assistantCards; }

    /**
     * Method removeAssistant removes an assistant from assistantCards
     * @param assistant assistant to remove
     */
    public void removeAssistant(Assistant assistant){
        lastassistantplayed=assistant;
        assistantCards.remove(assistant);
    }

    /**
     * Method getLastassistantplayed returns the player's last assistant played
     * @return player's last assistant played
     */
    public Assistant getLastassistantplayed(){return lastassistantplayed;}

    /**
     * Method setAssistantPlayed set the assistant played by the player
     * @param assistantPlayed the assistant played by the player
     */
    public void setAssistantPlayed(boolean assistantPlayed) {
        this.assistantPlayed = assistantPlayed;
    }

    /**
     * Method setLastassistantplayed sets the player's last assistant played
     * @param assistant player's last assistant played
     */
    public void setLastassistantplayed(Assistant assistant){ lastassistantplayed=assistant;} //Only used for tests

    /**
     * Method getPlayerView returns the player's playerView
     * @return player's playerView
     */
    public PlayerView getPlayerView(){
        return new PlayerView(nickname,wizard,lastassistantplayed,assistantCards,coins,plance.getPlanceView(),playerState);
    }
}





