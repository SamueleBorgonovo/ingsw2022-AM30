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
        for (Player player : listOfPlayers){
            if (player.getPlance().getNumOfTowers()== 0)
                return player;
            else if
        }
    }
}

