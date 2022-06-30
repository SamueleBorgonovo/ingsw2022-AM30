package it.polimi.ingsw.model.game;

import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.board.*;
import it.polimi.ingsw.model.player.*;
import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Game class contains the main logic of Eriantys
 */
public class Game implements GameInterface {
    private final GameMode gameMode;
    private final ArrayList<Player> listOfPlayers = new ArrayList<>();
    private GameState gameState;
    private final Board board;
    private final int numOfPlayers;
    private final EffectHandler effectHandler;
    private int numplayerhasplayed=0;
    private int movementStudents=0;
    private ArrayList<Player> playerorder = new ArrayList<>();
    private Characters characterInUse = null;
    private final ArrayList<Wizard> wizardChosen = new ArrayList<>();
    private final ArrayList<Assistant> usedAssistant = new ArrayList<>();

    /**
     * Constructor Game creates a new Game Instance
     *
     * @param gameMode the gameMode of the game
     * @param numofplayers the number of players in the game
     */
    public Game(GameMode gameMode, int numofplayers) {
        this.gameMode = gameMode;
        this.numOfPlayers = numofplayers;
        gameState = GameState.WAITINGFORPLAYERS;
        board = new Board(gameMode, numofplayers);
        effectHandler = new EffectHandler();
        for(Characters character : board.getCharacters())
            character.getEffect().initialize(this);
    }

    public ArrayList<Wizard> getWizardAvailable() {
        ArrayList<Wizard> wizardsAvailable = new ArrayList<>();
        for(Wizard wizard : Wizard.values())
            if(!wizardChosen.contains(wizard))
                wizardsAvailable.add(wizard);

        return wizardsAvailable;

    }

    /**
     * Method addWizardChosen save the player's wizard, so it will be not chosen again
     *
     * @param wizard the wizard chosen by the player in turn
     */
    public void addWizardChosen(Wizard wizard){
        wizardChosen.add(wizard);
    }

    /**
     * Method setWizard associates the player with the wizard chosen
     *
     * @param playerid the id of the player
     * @param wizard the wizard chosen by the player
     * @throws InvalidWizardException if the wizard chosen is not available
     */
    public void setWizard(int playerid,Wizard wizard) throws InvalidWizardException {
        if(!wizardChosen.contains(wizard)) {
            getPlayer(playerid).setWizard(wizard);
            addWizardChosen(wizard);
        }
        else throw new InvalidWizardException();
    }

    /**
     * Method setCharacterInUse save the character used in the turn
     *
     * @param character the character chosen in the turn
     */
    public void setCharacterInUse(Characters character){characterInUse=character;}

    /**
     * Method getState return the gameState of the Game
     *
     * @return the gameState of the game
     */
    public GameState getState() {
        return gameState;
    }

    /**
     * Method getGameMode return the gameMode of the Game
     *
     * @return the gameMode of the game
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Method getPlayers returns the player in the game
     *
     * @return the player in the game
     */
    public ArrayList<Player> getPlayers(){
        return new ArrayList<>(listOfPlayers);
    }

    /**
     * Method setReconnectedPlayer handle the reconnection of a player
     *
     * @param playerid the player who is reconnecting
     * @throws ReconnectedException if the player is already connected
     */
    public void setReconnectedPlayer(int playerid) throws ReconnectedException {
        if(getPlayer(playerid).getPlayerState() == PlayerState.DISCONNECTED) {
            getPlayer(playerid).setPlayerState(PlayerState.RECONNECTED);
            int count=0;
            for(Player player : listOfPlayers)
                if(player.getPlayerState()!=PlayerState.DISCONNECTED)
                    count++;
            if(count==2) {
                gameState=GameState.PLAYING;
            }
        }
        else throw new ReconnectedException();
    }

