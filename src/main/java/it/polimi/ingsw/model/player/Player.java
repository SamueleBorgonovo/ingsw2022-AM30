package it.polimi.ingsw.model.player;

import java.util.ArrayList;

public class Player {
    private final String nickname;
    private int playerID;
    private PlayerState playerState;
    private Plance plance;
    private final Wizard wizard;
    private int coins;
    private ArrayList<Assistant> assistantCards = new ArrayList<>();
    private Assistant lastassistantplayed;

    public Player(String nickname, Wizard wizard){
        this.nickname = nickname;
        this.wizard = wizard;
        coins = 0;
        lastassistantplayed = null;
        playerState = PlayerState.WAITING;
        for(Assistant assistant : Assistant.values())
            assistantCards.add(assistant);
    }

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




