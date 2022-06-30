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

/**
 * GameHandler handles multiple games, disconnection and reconnection of a player in a game and manage all
 * methods called to change a game
 */
public class GameHandler {
    private static ConcurrentHashMap<String, GameInterface> playertoGameMap;//Map that find the game of a player's nickname
    private static ConcurrentHashMap<String, Integer> playertoPlayerIDMap;//Map that find game's playerID from a player's nickname
    private static ConcurrentHashMap<String, ClientHandlerInterface> playertoHandlerMap;//Map that find player's handler
    private static ConcurrentHashMap<GameInterface, Thread> gameToTimerMap;//Map to find WinningTimer of a game
    private static ConcurrentHashMap<GameInterface, Integer> gameToStudentPlayed;
    private static ArrayList<String> NicknameChosen;
    private final int WINNING_TIMER = 240000;

    /**
     * Constructor GameHandler instantiates maps used in the class
     */
    public GameHandler(){
        playertoGameMap = new ConcurrentHashMap<>();
        playertoPlayerIDMap = new ConcurrentHashMap<>();
        playertoHandlerMap = new ConcurrentHashMap<>();
        gameToTimerMap = new ConcurrentHashMap<>();
        gameToStudentPlayed = new ConcurrentHashMap<>();
        NicknameChosen = new ArrayList<>();
    }

