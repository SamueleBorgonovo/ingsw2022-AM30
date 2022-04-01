package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Player {
    private final String nickname;
    private final int playerID;
    private PlayerState playerState;
    private Plance plance;
    private final Wizard wizard;
    private int coins;
    private ArrayList<Assistant> assistantCards = new ArrayList<>();

    public Player(String nickname, int playerID, PlayerState playerState, Plance plance, Wizard wizard, int coins, ArrayList<Assistant> assistantCards){
        this.nickname = nickname;
        this.playerID = playerID;
        this.playerState = playerState;
        this.plance = plance;
        this.wizard = wizard;
        this.coins= coins;
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

    public ArrayList<Assistant> getAssistantCards() {
        return assistantCards;
    }

    public void removeAssistant(Assistant assistant){
        assistantCards.remove(assistant);
    }



}




