package it.polimi.ingsw.model.game;

import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.board.*;
import it.polimi.ingsw.model.player.*;
import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game implements GameInterface {
    private final int TIMER = 120000;
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
    private ArrayList<Wizard> wizardChoosen = new ArrayList<>();
    private ArrayList<Assistant> usedAssistant = new ArrayList<>();
    private GameHandler gameHandler;
    private Thread timer;

    public Game(GameMode gameMode, int numofplayers, GameHandler gameHandler) {
        this.gameMode = gameMode;
        this.gameHandler = gameHandler;
        this.numOfPlayers = numofplayers;
        gameState = GameState.WAITINGFORPLAYERS;
        board = new Board(gameMode, numofplayers);
        effectHandler = new EffectHandler();
        for(Characters character : board.getCharacters())
            character.getEffect().inizialize(this);
    }

    public ArrayList<Wizard> getWizardAvailable() {
        ArrayList<Wizard> wizardsAvailable = new ArrayList<>();
        for(Wizard wizard : Wizard.values())
            if(!wizardChoosen.contains(wizard))
                wizardsAvailable.add(wizard);

        return wizardsAvailable;

    }

    public void addWizardChoosen(Wizard wizard){
        wizardChoosen.add(wizard);
    }

    public void setWizard(int playerid,Wizard wizard) throws InvalidWizardException {
        if(!wizardChoosen.contains(wizard)) {
            getPlayer(playerid).setWizard(wizard);
            addWizardChoosen(wizard);
        }
        else throw new InvalidWizardException();
    }

    public void setCharacterInUse(Characters character){characterInUse=character;}


    public GameState getState() {
        return gameState;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public ArrayList<PlayerInterface> getPlayers(){
        return new ArrayList<>(listOfPlayers);
    }


    public void setReconnectedPlayer(int playerid) throws ReconnectedException {
        if(getPlayer(playerid).getPlayerState() == PlayerState.DISCONNECTED) {
            getPlayer(playerid).setPlayerState(PlayerState.RECONNECTED);
            int count=0;
            for(Player player : listOfPlayers)
                if(player.getPlayerState()!=PlayerState.DISCONNECTED)
                    count++;
            if(count==2) {
                timer.interrupt();
                gameState=GameState.PLAYING;
            }
        }
        else throw new ReconnectedException();
    }

    public void setDisconnectPlayer(int playerid){

        if(gameState==GameState.WAITINGFORPLAYERS){
            Wizard wizardchoose = getPlayer(playerid).getWizard();
            wizardChoosen.remove(wizardchoose);
            listOfPlayers.remove(getPlayer(playerid)); //if game not already started, player can't reconnect
        }
        else {
            int counter = 0;
            for (Player player : listOfPlayers) {
                if (player.getPlayerState() == PlayerState.DISCONNECTED)
                    counter++;
            }
            if (counter == numOfPlayers - 1) {
                getPlayer(playerid).setPlayerState(PlayerState.DISCONNECTED);
                shutdown(false);
            } else {
                if (counter == numOfPlayers - 2) {
                    gameState=GameState.WAITINGFORRECONNECTION;
                    startTimer();
                }

                    if (getPlayer(playerid).getPlayerState() == PlayerState.WAITING) {
                        getPlayer(playerid).setPlayerState(PlayerState.DISCONNECTED);
                    } else if (getPlayer(playerid).getPlayerState() == PlayerState.ASSISTANTPHASE) {
                        numplayerhasplayed++;
                        getPlayer(playerid).setPlayerState(PlayerState.DISCONNECTED);
                        if (numplayerhasplayed == numOfPlayers) {
                            verifyPlayerOrder();
                            int tmp = searchfisrt();
                            if (tmp == -1) {
                                shutdown(false);
                            }
                            playerorder.get(tmp).setPlayerState(PlayerState.STUDENTPHASE);
                        } else playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.ASSISTANTPHASE);
                    } else {
                        numplayerhasplayed++;
                        getPlayer(playerid).setPlayerState(PlayerState.DISCONNECTED);
                        if (numplayerhasplayed == numOfPlayers) {
                            //All players played, ending round
                            numplayerhasplayed = 0;
                            for (int count = 0; count < getBoard().getClouds().size(); count++) {
                                getBoard().getClouds().get(count).setChoosen(false);
                            }

                            for (Player player : listOfPlayers) {
                                player.setPlayerState(PlayerState.WAITING);
                                player.setCharacterPlayed(false);
                            }
                            startRound();  //Start the new round
                        } else playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.STUDENTPHASE);

                    }
            }
        }

    }

    public void startTimer(){
        timer = new Thread(()->{
            try{
                Thread.sleep(TIMER);
                winner();
            } catch (InterruptedException e) { }
        });
        timer.start();
    }

    public void shutdown(boolean gameEnded){

        ArrayList<String> nicknames = new ArrayList<>();
        for(Player player : listOfPlayers)
            nicknames.add(player.getNickname());

        gameHandler.closeGame(nicknames,gameEnded);

    }


    public int searchfisrt(){
        for(int count=0;count<playerorder.size();count++){
            if(playerorder.get(count).getPlayerState() != PlayerState.DISCONNECTED)
                return count;
        }
        return -1;
    }

    public int addPlayer(String nickname) {
        Player player = new Player(nickname);

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
        if(gameMode.equals(GameMode.EXPERTMODE)) {
            player.addCoins();
            player.addCoins();
        }
        //if (listOfPlayers.size() == numOfPlayers)
            //this.startGame();
        return listOfPlayers.size();
    }

    public Board getBoard() {
        return board;
    }

    public boolean checkPlayerState(int playerid){
        if(getPlayer(playerid).getPlayerState()==PlayerState.DISCONNECTED)
            return true;
        return false;
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

    public void startRound(){
        for(Player player : listOfPlayers)
            if(player.getPlayerState() == PlayerState.RECONNECTED)
                player.setPlayerState(PlayerState.WAITING);

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
            getPlayer(tmpplayerid).setPlayerState(PlayerState.ASSISTANTPHASE);
        }
        //shutdown del game

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
        //Invece di fare una return facciamo che chiama un metodo in gameHandler per dire che ha vinto con dentro un
        //Arraylist di chi ha vinto (Arraylist perchè si può pareggiare)
        return playerChosen;
    }

    public void selectCloud(int playerID, int cloudID) throws InvalidTurnException, InvalidCloudException {
        if(getPlayer(playerID).getPlayerState()==PlayerState.CLOUDPHASE) {
            if(!getBoard().getCloud(cloudID).isChoosen()){
                    for(int count=0;count<getBoard().getCloud(cloudID).getStudents().size();count++)
                        getPlayer(playerID).getPlance().addStudentEntrance(getBoard().getCloud(cloudID).getStudents().get(count));
                    getBoard().getCloud(cloudID).setChoosen(true);
                    numplayerhasplayed++;
                }
            else throw new InvalidCloudException();

            if(numplayerhasplayed < numOfPlayers) {
                //Set next player to play
                getPlayer(playerID).setPlayerState(PlayerState.WAITING);
                if (effectHandler.isProfessorcontroll())
                    effectHandler.setProfessorcontroll(false);
                if(playerorder.get(numplayerhasplayed).getPlayerState()!=PlayerState.DISCONNECTED && playerorder.get(numplayerhasplayed).getPlayerState()!=PlayerState.RECONNECTED)
                    playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.STUDENTPHASE);
                    //Qui possiamo pure mandare un messaggio al server tipo "Tocca al secondo giocatore"
                else{
                    numplayerhasplayed++;
                    if(numplayerhasplayed==numOfPlayers){
                        //All players played, ending round
                        numplayerhasplayed=0;
                        for(int count=0;count<getBoard().getClouds().size();count++){
                            getBoard().getClouds().get(count).setChoosen(false);
                        }

                        for (Player player : listOfPlayers) {
                            player.setPlayerState(PlayerState.WAITING);
                            player.setCharacterPlayed(false);
                        }
                        startRound();  //Start the new round
                    }
                    else playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.STUDENTPHASE);
                    //Qui possiamo pure mandare un messaggio al server tipo "Tocca al secondo giocatore"
                }

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
               startRound();  //Start the new round
            }

        }
        else throw new InvalidTurnException();
    }

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

    public void moveStudentToIsland(int playerID, int islandID, Student student) throws InvalidTurnException, InvalidStudentException {

            if (getPlayer(playerID).getPlayerState() == PlayerState.STUDENTPHASE) {
                if (getPlayer(playerID).getPlance().getEntrance().contains(student)) {
                    Island island = getBoard().getArchipelago().getSingleIsland(islandID);
                    island.addStudent(student);
                    getPlayer(playerID).getPlance().removeStudentFromHall(student);
                    movementStudents++;
                    if (movementStudents == numOfPlayers + 1) {
                        getPlayer(playerID).setPlayerState(PlayerState.MOTHERNATUREPHASE);
                        movementStudents = 0;
                    }
                } else throw new InvalidStudentException();
            } else throw new InvalidTurnException();

    }

    public void useAssistant(int playerID,Assistant assistant) throws InvalidTurnException, InvalidAssistantException{

        if(getPlayer(playerID).getPlayerState()== PlayerState.ASSISTANTPHASE){ //Check that is the right id of the player that has to play
            if(!usedAssistant.contains(assistant) && getPlayer(playerID).getAssistantCards().contains(assistant)){
                getPlayer(playerID).removeAssistant(assistant);
                usedAssistant.add(assistant);
                getPlayer(playerID).setLastassistantplayed(assistant);
                getPlayer(playerID).setAssistantPlayed(true);
                numplayerhasplayed++;
                getPlayer(playerID).setPlayerState(PlayerState.WAITING);
                if(numplayerhasplayed == numOfPlayers){
                    //All players has played the assistant
                    numplayerhasplayed=0;
                    verifyPlayerOrder();
                    usedAssistant.clear();
                    playerorder.get(0).setPlayerState(PlayerState.STUDENTPHASE);
                }else{
                    if(playerorder.get(numplayerhasplayed).getPlayerState()!=PlayerState.DISCONNECTED && playerorder.get(numplayerhasplayed).getPlayerState()!= PlayerState.RECONNECTED)
                        playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.ASSISTANTPHASE);
                    else{
                        numplayerhasplayed++;
                        if(numplayerhasplayed==numOfPlayers){
                            //All players has played the assistant
                            numplayerhasplayed=0;
                            verifyPlayerOrder();
                            usedAssistant.clear();
                            playerorder.get(0).setPlayerState(PlayerState.STUDENTPHASE);
                        }
                        else
                            playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.ASSISTANTPHASE);
                    }

                }
            }
            else throw new InvalidAssistantException();
        }else throw new InvalidTurnException();
    }


    public void moveMotherNature(int playerID, int numberOfMovement) throws InvalidTurnException, InvalidValueException {
        if(getPlayer(playerID).getPlayerState()==PlayerState.MOTHERNATUREPHASE) {
            if (!this.getEffectHandler().getTwomoremoves()) {  //Check if effect4 is in use
                if (numberOfMovement >= 1 && numberOfMovement <= getPlayer(playerID).getLastassistantplayed().getValue()) {
                    for (int count = 0; count < numberOfMovement; count++)
                        this.getBoard().getArchipelago().getMothernature().move(this.getBoard().getArchipelago().getNumOfIslands());
                    verifyIslandInfluence(getBoard().getArchipelago().getSingleIsland((this.getBoard().getArchipelago().getMothernature().isOn())).getIslandID());
                    this.getBoard().getArchipelago().verifyMergeableIsland();
                    //probably have to put winner method
                    getPlayer(playerID).setPlayerState(PlayerState.CLOUDPHASE);
                } else throw new InvalidValueException();
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
                } else throw new InvalidValueException();
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

    public void CharacterStudentsPhase(int playerID,ArrayList<Student> students) throws InvalidTurnException, InvalidStudentEffectException {
        if(getPlayer(playerID).getPlayerState() == PlayerState.CHARACTHERSTUDENTSPHASE) {
            effectHandler.setStudentschoose(students);
            characterInUse.getEffect().secondPartEffect(this, playerID);
        }
        else throw new InvalidTurnException();
    }

    public EffectHandler getEffectHandler() {
        return effectHandler;
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
            if((listOfPlayers.get(count).getPlayerState()!=PlayerState.DISCONNECTED) && (listOfPlayers.get(count).getPlayerState()!=PlayerState.RECONNECTED))
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
        for(int count=0;count<playerorder.size();count++){
            if(playerorder.get(count).getPlayerState()==PlayerState.DISCONNECTED || playerorder.get(count).getPlayerState()==PlayerState.RECONNECTED){
                Collections.swap(playerorder,count,playerorder.size()-1);
            }

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



