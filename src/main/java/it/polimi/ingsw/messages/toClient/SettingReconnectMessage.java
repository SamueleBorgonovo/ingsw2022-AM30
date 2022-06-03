package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Wizard;

public class SettingReconnectMessage extends MessageToClient{
    private GameMode gameMode;
    private int numOfPlayers;
    private Wizard wizard;

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
