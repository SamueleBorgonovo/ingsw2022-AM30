package it.polimi.ingsw.model.player;

import java.util.ArrayList;

public class Player {
    private final String nickname;
    private final int playerID;
    private PlayerState playerState;
    private Plance plance;
    private final Wizard wizard;
    private int coins;
    private ArrayList<Assistant> assistantCards = new ArrayList<>();
    private Assistant lastassistantplayed;

    public Player(String nickname, int playerID, PlayerState playerState, Plance plance, Wizard wizard,ArrayList<Assistant> assistantCards){
        this.nickname = nickname;
        this.playerID = playerID;
        this.playerState = playerState;
        this.plance = plance;
        this.wizard = wizard;
        this.assistantCards=assistantCards;
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

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

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

    public void removeCoins(int numOfCoins){
        coins = coins - numOfCoins;
    }

    public ArrayList<Assistant> getAssistantCards() {return assistantCards; }

    //removeAssistant is used when an assistant is used
    public void removeAssistant(Assistant assistant){
        lastassistantplayed=assistant;
        assistantCards.remove(assistant);
    }

    public Assistant getLastassistantplayed(){return lastassistantplayed;}



}




