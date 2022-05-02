package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Wizard;
import it.polimi.ingsw.server.ClientHandler;

public class CreatePlayerInGameMessageToServer extends MessageToServer {
    String nickname;
    Wizard wizard;

    public void CreatePlayer(String nickname, Wizard wizard, GameMode gamemode,int numofplayers) {
        this.nickname = nickname;
        this.wizard = wizard;
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
