package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Plance;
import it.polimi.ingsw.model.player.Wizard;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayersView implements Serializable {
    private String nickname;
    private Wizard wizard;
    private Assistant lastassistantplayed;
    private ArrayList<Assistant> assistantCards = new ArrayList<>();
    private int coins;
    private Plance plance;

    public Plance getPlance() {
        return plance;
    }

    public void setPlance(Plance plance) {
        this.plance = plance;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    public void setLastassistantplayed(Assistant lastassistantplayed) {
        this.lastassistantplayed = lastassistantplayed;
    }

    public void setAssistantCards(ArrayList<Assistant> assistantCards) {
        this.assistantCards = assistantCards;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getNickname() {
        return nickname;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public Assistant getLastassistantplayed() {
        return lastassistantplayed;
    }

    public ArrayList<Assistant> getAssistantCards() {
        return assistantCards;
    }

    public int getCoins() {
        return coins;
    }
}
