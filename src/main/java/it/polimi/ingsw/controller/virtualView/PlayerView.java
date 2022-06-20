package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class PlayerView is used to send the updated player to the client from the server
 */
public class PlayerView implements Serializable {
    private final String nickname;
    private final Wizard wizard;
    private final Assistant lastassistantplayed;
    private final ArrayList<Assistant> assistantCards;
    private final int coins;
    private final PlanceView plance;
    private final PlayerState playerState;

    /**
     * Constructor PlayerView sets the instances of all attributes of the player
     * @param nickname player's nickname
     * @param wizard player's wizard
     * @param lastassistantplayed player's last assistant played
     * @param assistantCards player's available assistants
     * @param coins player's coin
     * @param plance player's plance
     * @param playerState player's state
     */
    public PlayerView(String nickname, Wizard wizard, Assistant lastassistantplayed, ArrayList<Assistant> assistantCards, int coins, PlanceView plance,PlayerState playerState){
        this.nickname=nickname;
        this.wizard=wizard;
        this.lastassistantplayed=lastassistantplayed;
        this.assistantCards=assistantCards;
        this.coins=coins;
        this.plance=plance;
        this.playerState=playerState;
    }

    /**
     * Method getPlance returns the player's plance
     * @return the player's plance
     */
    public PlanceView getPlance() {
        return plance;
    }

    /**
     * Method getNickname returns the player's nickname
     * @return the player's nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Method getWizard returns the player's wizard
     * @return the player's wizard
     */
    public Wizard getWizard() {
        return wizard;
    }

    /**
     * Method getLastassistantplayed returns the player's last assistant played
     * @return the player's last assistant played
     */
    public Assistant getLastassistantplayed() {
        return lastassistantplayed;
    }

    /**
     * Method getAssistantCards returns the player's available assistants
     * @return the player's available assistants
     */
    public ArrayList<Assistant> getAssistantCards() {
        return assistantCards;
    }

    /**
     * Method getCoins returns the player's coins
     * @return the player's coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Method getPlayerState returns the player's current state
     * @return the player's current state
     */
    public PlayerState getPlayerState() { return playerState; }
}
