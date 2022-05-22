package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.game.GameMode;

public class SettingReconnectMessage extends MessageToClient{
    private GameMode gameMode;
    private int numOfPlayers;
    public SettingReconnectMessage(GameMode gameMode,int numOfPlayers){
        this.gameMode=gameMode;
        this.numOfPlayers=numOfPlayers;
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
}
