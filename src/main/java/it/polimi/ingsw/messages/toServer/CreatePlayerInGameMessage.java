package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Wizard;
import it.polimi.ingsw.server.ClientHandler;

public class CreatePlayerInGameMessage extends MessageToServer {
    String nickname;
    Wizard wizard;
    GameMode gamemode;
    int numofplayers;

    public void CreatePlayer(String nickname, Wizard wizard, GameMode gamemode,int numofplayers) {
        this.nickname = nickname;
        this.wizard = wizard;
        this.gamemode = gamemode;
        this.numofplayers = numofplayers;
    }

    public void action(ClientHandler clienthandler) {
       /* id=gamehandler.getid(nickname)
        gamehandler.chooseStudent(id,gamehandler.getgame(nickname));

        */

    }

    public String getNickname() {
        return this.nickname;
    }
}
