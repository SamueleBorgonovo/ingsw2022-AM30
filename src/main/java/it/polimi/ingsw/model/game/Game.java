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

public class Game implements GameInterface {
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

    public ArrayList<Player> getPlayers(){
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
                gameState=GameState.PLAYING;
            }
        }
        else throw new ReconnectedException();
    }

    public boolean setDisconnectPlayer(int playerid){
        boolean callhandler=false;
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
                //shutdown(false);
            } else {
                if (counter == numOfPlayers - 2) {
                    gameState=GameState.WAITINGFORRECONNECTION;
                    //startTimer();
                }

                    if (getPlayer(playerid).getPlayerState() == PlayerState.WAITING) {
                        getPlayer(playerid).setPlayerState(PlayerState.DISCONNECTED);
                    } else if (getPlayer(playerid).getPlayerState() == PlayerState.ASSISTANTPHASE) {
                        callhandler=true;
                        numplayerhasplayed++;
                        getPlayer(playerid).setPlayerState(PlayerState.DISCONNECTED);
                        if (numplayerhasplayed == numOfPlayers) {
                            verifyPlayerOrder();
                            int tmp = searchfisrt();
                            if (tmp == -1) {
                                //shutdown(false);
                            }
                            playerorder.get(tmp).setPlayerState(PlayerState.STUDENTPHASE);
                        } else playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.ASSISTANTPHASE);
                    } else {
                        movementStudents=0;
                        callhandler=true;
                        numplayerhasplayed++;
                        getPlayer(playerid).setPlayerState(PlayerState.DISCONNECTED);
                        if (numplayerhasplayed == numOfPlayers) {
                            //All players played, ending round
                            numplayerhasplayed = 0;
                            for (int count = 0; count < getBoard().getClouds().size(); count++) {
                                getBoard().getClouds().get(count).setChoosen(false);
                            }

                            for (Player player : listOfPlayers) {
                                player.setCharacterPlayed(false);
                            }
                            startRound();  //Start the new round
                        } else playerorder.get(numplayerhasplayed).setPlayerState(PlayerState.STUDENTPHASE);

                    }
            }
        }
    return callhandler;
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
            player.setCoins(1);
        }
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
        if(numOfPlayers==2) {
            for (Player player : playerorder)
                if (player.getPlance().getEntrance().size() != 7) {
                    int cloudid = -1;
                    for (Cloud cloud : board.getClouds())
                        if (!cloud.isChoosen()) {
                            cloudid = cloud.getCloudID();
                            cloud.setChoosen(true);
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
                        if (!cloud.isChoosen()) {
                            cloudid = cloud.getCloudID();
                            cloud.setChoosen((true));
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
            getBoard().getClouds().get(count).setChoosen(false);
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




    public int winnerEndRound() {
        int gameIsFinished=0;

        if (board.getNumOfStudentsBag() == 0)
            gameIsFinished = 1;

        for (Player player : listOfPlayers)
            if (player.getAssistantCards().size() == 0)
                gameIsFinished = 2;
        return gameIsFinished;

        //Invece di fare una return facciamo che chiama un metodo in gameHandler per dire che ha vinto con dentro un
        //Arraylist di chi ha vinto (Arraylist perchè si può pareggiare)

    }

    public int winnerIstantly(){
        int gameIsFinished=0;
        if (board.getArchipelago().getNumOfIslands() == 3)
            gameIsFinished = 1;
        for (Player player : listOfPlayers)
            if (player.getPlance().getNumOfTowers() == 0)
                gameIsFinished = 2;
        return gameIsFinished;
    }

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
    //Return true if is the last cloud of the round
    public boolean selectCloud(int playerID, int cloudID) throws InvalidTurnException, InvalidCloudException {
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
                    getPlayer(playerID).getPlance().removeStudentEntrance(student);
                    movementStudents++;
                    if (movementStudents == numOfPlayers + 1) {
                        getPlayer(playerID).setPlayerState(PlayerState.MOTHERNATUREPHASE);
                        movementStudents = 0;
                    }
                } else throw new InvalidStudentException();
            } else throw new InvalidTurnException();

    }

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
                        //All players has played the assistant
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
                                //All players has played the assistant
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
        int maxScore = 0;
        int score;
        Player playerMaxScore = null;
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
                if (score > maxScore) {
                    maxScore = score;
                    playerMaxScore = player;
                }
            }
            effectHandler.setNocolor(false);
            effectHandler.setNotower(false);
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

    public void verifyProfessorControl() {
        // Controll and check Professors.
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
                if (numOfStudentColorHall > numOfStudentColorHallMax || (effectHandler.isProfessorcontroll() && numOfStudentColorHall >= numOfStudentColorHallMax)) {
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
            for (int counter = 0; counter < playerorder.size(); counter++) {
                if (lasttakenplayer.getPlayerID() == getNumOfPlayers()) {
                    if (playerorder.get(counter).getPlayerID() == 1)
                        tempplayer = playerorder.get(counter);
                } else {
                    if (playerorder.get(counter).getPlayerID() == lasttakenplayer.getPlayerID() + 1) {
                        tempplayer = playerorder.get(counter);
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

    @Override
    public int getMotherNatureIsland() {
        return board.getArchipelago().getMothernature().isOn();
    }

    public ArrayList<Player> getPlayerorder(){
        return playerorder;
    }

    public void setPlayerorder(ArrayList<Player> playerorder) {
        this.playerorder = playerorder;
    }

    public ArrayList<PlayerView> getPlayersView(){
        ArrayList<PlayerView> tempplayerview = new ArrayList<>();
        for(Player player : listOfPlayers)
            tempplayerview.add(player.getPlayerView());
        return tempplayerview;
    }

    public Characters searchCharacter(CharacterView characterView){
        for(Characters character : getBoard().getCharacters())
            if(character.getEffect().getName().equals(characterView.getName()))
                return character;
        return null;
    }

    public int getNumPlayerDisconnected(){
        int num=0;
        for(Player player : listOfPlayers)
            if(player.getPlayerState()==PlayerState.DISCONNECTED)
                num++;
        return num;
    }
}



