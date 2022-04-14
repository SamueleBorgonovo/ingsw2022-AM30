package it.polimi.ingsw.model.game;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.board.Character;
import it.polimi.ingsw.model.board.*;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Professor;

import java.util.ArrayList;

public class Game {
    private int gameID;
    private GameMode gameMode;
    private ArrayList<Player> listOfPlayers = new ArrayList<>();
    private GameState gameState;
    private Board board;
    private int numOfPlayers = 0;
    private VerifyType verifyType;
    private MotherNature mothernature;
    private int numplayerhasplayed=0;
    private int movementStudents=0;
    private ArrayList<Player> playerorder = new ArrayList<>();

    public Game(int gameID, GameMode gameMode, GameState gameState, Board board, MotherNature mothernature) {
        this.gameID = gameID;
        this.gameMode = gameMode;
        this.gameState = gameState;
        this.board = board;
        this.mothernature = mothernature;
    }

    public void setState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getState() {
        return gameState;
    }

    public void addPlayer(Player player, int playerid) {
        if (listOfPlayers.size() < 3) {
            listOfPlayers.add(playerid - 1, player);
            numOfPlayers++;
        }
        //Set the player in the position playerid-1, if player has id=1 he is in listofplayers.get(0)
    }

    public GameMode getGameMode() {
        return gameMode;
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

    public Island getIsland(int islandid){
        Island tempisland = null;
        for(int count=0;count<getBoard().getArchipelago().getNumOfIslands();count++){
            if(islandid == getBoard().getArchipelago().getIslands().get(count).getIslandID())
                tempisland = getBoard().getArchipelago().getIslands().get(count);
        }
        return tempisland;
    }

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
        boolean endOfTurn = true;
        if(getPlayer(playerID).getPlayerState()==PlayerState.CLOUDPHASE) {
            if(getCloud(cloudID).isChoosen() == false){
                    for(int count=0;count<getCloud(cloudID).getStudents().size();count++)
                        getPlayer(playerID).getPlance().addStudentEntrance(getCloud(cloudID).getStudents().get(count));
                    getCloud(cloudID).setChoosen(true);
                    numplayerhasplayed++;
                }
            else throw new WrongCloudException();

            if(numplayerhasplayed < numOfPlayers) {
                //Set next player to play
                getPlayer(playerID).setPlayerState(PlayerState.WAITING);
                playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.STUDENTPHASE);
                //Qui possiamo pure mandare un messaggio al server tipo "Tocca al secondo giocatore"
            }
            else {
                //All players played, ending round
                //Create a method StartTurn and use it on playerorder.get(0)
            }
            //Fix the code below
            //now we control if the turn is complete
            /*
            if(listOfPlayers.get(numOfPlayers).getPlayerID()==playerID)
                getPlayer(playerID).setPlayerState(PlayerState.WAITING);
            else
                for(Player player : listOfPlayers)
                    if (player.getPlayerID() == playerID){
                        player.setPlayerState(PlayerState.WAITING);
                        listOfPlayers.get(listOfPlayers.indexOf(player)+1).setPlayerState(PlayerState.ASSISTANTPHASE);
                    }
               */

        }
        else throw new InvalidTurnExceptions();
    }

    public void moveStudentToHall(int playerID, Student student) throws InvalidTurnExceptions, WrongStudentException{
        if(getPlayer(playerID).getPlayerState()==PlayerState.STUDENTPHASE) {
            if(getPlayer(playerID).getPlance().getEntrance().contains(student)){
                getPlayer(playerID).getPlance().addStudentHall(student);
                getPlayer(playerID).getPlance().getEntrance().remove(student);
                movementStudents++;
                if (movementStudents == numOfPlayers + 1) {
                    getPlayer(playerID).setPlayerState(PlayerState.MOTHERNATUREPHASE);
                    movementStudents=0;
                }
            } else throw new WrongStudentException();
        }
        else throw new InvalidTurnExceptions();
    }

    public void moveStudentToIsland(int playerID, Island island, Student student) throws InvalidTurnExceptions, WrongStudentException {
        if(getPlayer(playerID).getPlayerState()==PlayerState.STUDENTPHASE) {
            if(getPlayer(playerID).getPlance().getEntrance().contains(student)){
                island.addStudent(student);
                getPlayer(playerID).getPlance().getEntrance().remove(student);
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
                    numplayerhasplayed++;
                } else
                    throw new WrongAssistantException();
            }
            else{
                if(numplayerhasplayed==1){  //Second to choose, control if first has played same assistant
                    if(getPlayer(playerID).getAssistantCards().contains(assistant)) {
                        if(assistant != playerorder.get(numplayerhasplayed-1).getLastassistantplayed() || getPlayer(playerID).getAssistantCards().size()==1) {
                            getPlayer(playerID).removeAssistant(assistant);
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
                            numplayerhasplayed=0;
                        }else throw new WrongAssistantException();
                    }else throw new WrongAssistantException();
                }
            }
        }
        else throw new InvalidTurnExceptions();
    }

    public void moveMotherNature(int playerID, int numberOfMovement) throws InvalidTurnExceptions, WrongValueException {
        if(getPlayer(playerID).getPlayerState()==PlayerState.MOTHERNATUREPHASE) {
            if(numberOfMovement >= 1 && numberOfMovement <= getPlayer(playerID).getLastassistantplayed().getValue()) {
                for (int count = 0; count < numberOfMovement; count++)
                    mothernature.move();
                verifyIslandInfluence(getIsland(mothernature.isOn()));
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
        //Inizialitazion of the bag
       // ArrayList<Student> studentsColor = new ArrayList<>();
       // for (Student student : Student.values()) {
         //   for (int i = 0; i < 26; i++)
          //      studentsColor.add(student);
           // board.addStudentBag(studentsColor);
        //}

        //Initialization of every Plance
        int numTowers;
        int numStudentsCloud;
        if(listOfPlayers.size()==2){
            numStudentsCloud=3;
            numTowers=8;
        }
        else{
            numStudentsCloud=4;
            numTowers=6;
        }
        //Initialization of the plances
        for(Player player : listOfPlayers)
            for(int i=0; i<numTowers;i++)
                player.getPlance().addTower();
        //Initialization of the clouds
        for(Cloud cloud: board.getClouds())
            cloud.setStudents(board.getAndRemoveRandomBagStudent(numStudentsCloud));
    }

    public void endOfPlayerTurn() {
        if (verifyType.isProfessorcontroll())
            verifyType.setProfessorcontroll(false);
    }

}



