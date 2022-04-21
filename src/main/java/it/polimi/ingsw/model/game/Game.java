package it.polimi.ingsw.model.game;

import it.polimi.ingsw.model.board.Character;
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
    private VerifyType verifyType;
    private MotherNature mothernature;
    private int numplayerhasplayed=0;
    private int movementStudents=0;
    private ArrayList<Player> playerorder = new ArrayList<>();

    public Game(GameMode gameMode, int numofplayers) {
        this.gameMode = gameMode;
        this.numOfPlayers = numofplayers;
        gameState = GameState.WAITINGFORPLAYERS;
        board = new Board(gameMode, numofplayers);
        verifyType = new VerifyType();
    }

    public void setGameID(int gameID) {

        this.gameID = gameID;
    }

    public GameState getState() {
        return gameState;
    }

    public void addPlayer(Player player) {
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
            gameState = GameState.PLAYING;
    }


    public Board getBoard() {
        return board;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Player getPlayer(int playerid) {
        Player tempplayer = null;
        for (int count = 0; count < numOfPlayers; count++) {
            if (playerid == listOfPlayers.get(count).getPlayerID()) {
                tempplayer = listOfPlayers.get(count);
            }
        }
        return tempplayer;
    }

    // getIsland moved to class Archipelago

    public Cloud getCloud(int cloudid){
        Cloud tempcloud = null;
        for(int count=0;count<getBoard().getClouds().size();count++){
            if(cloudid == getBoard().getClouds().get(count).getCloudID())
                tempcloud = getBoard().getClouds().get(count);
        }
        return tempcloud;
    }


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

    public void selectCloud(int playerID, int cloudID) throws InvalidTurnExceptions, WrongCloudException {
        if(getPlayer(playerID).getPlayerState()==PlayerState.CLOUDPHASE) {
            if(!getCloud(cloudID).isChoosen()){
                    for(int count=0;count<getCloud(cloudID).getStudents().size();count++)
                        getPlayer(playerID).getPlance().addStudentEntrance(getCloud(cloudID).getStudents().get(count));
                    getCloud(cloudID).setChoosen(true);
                    numplayerhasplayed++;
                }
            else throw new WrongCloudException();

            if(numplayerhasplayed < numOfPlayers) {
                //Set next player to play
                getPlayer(playerID).setPlayerState(PlayerState.WAITING);
                if (verifyType.isProfessorcontroll())
                    verifyType.setProfessorcontroll(false);
                playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.STUDENTPHASE);
                //Qui possiamo pure mandare un messaggio al server tipo "Tocca al secondo giocatore"
            }
            else {
                //All players played, ending round
                numplayerhasplayed=0;
                for(int count=0;count<getBoard().getClouds().size();count++){
                    getBoard().getClouds().get(count).setChoosen(false);
                }

                for (Player player : listOfPlayers)
                    player.setPlayerState(PlayerState.WAITING);
                assistantPhase(); //Start the new round
            }

        }
        else throw new InvalidTurnExceptions();
    }

    public void moveStudentToHall(int playerID, Student student) throws InvalidTurnExceptions, WrongStudentException{
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
        else throw new InvalidTurnExceptions();
    }

    public void moveStudentToIsland(int playerID, int islandID, Student student) throws InvalidTurnExceptions, WrongStudentException {
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
        }  else throw new InvalidTurnExceptions();
    }

    public void useAssistant(int playerID, Assistant assistant) throws WrongAssistantException,InvalidTurnExceptions{
        ArrayList<Player> playerorder = verifyPlayerOrder();
        Player playertoplay;

        if(getPlayer(playerID).getPlayerState() == PlayerState.ASSISTANTPHASE) { //Check that is the right id of the player that has to play
            if (numplayerhasplayed == 0) {  //First to choose, no controls to do
                if (getPlayer(playerID).getAssistantCards().contains(assistant)) {
                    getPlayer(playerID).removeAssistant(assistant);
                    getPlayer(playerID).setAssistantPlayed(true);
                    numplayerhasplayed++;
                } else
                    throw new WrongAssistantException();
            }
            else{
                if(numplayerhasplayed==1){  //Second to choose, control if first has played same assistant
                    if(getPlayer(playerID).getAssistantCards().contains(assistant)) {
                        if(assistant != playerorder.get(numplayerhasplayed-1).getLastassistantplayed() || getPlayer(playerID).getAssistantCards().size()==1) {
                            getPlayer(playerID).removeAssistant(assistant);
                            getPlayer(playerID).setAssistantPlayed(true);
                            if(numOfPlayers==2)
                                numplayerhasplayed=0;
                            else
                                numplayerhasplayed++;

                        } else throw new WrongAssistantException();
                    } else throw new WrongAssistantException();
                } else {
                    if(getPlayer(playerID).getAssistantCards().contains(assistant)){ //Third to choose, control first and second assistants played
                        if((assistant != playerorder.get(numplayerhasplayed-1).getLastassistantplayed() && assistant != playerorder.get(numplayerhasplayed-2).getLastassistantplayed()) || getPlayer(playerID).getAssistantCards().size()==1){
                            getPlayer(playerID).removeAssistant(assistant);
                            getPlayer(playerID).setAssistantPlayed(true);
                            numplayerhasplayed=0;
                        }else throw new WrongAssistantException();
                    }else throw new WrongAssistantException();
                }
            }
        }
        else throw new InvalidTurnExceptions();
    }

    public void assistantPhase(){
        for (Player player : listOfPlayers) {
            do {
                player.setPlayerState(PlayerState.ASSISTANTPHASE);
            }while(!player.isAssistantPlayed());//<-------------------------------- modificare useAssistant
            player.setPlayerState(PlayerState.WAITING);
        }
        for(Player player : listOfPlayers)
            player.setAssistantPlayed(false);
        verifyPlayerOrder();
        playerorder.get(0).setPlayerState(PlayerState.STUDENTPHASE);

    }

    public void moveMotherNature(int playerID, int numberOfMovement) throws InvalidTurnExceptions, WrongValueException {
        if(getPlayer(playerID).getPlayerState()==PlayerState.MOTHERNATUREPHASE) {
            if(numberOfMovement >= 1 && numberOfMovement <= getPlayer(playerID).getLastassistantplayed().getValue()) {
                for (int count = 0; count < numberOfMovement; count++)
                    mothernature.move();
                verifyIslandInfluence(getBoard().getArchipelago().getSingleIsland((mothernature.isOn())));
                verifyMergeableIsland();
                //probably have to put winner method
                getPlayer(playerID).setPlayerState(PlayerState.CLOUDPHASE);
            } else throw new WrongValueException();
        }
        else throw new InvalidTurnExceptions();
    }

    public void useCharacter(int playerID, Character character) {

        for (Player player : listOfPlayers)
            if (player.getPlayerID() == playerID) {
                player.removeCoins(character.getCost());
                character.setUsed(true);
            }
    }

    public VerifyType getVerifyType() {
        return verifyType;
    }

    public void verifyMergeableIsland() {
        for (int i = 0; i < board.getArchipelago().getNumOfIslands(); i++)
            if (i != board.getArchipelago().getNumOfIslands() - 1 &&
                    board.getArchipelago().getIslands().get(i).getTowerColor() ==
                            board.getArchipelago().getIslands().get(i + 1).getTowerColor()) {
                board.getArchipelago().mergeIslands(board.getArchipelago().getIslands().get(i).getIslandID(),
                        board.getArchipelago().getIslands().get(i + 1).getIslandID());
                i = 0;
            } else if (i == board.getArchipelago().getNumOfIslands() - 1 &&
                    board.getArchipelago().getIslands().get(i).getTowerColor() ==
                            board.getArchipelago().getIslands().get(0).getTowerColor()) {
                board.getArchipelago().mergeIslands(board.getArchipelago().getIslands().get(i).getIslandID(), 0);
                i = 0;
            }
        //Probably have to put winner method
    }

    public Student chooseStudent() {
        //to implement in GUI
        return Student.BLUE;
    }

    public int chooseIsland() {
        //to implement in GUI to choose the island
        return 1;
    }

    //Just to test Effect7
    public Student chooseStudentFromPlance() {
        return Student.YELLOW;
    }

    public Assistant chooseAssistant() {
        //To implement in GUI and to do the right implement with game's rules and with check if the Assistant is already played
        return Assistant.CAT;
    }

    public int chooseNumberOfMotherNatureMovement(int playerid) {
        int lastassistantplayedvalue = getPlayer(playerid).getLastassistantplayed().getValue();
        //To implement in GUI the choose from player
        int choosednumber=5; //Set =5 to do tests
        if(choosednumber>=1 && choosednumber<=lastassistantplayedvalue)
            return choosednumber;
        //To implement Exception if doesn't enter in if
        return 0;
    }


    public ArrayList<Player> getListOfPlayers() {
        return listOfPlayers;
    }


    public void verifyIslandInfluence(Island island) {
        // Check which Player has the most influence on an Island and arrange the towers appropriately.
        int maxscore = 0;
        int score = 0;
        Player playermaxscore = null;

        if (island.isStop()) {
            island.setStop(false);
            verifyType.addislandstop();
        } else {
            for (Player player : listOfPlayers) {
                for (Professor professor : player.getPlance().getProfessors())
                    for (Student student : island.getStudents())
                        if (professor.ordinal() == student.ordinal())
                            if (!verifyType.isNocolor() || (verifyType.isNocolor() && !verifyType.getStudent().equals(student)))
                                score++;
                for (int i=0; i<island.getNumOfTowers() && player.getPlance().getTower().equals(island.getTowerColor()) && !verifyType.isNotower(); i++)
                    score++;
                if (verifyType.getTwopoints()!=0 && player.getPlayerID() == verifyType.getTwopoints()) {
                    score = score + 2;
                    verifyType.setTwopoints(0);
                }
                if (score > maxscore) {
                    maxscore = score;
                    playermaxscore = player;
                }
            }
            verifyType.setNocolor(false);
            verifyType.setNotower(false);
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

        for (int i = 0; i < 5; i++) {
            for (Player player : listOfPlayers) {
                numofstudentcolorhall = player.getPlance().getNumberOfStudentHall(Student.values()[i]);
                if (verifyType.isProfessorcontroll()) {
                    if (numofstudentcolorhall >= numofstudentcolorhallmax) {
                        numofstudentcolorhallmax = numofstudentcolorhall;
                        playermax = player;
                    }
                } else if (numofstudentcolorhall > numofstudentcolorhallmax) {
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
        ArrayList<Player> playerOrder=new ArrayList<>();
        Random rnd = new Random();
        int num;
        int index=0;
        if(numOfPlayers==2)
            num = rnd.nextInt(2);
        else
            num = rnd.nextInt(3);
        for(int i=0;i<listOfPlayers.size();i++)
            if(listOfPlayers.get(i).getPlayerID()==num)
                index=i;

        for(int j=index; j<listOfPlayers.size(); j++)
            playerOrder.add(listOfPlayers.get(j));

        for(int k=0; k<index; k++)
            playerOrder.add(listOfPlayers.get(k));

        verifyPlayerOrder();
        for(Player player : this.playerorder)
            player.setPlayerState(PlayerState.WAITING);
        assistantPhase();
    }


}



