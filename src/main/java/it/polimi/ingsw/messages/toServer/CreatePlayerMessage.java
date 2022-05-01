package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.model.player.Wizard;
import it.polimi.ingsw.server.ClientHandler;

public class CreatePlayerMessage extends Message {
    String nickname;
    Wizard wizard;
    GameHandler gamehandler;

    public void CreatePlayer(String nickname, Wizard wizard, GameHandler gamehandler) {
        this.nickname = nickname;
        this.wizard = wizard;
        this.gamehandler = gamehandler;
    }

    public void action(ClientHandler clienthandler) {
       /* id=gamehandler.getid(nickname)
        gamehandler.chooseStudent(id,gamehandler.getgame(nickname));




        */

    }
}
