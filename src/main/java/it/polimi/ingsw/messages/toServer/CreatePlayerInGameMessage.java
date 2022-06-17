package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class CreatePlayerInGameMessage extends MessageToServer {
    String nickname;
    GameMode gamemode;
    int numofplayers;

    public CreatePlayerInGameMessage(String nickname, GameMode gamemode,int numofplayers) {
        this.nickname = nickname;
        this.gamemode = gamemode;
        this.numofplayers = numofplayers;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getMessageHandler().process(this, clientHandler);
    }

    public String getNickname() {
        return nickname;
    }

    public GameMode getGamemode() {
        return gamemode;
    }

    public int getNumofplayers() {
        return numofplayers;
    }
}