    /**
     * Method used to add a new player to a game. If a game with same settings already exists, player is added in that game.
     * @param clientHandler clientHandler of the client to add
     * @param gamemode gamemode chosen by the player
     * @param numofplayers num of players chosen by the player
     */
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
                            clientHandler.sendMessageToClient(new BoardUpdateMessage(game.getBoard().getBoardView()));
                            clientHandler.sendMessageToClient(new PlanceUpdateMessage(game.getPlayersView()));
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
            clientHandler.sendMessageToClient(new BoardUpdateMessage(game.getBoard().getBoardView()));
            clientHandler.sendMessageToClient(new PlanceUpdateMessage(game.getPlayersView()));
            setGameofPlayer(clientHandler.getNickname(), game);
            setPlayeridofPlayer(clientHandler.getNickname(), playerid);
            setHandlerofPlayer(clientHandler.getNickname(),clientHandler);
            setGameToStudentPlayed(game,0);
            clientHandler.sendMessageToClient(new BoardUpdateMessage(game.getBoard().getBoardView()));
            WizardsListMessage message = new WizardsListMessage(game.getWizardAvailable());
            clientHandler.sendMessageToClient(message);
        }

    }

    /**
     * Method used to check if the nickname chosen by the player is already used, or if he can reconnect to a game
     * @param clientHandler clientHandler of the client of the player
     * @param nickname nickname chosen by the player
     * @param newGame if newGame is true player decided to don't reconnect to a game and to create a new one
     */
    public void checkNickname(ClientHandlerInterface clientHandler,String nickname,boolean newGame){
        if(!newGame) {
            if (playertoGameMap.containsKey(nickname)) {
                GameInterface game = findGameofPlayer(nickname);
                int playerid = findPlayeridofPlayer(nickname);
                if (game.checkPlayerState(playerid)) {
                    clientHandler.setNickname(nickname);
                    NicknameMessage message = new NicknameMessage(true, true);
                    clientHandler.sendMessageToClient(message);
                } else {
                    NicknameMessage message = new NicknameMessage(false, false);
                    clientHandler.sendMessageToClient(message);
                }
            } else {
                if (NicknameChosen.contains(nickname)) {
                    NicknameMessage message = new NicknameMessage(false, false);
                    clientHandler.sendMessageToClient(message);
                } else {
                    NicknameMessage message = new NicknameMessage(true, false);
                    NicknameChosen.add(nickname);
                    clientHandler.setNickname(nickname);
                    clientHandler.sendMessageToClient(message);
                }
            }
        }else {
            playertoGameMap.remove(nickname);
            playertoPlayerIDMap.remove(nickname);
            playertoHandlerMap.remove(nickname);
            clientHandler.setNickname(nickname);
            clientHandler.sendMessageToClient(new NicknameMessage(true,false));
        }
    }

    /**
     * Method used to start a game
     * @param game game to start
     */
    public void startGame(GameInterface game){
        sendMessagetoGame(game,new StartGameMessage(false));
        game.startGame();
        turnHandler(game);
    }

    /**
     * Method used to find the player that has to play and his phase. It sends a message of {@link SetTurnMessage} SetTurnMessage to all players of the game
     * @param game game to check who has to play
     */
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

    /**
     * Method used to disconnect a player from a game. Is game is not already started, player is removed from it.
     *     If num of disconnected player is equals to num of players, game is closed.
     * @param nickname nickname of the disconnected player
     */
    public synchronized void disconnectPlayer(String nickname){
        boolean callhandler;
        GameInterface game = findGameofPlayer(nickname);
        int playerid = findPlayeridofPlayer(nickname);
        if(game!=null) {
            if (game.getState() == GameState.WAITINGFORPLAYERS) {
                NicknameChosen.remove(nickname);
                playertoGameMap.remove(nickname);
                playertoPlayerIDMap.remove(nickname);
            }
            playertoHandlerMap.remove(nickname);
            if (playerid != -1) {
                callhandler = game.setDisconnectPlayer(playerid);
                int numPlayerDisconnected = game.getNumPlayerDisconnected();
                System.out.println(numPlayerDisconnected);
                if (numPlayerDisconnected == game.getNumOfPlayers()) {
                    gameShutdown(game, true, false);
                } else {
                    if (numPlayerDisconnected <= game.getNumOfPlayers() - 1) {
                        sendMessagetoGame(game, new DisconnectMessage(nickname, false, false));
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
            }
        }else {
            NicknameChosen.remove(nickname);
            playertoHandlerMap.remove(nickname);
        }
    }

    /**
     * Method used to reconnect a player to a game. If the game was set in pause, it starts.
     * @param clientHandler client handler of the player to reconnect
     */
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
                clientHandler.sendMessageToClient(new BoardUpdateMessage(game.getBoard().getBoardView()));
                clientHandler.sendMessageToClient(new EffectHandlerUpdateMessage(game.getEffectHandler()));
                Wizard wizardToSend=null;
                for(Player player : game.getPlayers())
                    if(playerid==player.getPlayerID()){
                        wizardToSend=player.getWizard();
                    }
                clientHandler.sendMessageToClient(new SettingReconnectMessage(game.getGameMode(),game.getNumOfPlayers(),wizardToSend));
                sendMessagetoGame(game, new ConnectMessage(clientHandler.getNickname(), true));
                if(game.getNumOfPlayers()-game.getNumPlayerDisconnected()==2) {
                    sendMessagetoGame(game,new StartGameMessage(true));
                    turnHandler(game);
                }
            }catch(ReconnectedException e){
                clientHandler.sendMessageToClient(new NoGameMessage());
            }
            }
    }

    /**
     * Method used to send message of lightView update to all clients of a game
     * @param game game to send the updates
     * @param boardView game's board update
     * @param playerView game's list of players update
     * @param effectHandler game's effectHandler update
     */
    public void updateClient(GameInterface game, BoardView boardView, ArrayList<PlayerView> playerView, EffectHandler effectHandler){
        sendMessagetoGame(game,new BoardUpdateMessage(boardView));
        sendMessagetoGame(game,new PlanceUpdateMessage(playerView));
        sendMessagetoGame(game,new EffectHandlerUpdateMessage(effectHandler));
    }

    /**
     * Method used to send a message to all clients of a game
     * @param game game from which it picks the players
     * @param message message to send to all clients
     */
    public void sendMessagetoGame(GameInterface game,MessageToClient message){
        for(int count=0;count<game.getPlayers().size();count++)
            if(game.getPlayers().get(count).getPlayerState()!=PlayerState.DISCONNECTED) {
                findHandler(game.getPlayers().get(count).getNickname()).sendMessageToClient(message);
            }
    }


    /**
     * Method used to get the gameInterface of a player from playertoGameMap
     * @param nickname nickname of the player
     * @return the gameInterface of the game of the player
     */
    public GameInterface findGameofPlayer(String nickname) {
        return playertoGameMap.get(nickname);
    }

    /**
     * Method used to set the gameInterface of a player in playertoGameMap
     * @param nickname nickname of the player
     * @param game gameInterface of the game of the player
     */
    public void setGameofPlayer(String nickname, GameInterface game) {
        playertoGameMap.put(nickname, game);
    }

    /**
     * Method used to get the playerID of a player in a game
     * @param nickname nickname of the player
     * @return the playerID of the player in his game
     */
    public int findPlayeridofPlayer(String nickname) {
        int n=-1;
        if(playertoPlayerIDMap.get(nickname)!=null)
            n=playertoPlayerIDMap.get(nickname);
        return n;
    }

    /**
     * Method used to set the playerID of a player in a game
     * @param nickname nickname of the player
     * @param playerid playerID of the player in the game
     */
    public void setPlayeridofPlayer(String nickname, int playerid) {
        playertoPlayerIDMap.put(nickname, playerid);
    }

    /**
     * Method used to get the ClientHandlerInterface of a player
     * @param nickname nickname of the player
     * @return the ClientHandlerInterface of the player
     */
   public ClientHandlerInterface findHandler(String nickname){
        return playertoHandlerMap.get(nickname);
   }

    /**
     * Method used to set the ClientHandlerInterface of a player
     * @param nickname nickname of the player
     * @param handler ClientHandlerInterface of the player
     */
   public void setHandlerofPlayer(String nickname, ClientHandlerInterface handler){
        playertoHandlerMap.put(nickname,handler);
   }

    /**
     * Method used to set number of studentPlayed of a game
     * @param game GameInterface of the game to set the studentPlayed
     * @param num number to set
     */
   public void setGameToStudentPlayed(GameInterface game,int num){
        if(gameToStudentPlayed.containsKey(game))
            gameToStudentPlayed.remove(game);
        gameToStudentPlayed.put(game,num);
   }

    /**
     * Method used to add 1 to a studentPlayed of a game
     * @param game GameInterface of the game
     */
   public void addGameToStudentPlayed(GameInterface game){

        int num = gameToStudentPlayed.get(game);
        gameToStudentPlayed.remove(game);
        gameToStudentPlayed.put(game,num+1);
   }

    /**
     * Method used to get the studentPlayed of a game
     * @param game GameInterface of the game
     * @return the studentPlayed of the game
     */
   public int getGameToStudentPlayed(GameInterface game){
        return gameToStudentPlayed.get(game);
   }


    /**
     * Method used when a client tries to use an assistant
     * @param clientHandler ClientHandlerInterface of the client
     * @param assistant assistant chosen
     */
    public void chooseAssistant(ClientHandlerInterface clientHandler, Assistant assistant) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.useAssistant(playerID, assistant);
            updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler());
            sendMessagetoGame(game,new AssistantChosenMessage(clientHandler.getNickname(),assistant));
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

    /**
     * Method used when a client tries to use a character
     * @param clientHandler ClientHandlerInterface of the client
     * @param characterview CharacterView of che character chosen
     */
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
                sendMessagetoGame(game,new CharacterChosenMessage(clientHandler.getNickname(),characterview));
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

    /**
     * Method used when a client tries to use a cloud
     * @param clientHandler ClientHandlerInterface of the client
     * @param cloudID ID of che cloud in the game
     */
    public void chooseCloud(ClientHandlerInterface clientHandler, int cloudID) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            boolean last = game.selectCloud(playerID,cloudID);
            updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler() );
            sendMessagetoGame(game,new CloudChosenMessage(clientHandler.getNickname(), cloudID));
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

    /**
     * Method used when a client tries to move MotherNature
     * @param clientHandler ClientHandlerInterface of the client
     * @param movement number of movement chosen by the client
     */
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
    /**
     * Method used when a client tries to move a student to the hall of his plance
     * @param clientHandler ClientHandlerInterface of the client
     * @param student student that the client chosen to move
     */
    public void moveStudentToHall(ClientHandlerInterface clientHandler, Student student) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.moveStudentToHall(playerID,student);
            updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler() );
            sendMessagetoGame(game,new StudentHallChosenMessage(clientHandler.getNickname(),student));
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

    /**
     * Method used when a client tries to move students after a character is  played
     * @param clientHandler ClientHandlerInterface of the client
     * @param students students chosen by the client
     */
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

    /**
     * Method used when a client chosen an island after a character is played
     * @param clientHandler ClientHandlerInterface of the client
     * @param islandID islandID of the island chosen
     */
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

    /**
     * Method used when a client tries to move a student to an island
     * @param clientHandler ClientHandlerInterface of the client
     * @param islandID islandID of the island chosen
     * @param student student chosen
     */
    public void moveStudentToIsland(ClientHandlerInterface clientHandler, int islandID, Student student) {
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerID = findPlayeridofPlayer(clientHandler.getNickname());
        try {
            game.moveStudentToIsland(playerID,islandID,student);
            updateClient(game,game.getBoard().getBoardView(), game.getPlayersView(),game.getEffectHandler() );
            sendMessagetoGame(game,new StudentIslandChosenMessage(clientHandler.getNickname(),student,islandID));
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

    /**
     * Method used when a client tries to select a wizard
     * @param clientHandler ClientHandlerInterface of the client
     * @param wizard wizard chosen
     */
    public void chooseWizard(ClientHandlerInterface clientHandler, Wizard wizard){
        GameInterface game = findGameofPlayer(clientHandler.getNickname());
        int playerid = findPlayeridofPlayer(clientHandler.getNickname());
        try{
            game.setWizard(playerid,wizard);
            clientHandler.sendMessageToClient(new CorrectlyConnectedMessage());
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

    /**
     * Method that checks if a player of a game has won. It does different check if the game is an end round phase or not
     * @param game GameInterface of the game to check
     * @param endRound is true if the game is in an end round phase, false otherwise
     * @return true if there is a winner, false otherwise
     */
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
            check=game.winnerInstantly();
            if (check != 0) {
                playerswinner=game.verifyWinner();
                ArrayList<String> nickWinners = new ArrayList<>();
                for(Player player : playerswinner)
                    nickWinners.add(player.getNickname());
                sendMessagetoGame(game,new WinInstantlyMessage(nickWinners,check));
            }
        }
        if(check!=0){
            gameShutdown(game,false,true);
            return true;
        }
        return false;
    }

    /**
     * Method used when a game has to been shutdown
     * @param game GameInterface of the game
     * @param timeout if is true, method has been called after a timeout expired
     * @param win if is true, method has been called after a player has won
     */
    public void gameShutdown(GameInterface game,boolean timeout,boolean win){
        sendMessagetoGame(game,new DisconnectMessage("win",timeout,win));
        for(Player player : game.getPlayers())
            if(player.getPlayerState()!=PlayerState.DISCONNECTED)
                findHandler(player.getNickname()).closePinger();


        for(Player player : game.getPlayers()){
            playertoHandlerMap.remove(player.getNickname());
            playertoGameMap.remove(player.getNickname());
            playertoPlayerIDMap.remove(player.getNickname());
            NicknameChosen.remove(player.getNickname());
            gameToStudentPlayed.remove(game);
        }
    }

    /**
     * Method used to start a timer when only one player is remained in the game
     * @param game GameInterface of the game
     */
    public void startWinnerTimer(GameInterface game){
        Thread timer = new Thread(() ->{
            try {
                Thread.sleep(WINNING_TIMER);
                gameShutdown(game,true,false);
            } catch (InterruptedException ignored) { }

        });
        timer.start();
        gameToTimerMap.put(game,timer);
    }
}
