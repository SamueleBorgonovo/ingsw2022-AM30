package org.example;
import java.util.ArrayList;

public class Game {
    private final int gameID;
    private final GameMode gameMode;
    private ArrayList<Player>listOfPlayers = new ArrayList<>();
    private GameState gameState;
    private Board board;
    private final int NumOfPlayers;

    public Game(int gameID, GameMode gameMode, ArrayList<Player> listOfPlayers, GameState gameState, Board board, int numOfPlayers) {
        this.gameID = gameID;
        this.gameMode = gameMode;
        this.listOfPlayers = listOfPlayers;
        this.gameState = gameState;
        this.board = board;
        NumOfPlayers = numOfPlayers;
    }

    public void setState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getState() {
        return gameState;
    }

    public void addPlayer(Player player){
        listOfPlayers.add(player);
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public int getNumOfPlayers(){
        return listOfPlayers.size();
    }

    public Player winner(){
        ArrayList<Player> playersCandidate = new ArrayList<>();
        Player playerChosen = null ;
        int minTowers = 8;
        int maxProfessor = 0;
        boolean gameIsFinished = false;

        if(board.getArchipelago().getNumOfIslands() == 3 ||
           board.getNumOfStudentsBag() == 0)
            gameIsFinished = true;

        for (Player player : listOfPlayers)

            if (player.getPlance().getNumOfTowers()== 0 ||
                player.getAssistantCards().size() == 0)
                    gameIsFinished = true;


        if(gameIsFinished)
            {
                for(Player player1 : listOfPlayers)
                    if(player1.getPlance().getNumOfTowers() < minTowers )
                        minTowers = player1.getPlance().getNumOfTowers();
                for(Player player1 : listOfPlayers)
                    if(player1.getPlance().getNumOfTowers() == minTowers )
                        playersCandidate.add(player1);
            }
        for(Player player2 : playersCandidate) {
            if (player2.getPlance().getProfessor().size() > maxProfessor) {
                maxProfessor = player2.getPlance().getProfessor().size();
                playerChosen = player2;
            }


        }
    return playerChosen;}

    public void moveStudentToEntrance(int playerID, Student student)
    {
        for(Player player : listOfPlayers)
            if(playerID == player.getPlayerID())
                player.getPlance().getEntrance().add(student);
    }

    public void moveStudentToIsland(int playerID, Island island, Student student){
        for(Player player : listOfPlayers)
            if(playerID == player.getPlayerID())
                island.addStudents(student);
    }

    public void useAssistant(int playerID, Assistant assistant){
        for(Player player : listOfPlayers)
            if(playerID == player.getPlayerID())
                player.getAssistantCards().remove(assistant);
    }

    public void startTurn(){
        setState(GameState.PLAYING);
    }

    public void endTurn(){
        setState(GameState.ENDED);
    }

    public void moveMotherNature (Island island){
        for(Island island1 : board.getArchipelago().getIslands())
            if(island1.isMotherNature())
                island1.setMotherNature(false);
        island.setMotherNature(true);
    }
}

