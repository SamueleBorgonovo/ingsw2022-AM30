package it.polimi.ingsw.model.game;

import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.board.*;
import it.polimi.ingsw.model.player.*;
import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private int gameID;
    private GameMode gameMode;
    private ArrayList<Player> listOfPlayers = new ArrayList<>();
    private GameState gameState;
    private Board board;
    private final int numOfPlayers;
    private EffectHandler effectHandler;
    private int numplayerhasplayed=0;
    private int movementStudents=0;
    private ArrayList<Player> playerorder = new ArrayList<>();
    private Characters characterInUse = null;

    public Game(GameMode gameMode, int numofplayers) {
        this.gameMode = gameMode;
        this.numOfPlayers = numofplayers;
        gameState = GameState.WAITINGFORPLAYERS;
        board = new Board(gameMode, numofplayers);
        effectHandler = new EffectHandler();
        for(Characters character : board.getCharacters())
            character.getEffect().inizialize(this);
    }

    public void setCharacterInUse(Characters character){characterInUse=character;}

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public GameState getState() {
        return gameState;
    }

    public void addPlayer(String nickname, Wizard wizard) {
        Player player = new Player(nickname, wizard);

        listOfPlayers.add(player);
        player.setPlayerID(listOfPlayers.size());
        if (numOfPlayers == 2) {
            player.setPlance(new Plance(Tower.values()[listOfPlayers.size()-1], 8));
            for(int i = 0; i < 7; i++)
                player.getPlance().addStudentEntrance(board.getAndRemoveRandomBagStudent(1).get(0));
        } else {
            player.setPlance(new Plance(Tower.values()[listOfPlayers.size()-1], 6));
            for(int i = 0; i < 9; i++)
                player.getPlance().addStudentEntrance(board.getAndRemoveRandomBagStudent(1).get(0));
        }
        if (listOfPlayers.size() == numOfPlayers)
            this.startGame();
    }

    public Board getBoard() {
        return board;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Player getPlayer(int playerid) {
        for(Player player : listOfPlayers)
            if(player.getPlayerID() == playerid)
                return player;
        return null;
    }

    // getIsland moved to class Archipelago



    public Player winner() {
        ArrayList<Player> playersCandidate = new ArrayList<>();
        Player playerChosen = null;
        int minTowers = 8;
        int maxProfessor = 0;
        boolean gameIsFinished = false;

        if (board.getArchipelago().getNumOfIslands() == 3 ||
                board.getNumOfStudentsBag() == 0)
            gameIsFinished = true;

        for (Player player : listOfPlayers)

            if (player.getPlance().getNumOfTowers() == 0 ||
                    player.getAssistantCards().size() == 0)
                gameIsFinished = true;


        if (gameIsFinished) {
            for (Player player1 : listOfPlayers)
                if (player1.getPlance().getNumOfTowers() < minTowers)
                    minTowers = player1.getPlance().getNumOfTowers();
            for (Player player1 : listOfPlayers)
                if (player1.getPlance().getNumOfTowers() == minTowers)
                    playersCandidate.add(player1);
        }
        for (Player player2 : playersCandidate) {
            if (player2.getPlance().getProfessors().size() > maxProfessor) {
                maxProfessor = player2.getPlance().getProfessors().size();
                playerChosen = player2;
            }
        }
        return playerChosen;
    }

    public void selectCloud(int playerID, int cloudID) throws InvalidTurnException, WrongCloudException {
        if(getPlayer(playerID).getPlayerState()==PlayerState.CLOUDPHASE) {
            if(!getBoard().getCloud(cloudID).isChoosen()){
                    for(int count=0;count<getBoard().getCloud(cloudID).getStudents().size();count++)
                        getPlayer(playerID).getPlance().addStudentEntrance(getBoard().getCloud(cloudID).getStudents().get(count));
                    getBoard().getCloud(cloudID).setChoosen(true);
                    numplayerhasplayed++;
                }
            else throw new WrongCloudException();

            if(numplayerhasplayed < numOfPlayers) {
                //Set next player to play
                getPlayer(playerID).setPlayerState(PlayerState.WAITING);
                if (effectHandler.isProfessorcontroll())
                    effectHandler.setProfessorcontroll(false);
                playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.STUDENTPHASE);
                //Qui possiamo pure mandare un messaggio al server tipo "Tocca al secondo giocatore"
            }
            else {
                //All players played, ending round
                numplayerhasplayed=0;
                for(int count=0;count<getBoard().getClouds().size();count++){
                    getBoard().getClouds().get(count).setChoosen(false);
                }

                for (Player player : listOfPlayers) {
                    player.setPlayerState(PlayerState.WAITING);
                    player.setCharacterPlayed(false);
                }
                playerorder.get(0).setPlayerState(PlayerState.ASSISTANTPHASE);  //Start the new round
            }

        }
        else throw new InvalidTurnException();
    }

    public void moveStudentToHall(int playerID, Student student) throws InvalidTurnException, WrongStudentException{
        if(getPlayer(playerID).getPlayerState()==PlayerState.STUDENTPHASE) {
            if(getPlayer(playerID).getPlance().getEntrance().contains(student)){
                getPlayer(playerID).getPlance().addStudentHall(student);
                getPlayer(playerID).getPlance().removeStudentEntrance(student);
                movementStudents++;
                verifyProfessorControl();
                if (getPlayer(playerID).getPlance().getNumberOfStudentHall(student) % 3 == 0)
                    getPlayer(playerID).addCoins();
                if (movementStudents == numOfPlayers + 1) {
                    getPlayer(playerID).setPlayerState(PlayerState.MOTHERNATUREPHASE);
                    movementStudents=0;
                }
            } else throw new WrongStudentException();
        }
        else throw new InvalidTurnException();
    }

    public void moveStudentToIsland(int playerID, int islandID, Student student) throws InvalidTurnException, WrongStudentException {
        if(getPlayer(playerID).getPlayerState()==PlayerState.STUDENTPHASE) {
            if(getPlayer(playerID).getPlance().getEntrance().contains(student)){
                Island island = getBoard().getArchipelago().getSingleIsland(islandID);
                island.addStudent(student);
                getPlayer(playerID).getPlance().removeStudentFromHall(student);
                movementStudents++;
                if (movementStudents == numOfPlayers + 1) {
                    getPlayer(playerID).setPlayerState(PlayerState.MOTHERNATUREPHASE);
                    movementStudents=0;
                }
            } else throw new WrongStudentException();
        }  else throw new InvalidTurnException();
    }

    public void useAssistant(int playerID, Assistant assistant) throws WrongAssistantException, InvalidTurnException {

        if(getPlayer(playerID).getPlayerState() == PlayerState.ASSISTANTPHASE) { //Check that is the right id of the player that has to play
            if (numplayerhasplayed == 0) {  //First to choose, no controls to do
                if (getPlayer(playerID).getAssistantCards().contains(assistant)) {
                    getPlayer(playerID).removeAssistant(assistant);
                    getPlayer(playerID).setAssistantPlayed(true);
                    numplayerhasplayed++;
                    getPlayer(playerID).setPlayerState(PlayerState.WAITING);
                    playerorder.get(1).setPlayerState(PlayerState.ASSISTANTPHASE);
                } else
                    throw new WrongAssistantException();
            }
            else{
                if(numplayerhasplayed==1){  //Second to choose, control if first has played same assistant
                    if(getPlayer(playerID).getAssistantCards().contains(assistant)) {
                            if (assistant != playerorder.get(numplayerhasplayed - 1).getLastassistantplayed() || getPlayer(playerID).getAssistantCards().size() == 1) {
                                getPlayer(playerID).removeAssistant(assistant);
                                getPlayer(playerID).setAssistantPlayed(true);
                                getPlayer(playerID).setPlayerState(PlayerState.WAITING);
                                if (numOfPlayers == 2) {
                                    numplayerhasplayed = 0;
                                    verifyPlayerOrder();
                                    playerorder.get(0).setPlayerState(PlayerState.STUDENTPHASE);
                                }
                                else {
                                    numplayerhasplayed++;
                                    playerorder.get(2).setPlayerState(PlayerState.ASSISTANTPHASE);
                                }

                            } else throw new WrongAssistantException();
                        } else throw new WrongAssistantException();
                }
                else {
                    if(getPlayer(playerID).getAssistantCards().contains(assistant)){ //Third to choose, control first and second assistants played
                        if((assistant != playerorder.get(numplayerhasplayed-1).getLastassistantplayed() && assistant != playerorder.get(numplayerhasplayed-2).getLastassistantplayed()) || getPlayer(playerID).getAssistantCards().size()==1){
                            getPlayer(playerID).removeAssistant(assistant);
                            getPlayer(playerID).setAssistantPlayed(true);
                            getPlayer(playerID).setPlayerState(PlayerState.WAITING);
                            numplayerhasplayed=0;
                            verifyPlayerOrder();
                            playerorder.get(0).setPlayerState(PlayerState.STUDENTPHASE);
                        }else throw new WrongAssistantException();
                    }else throw new WrongAssistantException();
                }
            }
        }
        else throw new InvalidTurnException();
    }


    public void moveMotherNature(int playerID, int numberOfMovement) throws InvalidTurnException, WrongValueException {
        if(getPlayer(playerID).getPlayerState()==PlayerState.MOTHERNATUREPHASE) {
            if (!this.getEffectHandler().getTwomoremoves()) {  //Check if effect4 is in use
                if (numberOfMovement >= 1 && numberOfMovement <= getPlayer(playerID).getLastassistantplayed().getValue()) {
                    for (int count = 0; count < numberOfMovement; count++)
                        this.getBoard().getArchipelago().getMothernature().move(this.getBoard().getArchipelago().getNumOfIslands());
                    verifyIslandInfluence(getBoard().getArchipelago().getSingleIsland((this.getBoard().getArchipelago().getMothernature().isOn())).getIslandID());
                    this.getBoard().getArchipelago().verifyMergeableIsland();
                    //probably have to put winner method
                    getPlayer(playerID).setPlayerState(PlayerState.CLOUDPHASE);
                } else throw new WrongValueException();
            } else{
                //Effect4 used
                if (numberOfMovement >= 1 && numberOfMovement <= getPlayer(playerID).getLastassistantplayed().getValue()+2) {
                    for (int count = 0; count < numberOfMovement; count++)
                        this.getBoard().getArchipelago().getMothernature().move(this.getBoard().getArchipelago().getNumOfIslands());
                    verifyIslandInfluence(getBoard().getArchipelago().getSingleIsland((this.getBoard().getArchipelago().getMothernature().isOn())).getIslandID());
                    this.getBoard().getArchipelago().verifyMergeableIsland();
                    //probably have to put winner method
                    this.getEffectHandler().setTwomoremoves(false);
                    getPlayer(playerID).setPlayerState(PlayerState.CLOUDPHASE);
                } else throw new WrongValueException();
            }
        } else throw new InvalidTurnException();
    }

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
                throw new InvalidCharacterException();//If choosed character is not on the 3 of the game
            } else
            throw new InvalidCharacterException(); //If player has already played a character in this turn, maybe use a different Exception
        }
        else throw new InvalidTurnException();//If player can't play a character

    }

    public void CharacterIslandPhase(int playerID,int islandID) throws InvalidTurnException, WrongIslandException, WrongStudentEffectException {
        if(getPlayer(playerID).getPlayerState() == PlayerState.CHARACTHERISLANDPHASE) {
            if (islandID >= 1 && islandID <= board.getArchipelago().getNumOfIslands()) {
                effectHandler.setIslandIDchoose(islandID);
                characterInUse.getEffect().secondPartEffect(this, playerID);
            }
            else throw new WrongIslandException();
        }
        else throw new InvalidTurnException();
    }

    public void CharacterStudentsPhase(int playerID,ArrayList<Student> students) throws InvalidTurnException, WrongStudentEffectException {
        if(getPlayer(playerID).getPlayerState() == PlayerState.CHARACTHERSTUDENTSPHASE) {
            effectHandler.setStudentschoose(students);
            characterInUse.getEffect().secondPartEffect(this, playerID);
        }
        else throw new InvalidTurnException();
    }

    public EffectHandler getEffectHandler() {
        return effectHandler;
    }

    public Student chooseStudent() { //Remove
        //to implement in GUI
        return Student.BLUE;
    }

    //Just to test Effect7
    public Student chooseStudentFromPlance() {   //Remove
        return Student.YELLOW;
    }

    public ArrayList<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    public void verifyIslandInfluence(int islandID) {
        // Check which Player has the most influence on an Island and arrange the towers appropriately.
        int maxscore = 0;
        int score;
        Player playermaxscore = null;
        Island island = this.getBoard().getArchipelago().getSingleIsland(islandID);
        if (island.isStop()) {
            island.setStop(false);
            effectHandler.addislandstop();
        } else {
            for (Player player : listOfPlayers) {
                score=0;
                for (Professor professor : player.getPlance().getProfessors())
                    for (Student student : island.getStudents())
                        if (professor.ordinal() == student.ordinal())
                            if (!effectHandler.isNocolor() || (effectHandler.isNocolor() && !effectHandler.getStudent().equals(student)))
                                score++;
                for (int i = 0; i<island.getNumOfTowers() && player.getPlance().getTower().equals(island.getTowerColor()) && !effectHandler.isNotower(); i++)
                    score++;
                if (effectHandler.getTwopoints()!=0 && player.getPlayerID() == effectHandler.getTwopoints()) {
                    score = score + 2;
                    effectHandler.setTwopoints(0);
                }
                if (score > maxscore) {
                    maxscore = score;
                    playermaxscore = player;
                }
            }
            effectHandler.setNocolor(false);
            effectHandler.setNotower(false);
            if (playermaxscore != null && !playermaxscore.getPlance().getTower().equals(island.getTowerColor()) && maxscore != 0) {
                if (island.getNumOfTowers()==0)
                    island.setTowerColor(playermaxscore.getPlance().getTower());
                else {
                    for (Player player : listOfPlayers)
                        if (player.getPlance().getTower().equals(island.getTowerColor()))
                            for (int i = 0; i<island.getNumOfTowers();i++)
                                player.getPlance().addTower();
                    island.setTowerColor(playermaxscore.getPlance().getTower());
                }
                for (int i = 0; i<island.getNumOfTowers();i++)
                    playermaxscore.getPlance().removeTower();
            }
        }
    }

    void verifyProfessorControl() {
        // Controll and check Professors.
        int numofstudentcolorhall = 0;
        int numofstudentcolorhallmax = 0;
        Player playermax = null;

        for (int i = 0; i < Professor.values().length; i++) {
            for (Player player : listOfPlayers) {
                numofstudentcolorhall = player.getPlance().getNumberOfStudentHall(Student.values()[i]);
                if (numofstudentcolorhall > numofstudentcolorhallmax || (effectHandler.isProfessorcontroll() && numofstudentcolorhall >= numofstudentcolorhallmax)) {
                    numofstudentcolorhallmax = numofstudentcolorhall;
                    playermax = player;
                }
            }
            if (playermax != null && !playermax.getPlance().getProfessors().contains(Professor.values()[i])) {
                for (Player player : listOfPlayers)
                    if (player.getPlance().getProfessors().contains(Professor.values()[i]))
                        player.getPlance().removeProfessor(Professor.values()[i]);
                playermax.getPlance().addProfessor(Professor.values()[i]);
            }
            numofstudentcolorhallmax = 0;
            playermax = null;
        }
    }

    public ArrayList<Player> verifyPlayerOrder() {
        Player minplayer = listOfPlayers.get(0);
        for (int count = 1; count < listOfPlayers.size(); count++) {
            if (listOfPlayers.get(count).getLastassistantplayed().getValue() < minplayer.getLastassistantplayed().getValue())
                minplayer = listOfPlayers.get(count);
        }

        playerorder.add(0, minplayer);
        Player lasttakenplayer = minplayer;
        Player tempplayer = null;
        for (int count = 1; count < listOfPlayers.size(); count++) {
            for (int counter = 0; counter < listOfPlayers.size(); counter++) {
                if (lasttakenplayer.getPlayerID() == getNumOfPlayers()) {
                    if (listOfPlayers.get(counter).getPlayerID() == 1)
                        tempplayer = listOfPlayers.get(counter);
                } else {
                    if (listOfPlayers.get(counter).getPlayerID() == lasttakenplayer.getPlayerID() + 1) {
                        tempplayer = listOfPlayers.get(counter);
                    }
                }
            }
            lasttakenplayer = tempplayer;
            playerorder.add(count, lasttakenplayer);
        }
        return playerorder;
    }

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

    public ArrayList<Player> getPlayerorder(){
        return playerorder;
    }

}



