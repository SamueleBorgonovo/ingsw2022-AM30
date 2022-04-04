package it.polimi.ingsw.model;
import java.util.ArrayList;

public class Game {
    private int gameID;
    private GameMode gameMode;
    private ArrayList<Player>listOfPlayers = new ArrayList<>();
    private GameState gameState;
    private Board board;
    private int numOfPlayers;
    private VerifyType verifyType;
    private MotherNature mothernature;

    public Game(int gameID, GameMode gameMode, GameState gameState, Board board, int numOfPlayers, VerifyType verifyType, MotherNature mothernature) {
        this.gameID = gameID;
        this.gameMode = gameMode;
        this.gameState = gameState;
        this.board = board;
        this.numOfPlayers = numOfPlayers;
        this.verifyType = verifyType;
        this.mothernature = mothernature;
    }

    public void setState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getState() {
        return gameState;
    }

    public void addPlayer(Player player){
        if(listOfPlayers.size()<getNumOfPlayers())
            listOfPlayers.add(player);
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Board getBoard(){return board;}

    public int getNumOfPlayers(){
        return numOfPlayers;
    }

    public Player getPlayer(int playerid){
        Player tempplayer=null;
        for(int count=0;count<numOfPlayers;count ++){
            if(playerid == listOfPlayers.get(count).getPlayerID()){
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
            if (player2.getPlance().getProfessor().size() > maxProfessor) {
                maxProfessor = player2.getPlance().getProfessor().size();
                playerChosen = player2;
            }


        }
        return playerChosen;
    }

    public void verifyProfessorControl(Professor professor, Player playerInControl, Player playerInTurn){
        if(playerInControl==null)
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


    public void startTurn(){
        setState(GameState.PLAYING);
    }

    public void endTurn(){
        setState(GameState.ENDED);
    }

    public void moveStudentToEntrance(int playerID, Student student)
    {
        for(Player player : listOfPlayers)
            if(playerID == player.getPlayerID()) {
                player.getPlance().addStudentEntrance(student);
            }
    }

    public void moveStudentToHall(int playerID, Student student){
        for(Player player : listOfPlayers)
            if(playerID == player.getPlayerID()){
                player.getPlance().addStudentHall(student);
                if(player.getPlance().getNumberOfStudent(student) % 3 ==0)
                    player.addCoins();
            }
    }

    public void moveStudentToIsland(int playerID, Island island, Student student){
        for(Player player : listOfPlayers)
            if(playerID == player.getPlayerID())
                island.addStudent(student);
    }

    public void useAssistant(int playerID, Assistant assistant){
        for(Player player : listOfPlayers)
            if(playerID == player.getPlayerID())
                player.getAssistantCards().remove(assistant);
    }

    public void moveMotherNature (Island island){
        mothernature.move(island.getIslandID());
        // aggiungere il metodo che verifica chi controlla l'isola
    }

    public void useCharacter(int playerID, Character character){
        for(Player player: listOfPlayers)
            if(player.getPlayerID() == playerID) {
                player.removeCoins(character.getCost());
                character.setUsed(true);
            }
    }

    public void setVerifyType(VerifyType verifyType) {
        this.verifyType = verifyType;
    }

    public void verifyMergeableIsland(){
        for(int i=0; i<board.getArchipelago().getNumOfIslands() ; i++)
            if(i!=board.getArchipelago().getNumOfIslands() -1 &&
                board.getArchipelago().getIslands().get(i).getTowers().get(0) ==
                board.getArchipelago().getIslands().get(i+1).getTowers().get(0)){
                    board.getArchipelago().mergeIslands(board.getArchipelago().getIslands().get(i).getIslandID(),
                                            board.getArchipelago().getIslands().get(i+1).getIslandID());
                    i=0;
            }
            else if(i==board.getArchipelago().getNumOfIslands() -1 &&
                    board.getArchipelago().getIslands().get(i).getTowers().get(0) ==
                            board.getArchipelago().getIslands().get(0).getTowers().get(0)) {
                board.getArchipelago().mergeIslands(board.getArchipelago().getIslands().get(i).getIslandID(), 0);
                i = 0;
            }
    }

    public Student chooseStudent(){
        //to implement in GUI
        return Student.BLUE;
    }

    public int chooseIsland(){
        //to implement in GUI to choose the island
        return 1;
    }
}


