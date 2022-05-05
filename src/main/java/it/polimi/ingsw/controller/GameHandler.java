package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.GameState;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Wizard;
import it.polimi.ingsw.server.ClientHandlerInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class GameHandler {
    HashMap<String, GameInterface> playertoGameMap = new HashMap<>();//Map that find the game of a player's nickname
    HashMap<String, Integer> playertoPlayerIDMap = new HashMap<>();//Map that find game's idplayer from a player's nickname

    public void addPlayer(String nickname, Wizard wizard, GameMode gamemode, int numofplayers){
        int playerid;
        int found=0;
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


    public void chooseAssistant(ClientHandlerInterface clientHandler, Assistant assistant) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.useAssistant(playerID, assistant);
        } catch (WrongAssistantException e) {
            //clientHandler.sendMessageToClient(wrongAssistantMessage);
        } catch (InvalidTurnException e) {
            //clientHandler.sendMessageToClient(invalidTurnMessage);
        }
    }

    public void chooseCharacter(ClientHandlerInterface clientHandler, Characters character) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.useCharacter(playerID, character);

        } catch (InvalidStopException e) {
            //Message InvalidPhasemessage = new InvalidPhaseMessage(sksjsjw);
            // server.sendMessage(nickname,InvalidPhaseMa);
        } catch (InvalidTurnException e) {
            //Meesagge to client to notify excepion
        } catch (OutOfCoinsException e) {
            //Notify excepion
        } catch (InvalidCharacterException e) {
            //C
        }
    }

    public void chooseCloud(ClientHandlerInterface clientHandler, int cloudID) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.selectCloud(playerID,cloudID);
        } catch (WrongCloudException e) {
            //clientHandler.sendMessageToClient(wrongAssistantMessage);
        } catch (InvalidTurnException e) {
            //clientHandler.sendMessageToClient(invalidTurnMessage);
        }
    }

    public void moveMotherNature(ClientHandlerInterface clientHandler, int movement) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.moveMotherNature(playerID,movement);

        } catch (WrongValueException e) {
            //Message InvalidPhasemessage = new InvalidPhaseMessage(sksjsjw);
            // server.sendMessage(nickname,InvalidPhaseMa);
        } catch (InvalidTurnException e) {
            //Meesagge to client to notify excepion
        }
    }

    public void moveStudentToHall(ClientHandlerInterface clientHandler, Student student) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.moveStudentToHall(playerID,student);

        } catch (WrongStudentException e) {
            //Message InvalidPhasemessage = new InvalidPhaseMessage(sksjsjw);
            // server.sendMessage(nickname,InvalidPhaseMa);
        } catch (InvalidTurnException e) {
            //Meesagge to client to notify excepion
        }
    }

    public void chooseStudentsEffect(ClientHandlerInterface clientHandler, ArrayList<Student> students) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.CharacterStudentsPhase(playerID,students);

        } catch (WrongStudentEffectException e) {
            //Message InvalidPhasemessage = new InvalidPhaseMessage(sksjsjw);
            // server.sendMessage(nickname,InvalidPhaseMa);
        } catch (InvalidTurnException e) {
            //Meesagge to client to notify excepion
        }
    }

    public void chooseIslandEffect(ClientHandlerInterface clientHandler, int islandID) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.CharacterIslandPhase(playerID,islandID);

        } catch (WrongIslandException e) {
            //Message InvalidPhasemessage = new InvalidPhaseMessage(sksjsjw);
            // server.sendMessage(nickname,InvalidPhaseMa);
        } catch (InvalidTurnException e) {
            //Meesagge to client to notify excepion
        } catch (WrongStudentEffectException e) {
            //Message InvalidPhasemessage = new InvalidPhaseMessage(sksjsjw);
        }
    }

    public void moveStudentToIsland(ClientHandlerInterface clientHandler, int islandID, Student student) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.moveStudentToIsland(playerID,islandID,student);

        } catch (WrongStudentException e) {
            //Message InvalidPhasemessage = new InvalidPhaseMessage(sksjsjw);
            // server.sendMessage(nickname,InvalidPhaseMa);
        } catch (InvalidTurnException e) {
            //Meesagge to client to notify excepion
        }
    }

}
