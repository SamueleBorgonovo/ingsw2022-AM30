package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.player.Assistant;

public class GameHandler {


    public int findgame(String nickname){
        return 1;
    }

    public void sendmessage(String nickname){
        int idgame=findgame(nickname);
        int idplayer;
       // getgame(idgame).chooseAssistant(idplayer);
    }


   /* public void chooseStudent(nickname,game){
        game=findgame(nickname);
        idplayer=findplayer(nickname);
        try{
            game.chooseStudent(id,student)
        }
        catch{
            Message InvalidPhasemessage = new InvalidPhaseMessage(sksjsjw);
            server.sendMessage(nickname,InvalidPhaseMa);
        }
    }


    */

    public void chooseAssistant(String nickname, Assistant assistant){

    }

}