    /**
     * Method setDisconnectedPlayer set a player as disconnect
     *
     * @param playerid the id of the player disconnected
     * @return true if the GameHandler has to call the methods to start a new round
     */
    public boolean setDisconnectPlayer(int playerid){
        boolean callhandler=false;
        if(gameState==GameState.WAITINGFORPLAYERS){
            Wizard wizardchosen = getPlayer(playerid).getWizard();
            wizardChosen.remove(wizardchosen);
            listOfPlayers.remove(getPlayer(playerid)); //if game not already started, player can't reconnect
        }
        else {
            int counter = getNumPlayerDisconnected();
            if (counter < numOfPlayers - 1) {
                if (counter == numOfPlayers - 2) {
                    gameState = GameState.WAITINGFORRECONNECTION;
                }

                if (getPlayer(playerid).getPlayerState() == PlayerState.WAITING) {
                    getPlayer(playerid).setPlayerState(PlayerState.DISCONNECTED);
                } else if (getPlayer(playerid).getPlayerState() == PlayerState.ASSISTANTPHASE) {
                    callhandler = true;
                    numplayerhasplayed++;
                    getPlayer(playerid).setPlayerState(PlayerState.DISCONNECTED);
                    if (numplayerhasplayed == numOfPlayers) {
                        verifyPlayerOrder();
                        int tmp = searchFirst();
                        if (tmp != -1) {
                            playerorder.get(tmp).setPlayerState(PlayerState.STUDENTPHASE);
                        }
                    } else playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.ASSISTANTPHASE);
                } else {
                    movementStudents = 0;
                    callhandler = true;
                    numplayerhasplayed++;
                    getPlayer(playerid).setPlayerState(PlayerState.DISCONNECTED);
                    if (numplayerhasplayed == numOfPlayers) {
                        //All players played, ending round
                        numplayerhasplayed = 0;
                        for (int count = 0; count < getBoard().getClouds().size(); count++) {
                            getBoard().getClouds().get(count).setChosen(false);
                        }

                        for (Player player : listOfPlayers) {
                            player.setCharacterPlayed(false);
                        }
                        startRound();  //Start the new round
                    } else {
                        System.out.println("sono qui");
                        playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.STUDENTPHASE);
                    }
                }
            }else getPlayer(playerid).setPlayerState(PlayerState.DISCONNECTED);
        }
    return callhandler;
    }

    /**
     * Method searchFirst search the first player not disconnected and return his index in the player list
     *
     * @return the index of the first player not disconnected in the player list
     */
    public int searchFirst(){
        for(int count=0;count<playerorder.size();count++){
            if(playerorder.get(count).getPlayerState() != PlayerState.DISCONNECTED)
                return count;
        }
        return -1;
    }

    /**
     * Method addPlayer add one player in the game
     *
     * @param nickname the nickname of the new  player
     * @return the id of the new player
     */
    public int addPlayer(String nickname) {
        Player player = new Player(nickname);
        listOfPlayers.add(player);
        int playerID=checkPlayerID();
        player.setPlayerID(playerID);
        if (numOfPlayers == 2) {
            player.setPlance(new Plance(Tower.values()[playerID-1], 8));
            for(int i = 0; i < 7; i++)
                player.getPlance().addStudentEntrance(board.getAndRemoveRandomBagStudent(1).get(0));
        } else {
            player.setPlance(new Plance(Tower.values()[playerID-1], 6));
            for(int i = 0; i < 9; i++)
                player.getPlance().addStudentEntrance(board.getAndRemoveRandomBagStudent(1).get(0));
        }
        if(gameMode.equals(GameMode.EXPERTMODE)) {
            player.setCoins(1);
        }
        return playerID;
    }

    /**
     * Method checkPlayerID returns the playerID to give to a player
     * @return the playerID to give to a player
     */
    public int checkPlayerID() {
        int i;
        boolean check =true;
        for (i = 1; i <= numOfPlayers && check; i++) {
            check=false;
            for (Player player : listOfPlayers)
                if (player.getPlayerID() == i)
                    check = true;
        }
        return i-1;
    }

    /**
     * Method getBoard returns the board of this Game Object
     *
     * @return the board of this Game Object
     */
    public Board getBoard() {
        return board;
    }

    /**
     *Method checkPlayerState checks if the player is connected to the game or not
     *
     * @param playerid the player to check
     * @return true if the player is disconnected, else false
     */
    public boolean checkPlayerState(int playerid){
        return getPlayer(playerid).getPlayerState() == PlayerState.DISCONNECTED;
    }

    /**
     * Method getNumOfPlayer returns the number of player of this Game Object
     *
     * @return the number of player of this Game Object
     */
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /**
     * Method getPlayer returns a player instance relying on his unique ID
     *
     * @param playerid the id of the player
     * @return the player instance
     */
    public Player getPlayer(int playerid) {
        for(Player player : listOfPlayers)
            if(player.getPlayerID() == playerid)
                return player;
        return null;
    }

    /**
     * Method startRound starts a new round of the game
     */
    public void startRound(){
        if(numOfPlayers==2) {
            for (Player player : playerorder)
                if (player.getPlance().getEntrance().size() != 7) {
                    int cloudid = -1;
                    for (Cloud cloud : board.getClouds())
                        if (!cloud.isChosen()) {
                            cloudid = cloud.getCloudID();
                            cloud.setChosen(true);
                            break;
                        }
                    int count = 0;
                    while (player.getPlance().getEntrance().size() != 7) {
                        player.getPlance().addStudentEntrance(board.getCloud(cloudid).getStudents().get(count));
                        count++;

                    }
                }
        }
        if(numOfPlayers==3) {
            for (Player player : playerorder)
                if (player.getPlance().getEntrance().size() != 9) {
                    int cloudid = -1;
                    for (Cloud cloud : board.getClouds())
                        if (!cloud.isChosen()) {
                            cloudid = cloud.getCloudID();
                            cloud.setChosen((true));
                            break;
                        }
                    int count = 0;
                    while (player.getPlance().getEntrance().size() != 9) {
                        player.getPlance().addStudentEntrance(board.getCloud(cloudid).getStudents().get(count));
                        count++;
                    }
                }
        }

        for(int count=0;count<getBoard().getClouds().size();count++){
            getBoard().getClouds().get(count).setChosen(false);
            getBoard().getClouds().get(count).getStudents().clear();
            getBoard().getClouds().get(count).setStudents(getBoard().getAndRemoveRandomBagStudent(numOfPlayers+1));
        }

        for(Player player : playerorder) {
            if (player.getPlayerState() != PlayerState.DISCONNECTED)
                player.setPlayerState(PlayerState.WAITING);
        }

        numplayerhasplayed=0;
        int tmpplayerid=-1;
        for (int count=0;count<playerorder.size();count++) {
            if (playerorder.get(count).getPlayerState() == PlayerState.WAITING) {
                tmpplayerid=count;
                break;
            }
        }
        if(tmpplayerid!=-1){
            numplayerhasplayed=tmpplayerid;
            playerorder.get(tmpplayerid).setPlayerState(PlayerState.ASSISTANTPHASE);
        }

    }

    /**
     * Method winnerEndRound returns the winner of the game (if there is one) by checking after every turn
     *
     * @return 1 if the game is out of students, 2 if there are no more assistant usable, 0 else
     */
    public int winnerEndRound() {
        int gameIsFinished=0;

        if (board.getNumOfStudentsBag() == 0)
            gameIsFinished = 1;

        for (Player player : listOfPlayers)
            if (player.getAssistantCards().size() == 0) {
                gameIsFinished = 2;
                break;
            }
        return gameIsFinished;
    }

    /**
     * Method winnerInstantly return the winner of the game (if there is one) by checking after every action of the turn
     *
     * @return the player id of the winner (if there is one) else it returns 0
     */
    public int winnerInstantly(){
        int gameIsFinished=0;
        if (board.getArchipelago().getNumOfIslands() <= 3)
            gameIsFinished = 1;
        for (Player player : listOfPlayers)
            if (player.getPlance().getNumOfTowers() == 0) {
                gameIsFinished = 2;
                break;
            }
        return gameIsFinished;
    }

    /**
     * Method verifyWinner returns the winner of the game
     *
     * @return the player id of the winner player
     */
    public ArrayList<Player> verifyWinner(){

        ArrayList<Player> playersCandidate = new ArrayList<>();
        ArrayList<Player> playersChosen = new ArrayList<>();
        int minTowers = 8;
        int maxProfessor = 0;

            for (Player player1 : listOfPlayers)
                if (player1.getPlance().getNumOfTowers() < minTowers)
                    minTowers = player1.getPlance().getNumOfTowers();
            for (Player player1 : listOfPlayers)
                if (player1.getPlance().getNumOfTowers() == minTowers)
                    playersCandidate.add(player1);

        for (Player player2 : playersCandidate) {
            if (player2.getPlance().getProfessors().size() > maxProfessor) {
                maxProfessor = player2.getPlance().getProfessors().size();
                playersChosen.clear();
                playersChosen.add(player2);
            }
            else if( player2.getPlance().getProfessors().size() == maxProfessor)
                playersChosen.add(player2);
        }

        return playersChosen;
    }

    /**
     * Method selectCloud is used when a player select a cloud
     *
     * @param playerID the id of the player
     * @param cloudID the id of the cloud
     * @return true if the cloud has been selected, false if the player can't make this action
     * @throws InvalidTurnException if is not the player's turn
     * @throws InvalidCloudException if the cloud chosen is not valid
     */
    public boolean selectCloud(int playerID, int cloudID) throws InvalidTurnException, InvalidCloudException {
        if(getPlayer(playerID).getPlayerState()==PlayerState.CLOUDPHASE) {
            if(!getBoard().getCloud(cloudID).isChosen()){
                    for(int count=0;count<getBoard().getCloud(cloudID).getStudents().size();count++)
                        getPlayer(playerID).getPlance().addStudentEntrance(getBoard().getCloud(cloudID).getStudents().get(count));
                    getBoard().getCloud(cloudID).setChosen(true);
                    numplayerhasplayed++;
                }
            else throw new InvalidCloudException();

            if(numplayerhasplayed < numOfPlayers) {
                //Set next player to play
                getPlayer(playerID).setPlayerState(PlayerState.WAITING);
                if (effectHandler.getProfessorControl()!=-1)
                    effectHandler.setProfessorControl(-1);
                if(playerorder.get(numplayerhasplayed).getPlayerState()!=PlayerState.DISCONNECTED && playerorder.get(numplayerhasplayed).getPlayerState()!=PlayerState.RECONNECTED) {
                    playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.STUDENTPHASE);
                }
                else{
                    numplayerhasplayed++;
                    if(numplayerhasplayed==numOfPlayers){
                        //All players played, ending round
                        numplayerhasplayed=0;
                        for (Player player : listOfPlayers) {
                            player.setCharacterPlayed(false);
                        }
                        startRound();  //Start the new round
                        return true;
                    }
                    else playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.STUDENTPHASE);

                }
                return false;

            }
            else {
                //All players played, ending round
                numplayerhasplayed=0;
                for (Player player : listOfPlayers) {
                    player.setCharacterPlayed(false);
                }
               startRound();  //Start the new round
                return true;
            }
        }
        else throw new InvalidTurnException();
    }

    /**
     * Method moveStudentToHall move the student chosen by the player to his hall in the plance
     *
     * @param playerID the id of the player
     * @param student the student to move
     * @throws InvalidTurnException if isn't player's turn
     * @throws InvalidStudentException if the student chosen is not valid
     */
    public void moveStudentToHall(int playerID, Student student) throws InvalidTurnException, InvalidStudentException {

            if (getPlayer(playerID).getPlayerState() == PlayerState.STUDENTPHASE) {
                if (getPlayer(playerID).getPlance().getEntrance().contains(student)) {
                    getPlayer(playerID).getPlance().addStudentHall(student);
                    getPlayer(playerID).getPlance().removeStudentEntrance(student);
                    movementStudents++;
                    verifyProfessorControl();
                    if (getPlayer(playerID).getPlance().getNumberOfStudentHall(student) % 3 == 0)
                        getPlayer(playerID).addCoins();
                    if (movementStudents == numOfPlayers + 1) {
                        getPlayer(playerID).setPlayerState(PlayerState.MOTHERNATUREPHASE);
                        movementStudents = 0;
                    }
                } else throw new InvalidStudentException();
            } else throw new InvalidTurnException();

    }

    /**
     * Method moveStudentToHall move the student chosen by the player to the island chosen
     *
     * @param playerID the id of the player
     * @param islandID the id of the island chosen
     * @param student the student to move
     * @throws InvalidTurnException if isn't player's turn
     * @throws InvalidStudentException if the student chosen is not valid
     */
    public void moveStudentToIsland(int playerID, int islandID, Student student) throws InvalidTurnException, InvalidStudentException {

            if (getPlayer(playerID).getPlayerState() == PlayerState.STUDENTPHASE) {
                if (getPlayer(playerID).getPlance().getEntrance().contains(student)) {
                    Island island = getBoard().getArchipelago().getSingleIsland(islandID);
                    island.addStudent(student);
                    getPlayer(playerID).getPlance().removeStudentEntrance(student);
                    movementStudents++;
                    if (movementStudents == numOfPlayers + 1) {
                        getPlayer(playerID).setPlayerState(PlayerState.MOTHERNATUREPHASE);
                        movementStudents = 0;
                    }
                } else throw new InvalidStudentException();
            } else throw new InvalidTurnException();

    }

    /**
     * method useAssistant is used when a player chose one assistant
     *
     * @param playerID the player id
     * @param assistant the assistant chosen
     * @throws InvalidTurnException if isn't player's turn
     * @throws InvalidAssistantException if the assistant chosen is not valid
     */
    public void useAssistant(int playerID,Assistant assistant) throws InvalidTurnException, InvalidAssistantException{
        boolean ok=false;
        if(getPlayer(playerID).getPlayerState()== PlayerState.ASSISTANTPHASE){ //Check that is the right id of the player that has to play
            if(getPlayer(playerID).getAssistantCards().contains(assistant)){
                if(numOfPlayers==2)
                    if(!usedAssistant.contains(assistant) || getPlayer(playerID).getAssistantCards().size()==1){
                     ok=true;
                    }
                if(numOfPlayers==3)
                    if(!usedAssistant.contains(assistant) || (usedAssistant.size()==2 && getPlayer(playerID).getAssistantCards().size()==2))
                        ok=true;
                if(ok) {
                    getPlayer(playerID).removeAssistant(assistant);
                    usedAssistant.add(assistant);
                    getPlayer(playerID).setLastassistantplayed(assistant);
                    getPlayer(playerID).setAssistantPlayed(true);
                    numplayerhasplayed++;
                    getPlayer(playerID).setPlayerState(PlayerState.WAITING);
                    if (numplayerhasplayed == numOfPlayers) {
                        //All players have played the assistant
                        numplayerhasplayed = 0;
                        verifyPlayerOrder();
                        usedAssistant.clear();
                        playerorder.get(0).setPlayerState(PlayerState.STUDENTPHASE);
                    } else {
                        if (playerorder.get(numplayerhasplayed).getPlayerState() != PlayerState.DISCONNECTED && playerorder.get(numplayerhasplayed).getPlayerState() != PlayerState.RECONNECTED)
                            playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.ASSISTANTPHASE);
                        else {
                            numplayerhasplayed++;
                            if (numplayerhasplayed == numOfPlayers) {
                                //All players have played the assistant
                                numplayerhasplayed = 0;
                                verifyPlayerOrder();
                                usedAssistant.clear();
                                playerorder.get(0).setPlayerState(PlayerState.STUDENTPHASE);
                            } else
                                playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.ASSISTANTPHASE);
                        }

                    }
                } else throw new InvalidAssistantException();
            }
            else throw new InvalidAssistantException();
        }else throw new InvalidTurnException();
    }

    /**
     * Method moveMotherNature is used when a player move mother nature
     *
     * @param playerID the id of the player
     * @param numberOfMovement the number of movement chosen
     * @throws InvalidTurnException if isn't player's turn
     * @throws InvalidValueException if the island where to move motherNature is not valid
     */
    public void moveMotherNature(int playerID, int numberOfMovement) throws InvalidTurnException, InvalidValueException {
        if(getPlayer(playerID).getPlayerState()==PlayerState.MOTHERNATUREPHASE) {
            if (!this.getEffectHandler().getTwoMoreMoves()) {  //Check if effect4 is in use
                if (numberOfMovement >= 1 && numberOfMovement <= getPlayer(playerID).getLastassistantplayed().getValue()) {
                    for (int count = 0; count < numberOfMovement; count++)
                        this.getBoard().getArchipelago().getMotherNature().move(this.getBoard().getArchipelago().getNumOfIslands());
                    verifyIslandInfluence(getBoard().getArchipelago().getSingleIsland((this.getBoard().getArchipelago().getMotherNature().isOn())).getIslandID());
                    this.getBoard().getArchipelago().verifyMergeableIsland();
                    //probably have to put winner method
                    getPlayer(playerID).setPlayerState(PlayerState.CLOUDPHASE);
                } else throw new InvalidValueException();
            } else{
                //Effect4 used
                if (numberOfMovement >= 1 && numberOfMovement <= getPlayer(playerID).getLastassistantplayed().getValue()+2) {
                    for (int count = 0; count < numberOfMovement; count++)
                        this.getBoard().getArchipelago().getMotherNature().move(this.getBoard().getArchipelago().getNumOfIslands());
                    verifyIslandInfluence(getBoard().getArchipelago().getSingleIsland((this.getBoard().getArchipelago().getMotherNature().isOn())).getIslandID());
                    this.getBoard().getArchipelago().verifyMergeableIsland();
                    //probably have to put winner method
                    this.getEffectHandler().setTwoMoreMoves(false);
                    getPlayer(playerID).setPlayerState(PlayerState.CLOUDPHASE);
                } else throw new InvalidValueException();
            }
        } else throw new InvalidTurnException();
    }

    /**
     * Method useCharacter is used when a player decides to use a character
     *
     * @param playerID the id of the player
     * @param character the character chosen
     * @throws InvalidStopException if there are not enough stops on the card
     * @throws InvalidTurnException if isn't player's turn
     * @throws OutOfCoinsException if the player hasn't enough coins
     * @throws InvalidCharacterException if the character chosen is not valid
     */
    public void useCharacter(int playerID, Characters character)  throws InvalidStopException, InvalidTurnException, OutOfCoinsException, InvalidCharacterException {

        if(getPlayer(playerID).getPlayerState() != PlayerState.WAITING) {
            if(!getPlayer(playerID).getCharacterPlayed()) {
                if (getBoard().getCharacters().contains(character)) {
                    getPlayer(playerID).removeCoins(character.getCost());
                    getPlayer(playerID).setCharacterPlayed(true);
                    characterInUse = character;
                    character.getEffect().effect(this, playerID);
                    character.setUsed(true);
                } else
                    throw new InvalidCharacterException();
            } else
                throw new InvalidCharacterException();
        }
        else throw new InvalidTurnException();

    }

    /**
     * Method CharacterIslandPhase is used when the character used requires an island in input
     *
     * @param playerID the player id
     * @param islandID the island id
     * @throws InvalidTurnException if isn't player's turn
     * @throws InvalidIslandException if the island chosen is not valid
     * @throws InvalidStudentEffectException if the student chosen after a played character is not valid
     */
    public void CharacterIslandPhase(int playerID,int islandID) throws InvalidTurnException, InvalidIslandException, InvalidStudentEffectException {
        if(getPlayer(playerID).getPlayerState() == PlayerState.CHARACTHERISLANDPHASE) {
            if (islandID >= 1 && islandID <= board.getArchipelago().getNumOfIslands()) {
                effectHandler.setIslandIDchoose(islandID);
                characterInUse.getEffect().secondPartEffect(this, playerID);
            }
            else throw new InvalidIslandException();
        }
        else throw new InvalidTurnException();
    }

    /**
     * Method CharacterIslandPhase is used when the character used requires a student in input
     *
     * @param playerID the player id
     * @param students the students chosen
     * @throws InvalidTurnException if isn't player's turn
     * @throws InvalidStudentEffectException if the student chosen after a played character is not valid
     */
    public void CharacterStudentsPhase(int playerID,ArrayList<Student> students) throws InvalidTurnException, InvalidStudentEffectException {
        if(getPlayer(playerID).getPlayerState() == PlayerState.CHARACTHERSTUDENTSPHASE) {
            effectHandler.setStudentschoose(students);
            characterInUse.getEffect().secondPartEffect(this, playerID);
        }
        else throw new InvalidTurnException();
    }

    /**
     * Method getEffectHandler returns the EffectHandler Object
     *
     * @return the EffectHandler Object
     */
    public EffectHandler getEffectHandler() {
        return effectHandler;
    }

    /**
     * Method getListOfPlayer returns the player in the game
     *
     * @return the players in the game
     */
    public ArrayList<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    /**
     * Method verifyIslandInfluence calculate which player has the most influence on the island (if there is one)
     *
     * @param islandID the island id
     */
    public void verifyIslandInfluence(int islandID) {
        // Check which Player has the most influence on an Island and arrange the towers appropriately.
        int maxScore = 0;
        int score;
        Player playerMaxScore = null;
        Island island = this.getBoard().getArchipelago().getSingleIsland(islandID);

        if (island.isStop()) {
            island.setStop(false);
            effectHandler.addIslandStop();
        } else {
            for (Player player : listOfPlayers) {
                score=0;
                for (Professor professor : player.getPlance().getProfessors())
                    for (Student student : island.getStudents())
                        if (professor.ordinal() == student.ordinal())
                            if (!effectHandler.isNoColor() || (effectHandler.isNoColor() && !effectHandler.getStudent().equals(student)))
                                score++;
                for (int i = 0; i<island.getNumOfTowers() && player.getPlance().getTower().equals(island.getTowerColor()) && !effectHandler.isNoTower(); i++)
                    score++;
                if (effectHandler.getTwoPoints()!=0 && player.getPlayerID() == effectHandler.getTwoPoints()) {
                    score = score + 2;
                    effectHandler.setTwoPoints(0);
                }
                if (score > maxScore) {
                    maxScore = score;
                    playerMaxScore = player;
                }
                else if(score == maxScore)
                    playerMaxScore=null;
            }
            effectHandler.setNoColor(false);
            effectHandler.setNoTower(false);
            if (playerMaxScore != null && !playerMaxScore.getPlance().getTower().equals(island.getTowerColor()) && maxScore != 0) {
                if (island.getNumOfTowers()==0)
                    island.setTowerColor(playerMaxScore.getPlance().getTower());
                else {
                    for (Player player : listOfPlayers)
                        if (player.getPlance().getTower().equals(island.getTowerColor()))
                            for (int i = 0; i<island.getNumOfTowers();i++)
                                player.getPlance().addTower();
                    island.setTowerColor(playerMaxScore.getPlance().getTower());
                }
                for (int i = 0; i<island.getNumOfTowers();i++)
                    playerMaxScore.getPlance().removeTower();
            }
        }
    }

    /**
     * Method verifyIslandInfluence calculate which player has the most influence on Professors (if there is one)
     */
    public void verifyProfessorControl() {
        int numOfStudentColorHall;
        int numOfStudentColorHallMax;
        Player playerMax;

        for (int i = 0; i < Professor.values().length; i++) {
            playerMax = null;
            numOfStudentColorHallMax = 0;
            for (Player player : listOfPlayers)
                if(player.getPlance().getProfessors().contains(Professor.values()[i]))
                    numOfStudentColorHallMax=player.getPlance().getNumberOfStudentHall(Student.values()[i]);
            for (Player player : listOfPlayers) {
                numOfStudentColorHall = player.getPlance().getNumberOfStudentHall(Student.values()[i]);
                if (numOfStudentColorHall > numOfStudentColorHallMax || (effectHandler.getProfessorControl()==player.getPlayerID() && numOfStudentColorHall >= numOfStudentColorHallMax)) {
                    numOfStudentColorHallMax = numOfStudentColorHall;
                    playerMax = player;
                }
            }
            if (playerMax != null && !playerMax.getPlance().getProfessors().contains(Professor.values()[i])) {
                for (Player player : listOfPlayers)
                    if (player.getPlance().getProfessors().contains(Professor.values()[i]))
                        player.getPlance().removeProfessor(Professor.values()[i]);
                playerMax.getPlance().addProfessor(Professor.values()[i]);
            }
        }
    }

    /**
     * Method verifyPlayerOrder calculate the right order of player in the turn
     *
     * @return the right order of player in the turn
     */
    public ArrayList<Player> verifyPlayerOrder() {
        ArrayList<Player> temporder = new ArrayList<>();
        Player minplayer = playerorder.get(0);
        for (int count = 1; count < playerorder.size(); count++) {
            if((playerorder.get(count).getPlayerState()!=PlayerState.DISCONNECTED) && (playerorder.get(count).getPlayerState()!=PlayerState.RECONNECTED))
                if (playerorder.get(count).getLastassistantplayed().getValue() < minplayer.getLastassistantplayed().getValue())
                    minplayer = playerorder.get(count);
        }

        temporder.add(0, minplayer);
        Player lasttakenplayer = minplayer;
        Player tempplayer = null;
        for (int count = 1; count < playerorder.size(); count++) {
            for (Player player : playerorder) {
                if (lasttakenplayer.getPlayerID() == getNumOfPlayers()) {
                    if (player.getPlayerID() == 1)
                        tempplayer = player;
                } else {
                    if (player.getPlayerID() == lasttakenplayer.getPlayerID() + 1) {
                        tempplayer = player;
                    }
                }
            }
            lasttakenplayer = tempplayer;
            temporder.add(count, lasttakenplayer);
        }
        for(int count=0;count<temporder.size();count++){
            if(temporder.get(count).getPlayerState()==PlayerState.DISCONNECTED || temporder.get(count).getPlayerState()==PlayerState.RECONNECTED){
                Collections.swap(temporder,count,temporder.size()-1);
            }

        }
        playerorder.clear();
        playerorder.addAll(temporder);
        return playerorder;
    }

    /**
     * Method startGame starts a new game
     */
    public void startGame() {
        Random rnd = new Random();
        int index;

        gameState = GameState.PLAYING;
        index = rnd.nextInt(numOfPlayers);
        for(int j=index; j<numOfPlayers; j++)
            playerorder.add(listOfPlayers.get(j));
        for(int k=0; k<index; k++)
            playerorder.add(listOfPlayers.get(k));

        playerorder.get(0).setPlayerState(PlayerState.ASSISTANTPHASE);
    }

    /**
     * Method getMotherNatureIsland returns the id of the island which has mother nature
     *
     * @return id of the island which has mother nature
     */
    @Override
    public int getMotherNatureIsland() {
        return board.getArchipelago().getMotherNature().isOn();
    }

    /**
     * Method getPlayerOrder returns the order of the player in the turn
     *
     * @return the order of the player in the turn
     */
    public ArrayList<Player> getPlayerorder(){
        return playerorder;
    }

    /**
     * Method setPlayerOrder set the order of the player in the turn
     *
     * @param playerorder the order of the players
     */
    public void setPlayerorder(ArrayList<Player> playerorder) {
        this.playerorder = playerorder;
    }

    /**
     * Method getPlayersView returns the view chosen by the player
     *
     * @return the view of the players in the game
     */
    public ArrayList<PlayerView> getPlayersView(){
        ArrayList<PlayerView> tempplayerview = new ArrayList<>();
        for(Player player : listOfPlayers)
            tempplayerview.add(player.getPlayerView());
        return tempplayerview;
    }

    /**
     * Method searchCharacter returns the character associated to the characterView
     *
     * @param characterView the characterView to search
     * @return the character associated to the characterView
     */
    public Characters searchCharacter(CharacterView characterView){
        for(Characters character : getBoard().getCharacters())
            if(character.getEffect().getName().equals(characterView.getName()))
                return character;
        return null;
    }

    /**
     * Method getNumPlayerDisconnected returns the number of disconnected players
     *
     * @return the number of disconnected players
     */
    public int getNumPlayerDisconnected(){
        int num=0;
        for(Player player : listOfPlayers) {
            if (player.getPlayerState() == PlayerState.DISCONNECTED)
                num++;
        }
        return num;
    }
}



