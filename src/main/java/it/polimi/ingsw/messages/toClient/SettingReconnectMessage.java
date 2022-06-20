package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Wizard;

/**
 * Message to send the settings of the game when a player is reconnecting
 */
public class SettingReconnectMessage extends MessageToClient{
    private final GameMode gameMode;
    private final int numOfPlayers;
    private final Wizard wizard;

    public SettingReconnectMessage(GameMode gameMode,int numOfPlayers,Wizard wizard){
        this.gameMode=gameMode;
        this.numOfPlayers=numOfPlayers;
        this.wizard=wizard;
    }
    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Wizard getWizard(){return wizard;}
}
