package it.polimi.ingsw.model.game;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.Character;
import it.polimi.ingsw.model.board.Island;
import it.polimi.ingsw.model.board.MotherNature;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Player;
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

    public Game(int gameID, GameMode gameMode, GameState gameState, Board board, VerifyType verifyType, MotherNature mothernature) {
        this.gameID = gameID;
        this.gameMode = gameMode;
        this.gameState = gameState;
        this.board = board;
        this.verifyType = verifyType;
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

    public void verifyProfessorControl(Professor professor, Player playerInControl, Player playerInTurn) {
        if (playerInControl == null)
            playerInTurn.getPlance().addProfessor(professor);
        else {
            Student[][] hallInTurn = new Student[4][4];
            Student[][] hallInControl = new Student[4][4];
            int numInTurn = 0, numInControl = 0;
            hallInTurn = playerInTurn.getPlance().getStudentHall();
            hallInControl = playerInControl.getPlance().getStudentHall();
            for (int i = 0; i < 10; i++) {
                if (hallInTurn[professor.ordinal()][i] != null)
                    numInTurn++;
                else if (hallInControl[professor.ordinal()][i] != null)
                    numInControl++;
                if (numInTurn > numInControl)
                    playerInControl.getPlance().removeProfessor(professor);
                playerInTurn.getPlance().addProfessor(professor);
            }

        }
    }


    public void startTurn() {
        setState(GameState.PLAYING);
    }

    public void endTurn() {
        setState(GameState.ENDED);
    }

    public void moveStudentToEntrance(int playerID, Student student) {
        for (Player player : listOfPlayers)
            if (playerID == player.getPlayerID()) {
                player.getPlance().addStudentEntrance(student);
            }
    }

    public void moveStudentToHall(int playerID, Student student) {
        for (Player player : listOfPlayers)
            if (playerID == player.getPlayerID()) {
                player.getPlance().addStudentHall(student);
                if (player.getPlance().getNumberOfStudent(student) % 3 == 0)
                    player.addCoins();
            }
    }

    public void moveStudentToIsland(int playerID, Island island, Student student) {
        for (Player player : listOfPlayers)
            if (playerID == player.getPlayerID())
                island.addStudent(student);
    }

    public void useAssistant(int playerID, Assistant assistant) {
        for (Player player : listOfPlayers)
            if (playerID == player.getPlayerID())
                player.getAssistantCards().remove(assistant);
    }

    public void moveMotherNature(int playerid) {
        int numberofMovement = chooseNumberOfMotherNatureMovement(playerid);
        for(int count=0;count<numberofMovement;count++)
            mothernature.move();
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
                    board.getArchipelago().getIslands().get(i).getTowers().get(0) ==
                            board.getArchipelago().getIslands().get(i + 1).getTowers().get(0)) {
                board.getArchipelago().mergeIslands(board.getArchipelago().getIslands().get(i).getIslandID(),
                        board.getArchipelago().getIslands().get(i + 1).getIslandID());
                i = 0;
            } else if (i == board.getArchipelago().getNumOfIslands() - 1 &&
                    board.getArchipelago().getIslands().get(i).getTowers().get(0) ==
                            board.getArchipelago().getIslands().get(0).getTowers().get(0)) {
                board.getArchipelago().mergeIslands(board.getArchipelago().getIslands().get(i).getIslandID(), 0);
                i = 0;
            }
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
                verifyType.setNocolor(false);
                for (Tower tower : island.getTowers())
                    if (player.getPlance().getTowers().get(0).equals(tower) && !verifyType.isNotower())
                        score++;
                verifyType.setNotower(false);
                if (verifyType.isTwopoints()) {
                    score = score + 2;
                    verifyType.setTwopoints(false);
                }
                if (score > maxscore) {
                    maxscore = score;
                    playermaxscore = player;
                }
            }
            if (playermaxscore != null && !playermaxscore.getPlance().getTowers().get(0).equals(island.getTowers().get(0)) && maxscore != 0) {
                if (island.getTowers().isEmpty())
                    island.addTower(playermaxscore.getPlance().getTowers().get(0));
                else {
                    for (Player player : listOfPlayers)
                        if (player.getPlance().getTowers().get(0).equals(island.getTowers().get(0)))
                            for (Tower tower : island.getTowers())
                                player.getPlance().addTower();
                    island.changeTowers(playermaxscore.getPlance().getTowers().get(0));
                }
                for (Tower tower : island.getTowers())
                    playermaxscore.getPlance().removeTower();
            }
        }
    }

    void verifyProfessorControll() {
        // Controll and check Professors.
        int count = 0;
        int countmax = 0;
        Player playermax = null;

        for (int i = 0; i < 5; i++) {
            for (Player player : listOfPlayers) {
                count = 0;
                for (int j = 0; j >= 0 && j < 10; j++)
                    if (player.getPlance().getStudentHall()[i][j] != null)
                        count++;
                if (verifyType.isProfessorcontroll()) {
                    if (count >= countmax) {
                        countmax = count;
                        playermax = player;
                    }
                } else if (count > countmax) {
                    countmax = count;
                    playermax = player;
                }
            }
            countmax = 0;
            if (playermax != null && !playermax.getPlance().getProfessors().contains(Professor.values()[i])) {
                for (Player player : listOfPlayers)
                    if (player.getPlance().getProfessors().contains(Professor.values()[i]))
                        player.getPlance().removeProfessor(Professor.values()[i]);
                playermax.getPlance().addProfessor(Professor.values()[i]);
            }
            playermax = null;
        }
    }

    public ArrayList<Player> VerifyPlayerOrder() {
        ArrayList<Player> playerorder = new ArrayList<>();
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
        ArrayList<Student> studentsColor = new ArrayList<>();
        for (Student student : Student.values()) {
            for (int i = 0; i < 26; i++)
                studentsColor.add(student);
            board.addStudentBag(studentsColor);
        }

    }

    public void endOfTurn() {
        if (verifyType.isProfessorcontroll())
            verifyType.setProfessorcontroll(false);
    }

}



