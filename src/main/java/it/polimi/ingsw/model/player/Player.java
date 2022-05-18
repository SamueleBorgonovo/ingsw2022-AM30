package it.polimi.ingsw.model.player;

import it.polimi.ingsw.exceptions.OutOfCoinsException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Player implements PlayerInterface, Serializable {
    private final String nickname;
    private int playerID;
    private PlayerState playerState;
    private Plance plance;
    private Wizard wizard;
    private int coins;
    private ArrayList<Assistant> assistantCards = new ArrayList<>();
    private Assistant lastassistantplayed;
    private boolean assistantPlayed;
    private boolean characterPlayed;

    public Player(String nickname){
        this.nickname = nickname;
        lastassistantplayed = null;
        coins = -100;
        assistantPlayed = false;
        playerState = PlayerState.WAITING;
        Collections.addAll(assistantCards, Assistant.values());
        characterPlayed = false;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setWizard(Wizard wizard){this.wizard=wizard;}

    public boolean getCharacterPlayed() {return characterPlayed;}

    public void setCharacterPlayed(boolean characterPlayed) {this.characterPlayed = characterPlayed;}

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setPlance(Plance plance) {
        this.plance = plance;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPlayerID() {
        return playerID;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) { this.playerState = playerState;}

    public Plance getPlance() {
        return plance;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public int getCoins(){
        return coins;
    }

    public void addCoins(){
        coins++;
    }

    public void removeCoins(int numOfCoins) throws OutOfCoinsException {
        if(coins>=numOfCoins)
            coins = coins - numOfCoins;
        else throw new OutOfCoinsException();
    }

    public void setCoins(){
        coins=0;
    }

    public ArrayList<Assistant> getAssistantCards() {return assistantCards; }

    //removeAssistant is used when an assistant is used
    public void removeAssistant(Assistant assistant){
        lastassistantplayed=assistant;
        assistantCards.remove(assistant);
    }

    public Assistant getLastassistantplayed(){return lastassistantplayed;}

    public boolean isAssistantPlayed() {
        return assistantPlayed;
    }

    public void setAssistantPlayed(boolean assistantPlayed) {
        this.assistantPlayed = assistantPlayed;
    }

    public void setLastassistantplayed(Assistant assistant){ lastassistantplayed=assistant;} //Only used for tests

    }





