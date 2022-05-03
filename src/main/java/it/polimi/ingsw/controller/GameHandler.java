package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.InvalidTurnException;
import it.polimi.ingsw.exceptions.WrongAssistantException;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.GameState;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Wizard;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.ClientHandlerInterface;

import java.util.HashMap;

public class GameHandler {
    HashMap<String, GameInterface> playertoGameMap = new HashMap<>();//Map that find the game of a player's nickname
    HashMap<String, Integer> playertoPlayerIDMap = new HashMap<>();//Map that find game's idplayer from a player's nickname


    //Da vedere se è meglio mettere in ingresso solo il nickname o direttamente il clientHandler del player
    public void addPlayer(String nickname, Wizard wizard, GameMode gamemode, int numofplayers){
        int playerid;
        int found=0;
        for (String name : playertoPlayerIDMap.keySet())
            if(nickname.equals(name)){
                //Mandiamo un messaggio di nome già occupato
                return;
            }
        for(GameInterface game : playertoGameMap.values()){
            if(game.getGameMode()==gamemode)
                    if(game.getNumOfPlayers()==numofplayers){
                        if(game.getState()== GameState.WAITINGFORPLAYERS) {
                            playerid = game.addPlayer(nickname, wizard);
                            setGameofPlayer(nickname, game);
                            setPlayeridofPlayer(nickname, playerid);
                            found = 1;
                            break;
                        }
                    }
        }
        if(found==0) {
            GameInterface game = new Game(gamemode,numofplayers);
            playerid = game.addPlayer(nickname,wizard);
            setGameofPlayer(nickname,game);
            setPlayeridofPlayer(nickname,playerid);

        }

    }
    public void checkNickname(ClientHandlerInterface clientHandler,String nickname){
        for (String name : playertoPlayerIDMap.keySet())
            if(nickname.equals(name)){
                //Mandiamo un messaggio di nome già occupato
                return;
            }
        //Mandiamo un messaggio di nome valido
    }



    public GameInterface findGameofPlayer(String nickname) {
        return playertoGameMap.get(nickname);
    }

    public void setGameofPlayer(String nickname, GameInterface game) {
        playertoGameMap.put(nickname, game);
    }

    public int findPlayeridofPlayer(String nickname) {
        return playertoPlayerIDMap.get(nickname);
    }

    public void setPlayeridofPlayer(String nickname, int playerid) {
        playertoPlayerIDMap.put(nickname, playerid);
    }





    //ChooseStudent va splittato in movestudentotoisland e movestudenttohall
    /*public void chooseStudent(String nickname, Student student){
        Game game= findGameofPlayer(nickname);
        int playerid= findPlayeridofPlayer(nickname);
        try{
            game.chooseStudent(id,student)
        }
        catch{
            Message InvalidPhasemessage = new InvalidPhaseMessage(sksjsjw);
           // server.sendMessage(nickname,InvalidPhaseMa);
        }
    }

     */


    public void chooseAssistant(ClientHandlerInterface clientHandler, Assistant assistant) throws WrongAssistantException, InvalidTurnException {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerid = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.useAssistant(playerid, assistant);

        } catch (WrongAssistantException e) {
            //Message InvalidPhasemessage = new InvalidPhaseMessage(sksjsjw);
            // server.sendMessage(nickname,InvalidPhaseMa);
        } catch (InvalidTurnException e) {
            //C
        }

    }



}
