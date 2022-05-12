package it.polimi.ingsw.controller;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.messages.toClient.*;
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
    HashMap<String, ClientHandlerInterface> playertoHandlerMap = new HashMap<>();//Map that find player's handler
    ArrayList<String> nicknameChoosen = new ArrayList<>();


    //Manca l'handler del process del messaggio della scelta del wizard
    public void addPlayer(ClientHandlerInterface clientHandler, GameMode gamemode, int numofplayers){
        int playerid;
        int found=0;
        for(GameInterface game : playertoGameMap.values()){
            if(game.getGameMode()==gamemode)
                    if(game.getNumOfPlayers()==numofplayers){
                        if(game.getState()== GameState.WAITINGFORPLAYERS) {
                            playerid = game.addPlayer(clientHandler.getNickname());
                            setGameofPlayer(clientHandler.getNickname(), game);
                            setPlayeridofPlayer(clientHandler.getNickname(), playerid);
                            setHandlerofPlayer(clientHandler.getNickname(),clientHandler);
                            found = 1;
                            //Possiamo mandare tipo un messaggio di addato ad una partita
                            WizardsListMessage message = new WizardsListMessage(game.getWizardChoosen());
                            clientHandler.sendMessageToClient(message);

                            break;
                        }
                    }
        }
        if(found==0) {
            GameInterface game = new Game(gamemode,numofplayers, this);
            playerid = game.addPlayer(clientHandler.getNickname());
            setGameofPlayer(clientHandler.getNickname(), game);
            setPlayeridofPlayer(clientHandler.getNickname(), playerid);
            setHandlerofPlayer(clientHandler.getNickname(),clientHandler);

            //Possiamo mandare un messaggio di creazione nuova partita
            WizardsListMessage message = new WizardsListMessage(game.getWizardChoosen());
            clientHandler.sendMessageToClient(message);
        }

    }
    public void checkNickname(ClientHandlerInterface clientHandler,String nickname){
        if(playertoGameMap.containsKey(nickname)){
            GameInterface game = findGameofPlayer(nickname);
            int playerid = findPlayeridofPlayer(nickname);
            if(game.checkPlayerState(playerid)){
                NicknameMessage message = new NicknameMessage(true,true);
                clientHandler.sendMessageToClient(message);
            }else{
                NicknameMessage message = new NicknameMessage(false,false);
                clientHandler.sendMessageToClient(message);
            }
        }else{
            if(nicknameChoosen.contains(nickname)){
                NicknameMessage message = new NicknameMessage(false,false);
                clientHandler.sendMessageToClient(message);
            }else{
                NicknameMessage message = new NicknameMessage(true,false);
                nicknameChoosen.add(nickname);
                clientHandler.setNickname(nickname);
                clientHandler.sendMessageToClient(message);
            }
        }
    }

    public void disconnectPlayer(ClientHandlerInterface clientHandler){
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerid = findPlayeridofPlayer(clientHandler.getNickname());
        if(game.getState()==GameState.WAITINGFORPLAYERS) {
            nicknameChoosen.remove(clientHandler.getNickname());
            playertoGameMap.remove(clientHandler.getNickname());
            playertoPlayerIDMap.remove(clientHandler.getNickname());
            playertoHandlerMap.remove(clientHandler.getNickname());
        }
        game.setDisconnectPlayer(playerid);
    }

    public void reconnectPlayer(ClientHandlerInterface clientHandler){
        if(playertoGameMap.containsKey(clientHandler.getNickname())) {
            GameInterface game = findGameofPlayer(clientHandler.getNickname());
            int playerid = findPlayeridofPlayer(clientHandler.getNickname());
            try {
                game.setReconnectedPlayer(playerid);
            }catch(ReconnectedException e){
                //Fare il messaggio invalidReconnection
                NicknameMessage message = new NicknameMessage(false,false);
                clientHandler.sendMessageToClient(message);
            }
            }
        else{
            NoGameMessage message = new NoGameMessage();
            clientHandler.sendMessageToClient(message);
        }

    }

    public void closeGame(Game game,ArrayList<String> nicknames){
        //sendMessagetoAll il game si sta chiudendo

        for(String nick : nicknames) {
            playertoGameMap.remove(nick);
            playertoPlayerIDMap.remove(nick);
            nicknameChoosen.remove(nick);
        }
    }

    public void sendMessagetoAll(ArrayList<String> nicknames,MessageToClient message){

        for(String nick : nicknames) {
            findHandler(nick).sendMessageToClient(message);
        }
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

   public ClientHandlerInterface findHandler(String nickname){
        return playertoHandlerMap.get(nickname);
   }

   public void setHandlerofPlayer(String nickname, ClientHandlerInterface handler){
        playertoHandlerMap.put(nickname,handler);
   }




    public void chooseAssistant(ClientHandlerInterface clientHandler, Assistant assistant) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.useAssistant(playerID, assistant);
        } catch (InvalidAssistantException e) {
            InvalidAssistantMessage message = new InvalidAssistantMessage();
            clientHandler.sendMessageToClient(message);
        } catch (InvalidTurnException e) {
            InvalidTurnMessage message = new InvalidTurnMessage();
            clientHandler.sendMessageToClient(message);
        }
    }

    public void chooseCharacter(ClientHandlerInterface clientHandler, Characters character) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.useCharacter(playerID, character);

        } catch (InvalidStopException e) {
            InvalidStopMessage message = new InvalidStopMessage();
            clientHandler.sendMessageToClient(message);
        } catch (InvalidTurnException e) {
            InvalidTurnMessage message = new InvalidTurnMessage();
            clientHandler.sendMessageToClient(message);
        } catch (OutOfCoinsException e) {
            OutOfCoinsMessage message = new OutOfCoinsMessage();
            clientHandler.sendMessageToClient(message);
        } catch (InvalidCharacterException e) {
            InvalidCharacterMessage message = new InvalidCharacterMessage();
            clientHandler.sendMessageToClient(message);
        }
    }

    public void chooseCloud(ClientHandlerInterface clientHandler, int cloudID) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.selectCloud(playerID,cloudID);
        } catch (InvalidCloudException e) {
            InvalidCloudMessage message = new InvalidCloudMessage();
            clientHandler.sendMessageToClient(message);
        } catch (InvalidTurnException e) {
            InvalidTurnMessage message = new InvalidTurnMessage();
            clientHandler.sendMessageToClient(message);
        }
    }

    public void moveMotherNature(ClientHandlerInterface clientHandler, int movement) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.moveMotherNature(playerID,movement);

        } catch (InvalidValueException e) {
            InvalidValueMessage message = new InvalidValueMessage();
            clientHandler.sendMessageToClient(message);
        } catch (InvalidTurnException e) {
            InvalidTurnMessage message = new InvalidTurnMessage();
            clientHandler.sendMessageToClient(message);
        }
    }

    public void moveStudentToHall(ClientHandlerInterface clientHandler, Student student) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.moveStudentToHall(playerID,student);

        } catch (InvalidStudentException e) {
            InvalidStudentMessage message = new InvalidStudentMessage();
            clientHandler.sendMessageToClient(message);
        } catch (InvalidTurnException e) {
            InvalidTurnMessage message = new InvalidTurnMessage();
            clientHandler.sendMessageToClient(message);
        }
    }

    public void chooseStudentsEffect(ClientHandlerInterface clientHandler, ArrayList<Student> students) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.CharacterStudentsPhase(playerID,students);

        } catch (InvalidStudentEffectException e) {
            InvalidStudentEffectMessage message = new InvalidStudentEffectMessage();
            clientHandler.sendMessageToClient(message);
        } catch (InvalidTurnException e) {
            InvalidTurnMessage message = new InvalidTurnMessage();
            clientHandler.sendMessageToClient(message);
        }
    }

    public void chooseIslandEffect(ClientHandlerInterface clientHandler, int islandID) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.CharacterIslandPhase(playerID,islandID);

        } catch (InvalidIslandException e) {
            InvalidIslandMessage message = new InvalidIslandMessage();
            clientHandler.sendMessageToClient(message);
        } catch (InvalidTurnException e) {
            InvalidTurnMessage message = new InvalidTurnMessage();
            clientHandler.sendMessageToClient(message);
        } catch (InvalidStudentEffectException e) {
            InvalidStudentEffectMessage message = new InvalidStudentEffectMessage();
            clientHandler.sendMessageToClient(message);
        }
    }

    public void moveStudentToIsland(ClientHandlerInterface clientHandler, int islandID, Student student) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.moveStudentToIsland(playerID,islandID,student);

        } catch (InvalidStudentException e) {
            InvalidStudentMessage message = new InvalidStudentMessage();
            clientHandler.sendMessageToClient(message);
        } catch (InvalidTurnException e) {
            InvalidTurnMessage message = new InvalidTurnMessage();
            clientHandler.sendMessageToClient(message);
        }
    }

    public void chooseWizard(ClientHandlerInterface clientHandler, Wizard wizard){
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerid = findPlayeridofPlayer(clientHandler.getNickname());
        try{
            game.setWizard(playerid,wizard);
        } catch (InvalidWizardException e) {
           InvalidWizardMessage message = new InvalidWizardMessage();
           clientHandler.sendMessageToClient(message);
        }
    }

}
