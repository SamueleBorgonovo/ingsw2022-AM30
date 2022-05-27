package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.virtualView.BoardView;
import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.messages.toClient.*;
import it.polimi.ingsw.messages.toClient.InvalidMoveMessages.*;
import it.polimi.ingsw.messages.toClient.StatesMessages.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.game.*;
import it.polimi.ingsw.model.player.*;
import it.polimi.ingsw.server.ClientHandlerInterface;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class GameHandler {
    private static ConcurrentHashMap<String, GameInterface> playertoGameMap;//Map that find the game of a player's nickname
    private static ConcurrentHashMap<String, Integer> playertoPlayerIDMap;//Map that find game's idplayer from a player's nickname
    private static ConcurrentHashMap<String, ClientHandlerInterface> playertoHandlerMap;//Map that find player's handler
    private static ConcurrentHashMap<GameInterface, Thread> gameToTimerMap;//Map to find WinningTimer of a game
    private static ConcurrentHashMap<GameInterface, Integer> gameToStudentPlayed;
    private static ArrayList<String> nicknameChoosen;
    private final int WINNING_TIMER = 60000;

    public GameHandler(){
        playertoGameMap = new ConcurrentHashMap<>();//Map that find the game of a player's nickname
        playertoPlayerIDMap = new ConcurrentHashMap<>();//Map that find game's idplayer from a player's nickname
        playertoHandlerMap = new ConcurrentHashMap<>();//Map that find player's handler
        gameToTimerMap = new ConcurrentHashMap<>();
        gameToStudentPlayed = new ConcurrentHashMap<>();
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
            GameInterface game = new Game(gamemode,numofplayers);
            playerid = game.addPlayer(clientHandler.getNickname());

            setGameofPlayer(clientHandler.getNickname(), game);
            setPlayeridofPlayer(clientHandler.getNickname(), playerid);
            setHandlerofPlayer(clientHandler.getNickname(),clientHandler);
            setGameToStudentPlayed(game,0);
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
                    if(state==PlayerState.STUDENTPHASE && getGameToStudentPlayed(game)==0)
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
        callhandler = game.setDisconnectPlayer(playerid);
        int numPlayerDisconnected = game.getNumPlayerDisconnected();
        if (numPlayerDisconnected == game.getNumOfPlayers()) {
            gameShutdown(game,true,false);
        }
        if (numPlayerDisconnected <= game.getNumOfPlayers() - 1) {
            sendMessagetoGame(game, new DisconnectMessage(nickname, false,false));
            if (numPlayerDisconnected == game.getNumOfPlayers() - 1) {
                sendMessagetoGame(game, new WaitingForPlayersMessage(false));
                startWinnerTimer(game);
            }
        }
        if (callhandler) {
            setGameToStudentPlayed(game, 0);
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
                if(gameToTimerMap.containsKey(game)){
                    gameToTimerMap.get(game).interrupt();
                    gameToTimerMap.remove(game);
                }
                clientHandler.sendMessageToClient(new PlanceUpdateMessage(game.getPlayersView()));
                clientHandler.sendMessageToClient(new BoardUpdateMessage(game.getBoard().getBoardView(),true));
                clientHandler.sendMessageToClient(new EffectHandlerUpdateMessage(game.getEffectHandler()));
                clientHandler.sendMessageToClient(new SettingReconnectMessage(game.getGameMode(),game.getNumOfPlayers()));
                sendMessagetoGame(game, new ConnectMessage(clientHandler.getNickname(), true));
                if(game.getNumOfPlayers()-game.getNumPlayerDisconnected()==2)
                    turnHandler(game);
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

    public void updateClient(GameInterface game, BoardView boardView, ArrayList<PlayerView> playerView, EffectHandler effectHandler){
        sendMessagetoGame(game,new BoardUpdateMessage(boardView,true));
        sendMessagetoGame(game,new PlanceUpdateMessage(playerView));
        sendMessagetoGame(game,new EffectHandlerUpdateMessage(effectHandler));
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

   public void setGameToStudentPlayed(GameInterface game,int num){
        if(gameToStudentPlayed.containsKey(game))
            gameToStudentPlayed.remove(game);
        gameToStudentPlayed.put(game,num);
   }

   public void addGameToStudentPlayed(GameInterface game){

        int num = gameToStudentPlayed.get(game);
        gameToStudentPlayed.remove(game);
        gameToStudentPlayed.put(game,num+1);
   }

   public int getGameToStudentPlayed(GameInterface game){
        return gameToStudentPlayed.get(game);
   }



    public void chooseAssistant(ClientHandlerInterface clientHandler, Assistant assistant) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.useAssistant(playerID, assistant);
            updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler());
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

    public void chooseCharacter(ClientHandlerInterface clientHandler, CharacterView characterview) {
        System.out.println(characterview.getName());
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        Characters character = game.searchCharacter(characterview);
        if(character==null){
            InvalidCharacterMessage message = new InvalidCharacterMessage();
            clientHandler.sendMessageToClient(message);
        }else {
            try {
                game.useCharacter(playerID, character);
                updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler() );
                sendMessagetoGame(game,new CharacterChoosedMessage(clientHandler.getNickname(),characterview));
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
    }

    public void chooseCloud(ClientHandlerInterface clientHandler, int cloudID) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            boolean last = game.selectCloud(playerID,cloudID);
            updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler() );
            sendMessagetoGame(game,new CloudChoosedMessage(clientHandler.getNickname(), cloudID));
            if(last) {
                if (!checkWinner(game, true)) {
                    turnHandler(game);
                }
            }else turnHandler(game);
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
            updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler() );
            sendMessagetoGame(game,new MotherNatureMoveMessage(clientHandler.getNickname(), game.getMotherNatureIsland()));
            if(!checkWinner(game,false))
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
            updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler() );
            sendMessagetoGame(game,new StudentHallChoosedMessage(clientHandler.getNickname(),student));
            addGameToStudentPlayed(game);
            if(getGameToStudentPlayed(game)<game.getNumOfPlayers()+1)
                clientHandler.sendMessageToClient(new PlayerStateMessage(PlayerState.STUDENTPHASE));
            else {
                setGameToStudentPlayed(game,0);
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
            updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler() );
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
            updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler() );
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
            updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler() );
            sendMessagetoGame(game,new StudentIslandChoosedMessage(clientHandler.getNickname(),student,islandID));
            addGameToStudentPlayed(game);
            if(getGameToStudentPlayed(game)< game.getNumOfPlayers()+1)
                clientHandler.sendMessageToClient(new PlayerStateMessage(PlayerState.STUDENTPHASE));
            else {
                setGameToStudentPlayed(game,0);
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
                updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler() );
                startGame(game);
            }else clientHandler.sendMessageToClient(new WaitingForPlayersMessage(true));
        } catch (InvalidWizardException e) {
           InvalidWizardMessage message = new InvalidWizardMessage();
           clientHandler.sendMessageToClient(message);
           clientHandler.sendMessageToClient(new WizardsListMessage(game.getWizardAvailable()));
        }
    }

   public boolean checkWinner(GameInterface game,boolean endRound){
       int check;
       ArrayList<Player> playerswinner;
        if(endRound) {
            check=game.winnerEndRound();
            if(check!=0) {
                playerswinner=game.verifyWinner();
                ArrayList<String> nickWinners = new ArrayList<>();
                for(Player player : playerswinner)
                    nickWinners.add(player.getNickname());
                sendMessagetoGame(game, new WinEndRoundMessage(nickWinners,check));
            }
        }else {
            check=game.winnerIstantly();
            if (check != 0) {
                playerswinner=game.verifyWinner();
                ArrayList<String> nickWinners = new ArrayList<>();
                for(Player player : playerswinner)
                    nickWinners.add(player.getNickname());
                sendMessagetoGame(game,new WinIstantlyMessage(nickWinners,check));
            }
        }
        if(check!=0){
            gameShutdown(game,false,true);
            return true;
        }
        return false;
    }

    public void gameShutdown(GameInterface game,boolean timeout,boolean win){
        sendMessagetoGame(game,new DisconnectMessage("win",timeout,win));
        for(Player player : game.getPlayers())
            if(player.getPlayerState()!=PlayerState.DISCONNECTED)
                findHandler(player.getNickname()).handleSocketDisconnection(timeout, true);


        for(Player player : game.getPlayers()){
            playertoHandlerMap.remove(player.getNickname());
            playertoGameMap.remove(player.getNickname());
            playertoPlayerIDMap.remove(player.getNickname());
            nicknameChoosen.remove(player.getNickname());
            gameToStudentPlayed.remove(game);
        }
    }

    public void startWinnerTimer(GameInterface game){
        Thread timer = new Thread(() ->{
            try {
                Thread.sleep(WINNING_TIMER);
                gameShutdown(game,true,false);
            } catch (InterruptedException e) { }

        });
        gameToTimerMap.put(game,timer);
    }
}
