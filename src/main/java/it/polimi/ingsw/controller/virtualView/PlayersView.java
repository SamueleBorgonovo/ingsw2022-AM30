package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Plance;
import it.polimi.ingsw.model.player.Wizard;

import java.awt.event.WindowAdapter;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayersView implements Serializable {
    private String nickname;
    private Wizard wizard;
    private Assistant lastassistantplayed;
    private ArrayList<Assistant> assistantCards = new ArrayList<>();
    private int coins;
    private PlanceView plance;

    public PlayersView(String nickname, Wizard wizard,Assistant lastassistantplayed,ArrayList<Assistant> assistantCards,int coins,PlanceView plance){
        this.nickname=nickname;
        this.wizard=wizard;
        this.lastassistantplayed=lastassistantplayed;
        this.assistantCards=assistantCards;
        this.coins=coins;
        this.plance=plance;
    }

    public PlanceView getPlance() {
        return plance;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
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
