package src.main.java.org.example;

import java.util.ArrayList;

public class Player {
    private final String nickname;
    private final int playerID;
    private PlayerState playerState;
    private Plance plance;
    private final Wizard wizard;
    private int coins = 0;
    private final ArrayList<Assistant> assistantCards = new ArrayList<Assistant>();

    public Player(String nickname, int playerID, PlayerState playerState, Dashboard dashboard, Plance plance, Wizard wizard) {
        this.nickname = nickname;
        this.playerID = playerID;
        this.playerState = playerState;
        this.dashboard = dashboard;
        this.plance = plance;
        this.wizard = wizard;
        this.coins= coins;
        this.assistanCards=AssistantCards;
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



}
