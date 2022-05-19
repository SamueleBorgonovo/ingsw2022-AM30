package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.messages.toClient.*;
import it.polimi.ingsw.messages.toClient.StatesMessages.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.GameState;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.PlayerInterface;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;
import it.polimi.ingsw.server.ClientHandlerInterface;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class GameHandler {
    private static ConcurrentHashMap<String, GameInterface> playertoGameMap;//Map that find the game of a player's nickname
    private static ConcurrentHashMap<String, Integer> playertoPlayerIDMap;//Map that find game's idplayer from a player's nickname
    private static ConcurrentHashMap<String, ClientHandlerInterface> playertoHandlerMap;//Map that find player's handler
    private static ArrayList<String> nicknameChoosen;
    private ArrayList<PlayerView> playerViews;
    private static int studentPlayed=0;

    public GameHandler(){
        playertoGameMap = new ConcurrentHashMap<>();//Map that find the game of a player's nickname
        playertoPlayerIDMap = new ConcurrentHashMap<>();//Map that find game's idplayer from a player's nickname
        playertoHandlerMap = new ConcurrentHashMap<>();//Map that find player's handler
        nicknameChoosen = new ArrayList<>();
    }

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
                            WizardsListMessage message = new WizardsListMessage(game.getWizardAvailable());
                            clientHandler.sendMessageToClient(message);
                            sendMessagetoGame(game,new ConnectMessage(clientHandler.getNickname(), false));

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
            WizardsListMessage message = new WizardsListMessage(game.getWizardAvailable());
            clientHandler.sendMessageToClient(message);
        }

    }
    public void checkNickname(ClientHandlerInterface clientHandler,String nickname){
        if(playertoGameMap.containsKey(nickname)){
            GameInterface game = findGameofPlayer(nickname);
            int playerid = findPlayeridofPlayer(nickname);
            if(game.checkPlayerState(playerid)){
                clientHandler.setNickname(nickname);
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

    public void startGame(GameInterface game){
        sendMessagetoGame(game,new StartGameMessage());
        game.startGame();
        turnHandler(game);
    }

    public void turnHandler(GameInterface game){
        if(game.getState()==GameState.PLAYING) {
            PlayerInterface playertoplay;
            PlayerState state;
            for (PlayerInterface player : game.getPlayerorder())
                if (player.getPlayerState() != PlayerState.WAITING && player.getPlayerState() != PlayerState.DISCONNECTED
                        && player.getPlayerState() != PlayerState.RECONNECTED) {
                    playertoplay = player;
                    state = player.getPlayerState();
                    if(state==PlayerState.STUDENTPHASE && studentPlayed==0)
                        sendMessagetoGame(game,new SetTurnMessage(playertoplay.getNickname(), false));
                    if(state==PlayerState.ASSISTANTPHASE)
                        sendMessagetoGame(game,new SetTurnMessage(playertoplay.getNickname(), true));

                    findHandler(playertoplay.getNickname()).sendMessageToClient(new PlayerStateMessage(state));
                    break;
                }
        }
    }

    public void disconnectPlayer(String nickname){
        boolean callhandler;
        GameInterface game = findGameofPlayer(nickname);
        int playerid = findPlayeridofPlayer(nickname);

        if(game.getState()==GameState.WAITINGFORPLAYERS) {
            nicknameChoosen.remove(nickname);
            playertoGameMap.remove(nickname);
            playertoPlayerIDMap.remove(nickname);
        }
        playertoHandlerMap.remove(nickname);
        callhandler=game.setDisconnectPlayer(playerid);
        if(callhandler) {
            studentPlayed=0;
            turnHandler(game);
        }
    }

    public void reconnectPlayer(ClientHandlerInterface clientHandler){
        if(playertoGameMap.containsKey(clientHandler.getNickname())) {
            GameInterface game = findGameofPlayer(clientHandler.getNickname());
            int playerid = findPlayeridofPlayer(clientHandler.getNickname());
            try {
                game.setReconnectedPlayer(playerid);
                setHandlerofPlayer(clientHandler.getNickname(),clientHandler);
                clientHandler.sendMessageToClient(new PlanceUpdateMessage(game.getPlayers()));
                clientHandler.sendMessageToClient(new BoardUpdateMessage(game.getBoard(),true));
                sendMessagetoGame(game, new ConnectMessage(clientHandler.getNickname(), true));
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

    public void closeGame(ArrayList<String> nicknames,boolean gameEnded){
        for(String nick : nicknames) {
            findHandler(nick).handleSocketDisconnection(false,gameEnded);
        }
    }

    //sends message to all client of a game
    public void sendMessagetoGame(GameInterface game,MessageToClient message){
        for(int count=0;count<game.getPlayers().size();count++)
            if(game.getPlayers().get(count).getPlayerState()!=PlayerState.DISCONNECTED) {
                findHandler(game.getPlayers().get(count).getNickname()).sendMessageToClient(message);
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
            sendMessagetoGame(game,new PlanceUpdateMessage(game.getPlayerorder()));
            sendMessagetoGame(game,new BoardUpdateMessage(game.getBoard(),true));
            sendMessagetoGame(game,new AssistantChoosedMessage(clientHandler.getNickname(),assistant));
            turnHandler(game);
        } catch (InvalidAssistantException e) {
            InvalidAssistantMessage message = new InvalidAssistantMessage();
            clientHandler.sendMessageToClient(message);
            clientHandler.sendMessageToClient(new PlayerStateMessage(PlayerState.ASSISTANTPHASE));
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
            turnHandler(game);
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
            sendMessagetoGame(game,new PlanceUpdateMessage(game.getPlayers()));
            sendMessagetoGame(game,new BoardUpdateMessage(game.getBoard(),true));
            sendMessagetoGame(game,new CloudChoosedMessage(clientHandler.getNickname(), cloudID));
            turnHandler(game);
        } catch (InvalidCloudException e) {
            InvalidCloudMessage message = new InvalidCloudMessage();
            clientHandler.sendMessageToClient(message);
            clientHandler.sendMessageToClient(new PlayerStateMessage(PlayerState.CLOUDPHASE));
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
            sendMessagetoGame(game,new PlanceUpdateMessage(game.getPlayers()));
            sendMessagetoGame(game,new BoardUpdateMessage(game.getBoard(),true));
            sendMessagetoGame(game,new MotherNatureMoveMessage(clientHandler.getNickname(), game.getMotherNatureIsland()));
            turnHandler(game);
        } catch (InvalidValueException e) {
            InvalidValueMessage message = new InvalidValueMessage();
            clientHandler.sendMessageToClient(message);
            clientHandler.sendMessageToClient(new PlayerStateMessage(PlayerState.MOTHERNATUREPHASE));
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
            sendMessagetoGame(game,new PlanceUpdateMessage(game.getPlayers()));
            sendMessagetoGame(game,new BoardUpdateMessage(game.getBoard(),true));
            sendMessagetoGame(game,new StudentHallChoosedMessage(clientHandler.getNickname(),student));
            studentPlayed++;
            if(studentPlayed<game.getNumOfPlayers()+1)
                clientHandler.sendMessageToClient(new PlayerStateMessage(PlayerState.STUDENTPHASE));
            else {
                studentPlayed=0;
                turnHandler(game);
            }
        } catch (InvalidStudentException e) {
            InvalidStudentMessage message = new InvalidStudentMessage();
            clientHandler.sendMessageToClient(message);
            clientHandler.sendMessageToClient(new PlayerStateMessage(PlayerState.STUDENTPHASE));
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
            turnHandler(game);
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
            turnHandler(game);
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
            sendMessagetoGame(game,new PlanceUpdateMessage(game.getPlayers()));
            sendMessagetoGame(game,new BoardUpdateMessage(game.getBoard(),true));
            sendMessagetoGame(game,new StudentIslandChoosedMessage(clientHandler.getNickname(),student,islandID));
            studentPlayed++;

            if(studentPlayed< game.getNumOfPlayers()+1)
                clientHandler.sendMessageToClient(new PlayerStateMessage(PlayerState.STUDENTPHASE));
            else {
                studentPlayed=0;
                turnHandler(game);
            }
        } catch (InvalidStudentException e) {
            InvalidStudentMessage message = new InvalidStudentMessage();
            clientHandler.sendMessageToClient(message);
            clientHandler.sendMessageToClient(new PlayerStateMessage(PlayerState.STUDENTPHASE));
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
            if(4-game.getWizardAvailable().size()==game.getNumOfPlayers()){
                sendMessagetoGame(game,new PlanceUpdateMessage(game.getPlayers()));
                sendMessagetoGame(game,new BoardUpdateMessage(game.getBoard(),true));
                startGame(game);
            }
        } catch (InvalidWizardException e) {
           InvalidWizardMessage message = new InvalidWizardMessage();
           clientHandler.sendMessageToClient(message);
           clientHandler.sendMessageToClient(new WizardsListMessage(game.getWizardAvailable()));
        }
    }
}
