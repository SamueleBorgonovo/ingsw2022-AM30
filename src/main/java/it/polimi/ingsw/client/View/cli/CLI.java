package it.polimi.ingsw.client.View.cli;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.client.View.cli.Graphical.Graphic;
import it.polimi.ingsw.messages.toServer.*;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.board.Cloud;
import it.polimi.ingsw.model.game.EffectHandler;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CLI extends View {

    private static final int MIN_PORT = 1024;
    private static final int MAX_PORT = 49151;

    private final Graphic graphic = new Graphic();
    private Client client;
    private InputParser inputParser = new InputParser();
    private ArrayList<PlayerInterface> players= new ArrayList<>();
    private Board board;
    private CharacterInput characterInput;
    EffectHandler effectHandler = new EffectHandler();
    private String nickname;
    private PlayerInterface player;
    private boolean isFirst=true;
    private GameMode gameMode;
    private boolean character4played = false;


    public void init() throws IOException, ClassNotFoundException, NumberFormatException {
        boolean check=false;
        this.graphic.printLogo();

        while(!check){
            Scanner stdin = new Scanner(System.in);
            System.out.println("Please insert the ip-address");
            String ip = stdin.nextLine();
            System.out.println("Please insert port");
            int port=inputParser.intParser();;
            while (port < MIN_PORT || port > MAX_PORT) {
                System.out.println("Port Number is not valid, please insert a new one");
                port = inputParser.intParser();
            }
            Client client = new Client(ip, port, this);
            check = client.setupConnection();
            if(check)
                this.client = client;
            else
                System.out.println("Connection not valid. Please try again");
        }
        chooseNickname(true, false);
    }

    @Override
    public void chooseNickname(boolean validNickname, boolean reconnect) {
        if (isFirst) {
            isFirst = false;
            Scanner stdin = new Scanner(System.in);
            System.out.println("Choose your nickname");
            this.nickname = stdin.nextLine();
            client.sendMessage(new ChooseNicknameMessage(nickname, false));
        } else {
            if (validNickname) {
                client.setNickname(this.nickname);
                if (reconnect) {
                    if(tryToReconnect()) {
                        client.sendMessage(new ChooseNicknameMessage(nickname, true));
                    }else {
                        client.sendMessage(new ChooseNicknameMessage(nickname, false));
                        client.gameSetup();
                    }
                }else {
                    client.gameSetup();
                }
            }else {
                System.out.println("Invalid nickname. Please try again");
                Scanner stdin = new Scanner(System.in);
                System.out.println("Choose your nickname");
                this.nickname = stdin.nextLine();
                client.sendMessage(new ChooseNicknameMessage(nickname, false));
            }
        }
    }

    @Override
    public boolean tryToReconnect() {
        System.out.println("Do you want to reconnect to the last game?   y | n");
        Scanner stdin = new Scanner(System.in);
        String input=stdin.nextLine().toLowerCase();;

        boolean check=false;
        while(!check){
            if(input.equals("y") || input.equals("n"))
                check=true;
            else {
                System.out.println("Not valid. Try again");
                input = stdin.nextLine().toLowerCase();
            }
        }
        return input.equals("y");
    }


    @Override
    public GameMode chooseGameMode(){
        player= new Player(this.nickname);
        Scanner stdin = new Scanner(System.in);
        System.out.println("Press s for SimpleMode or e for ExpertMode  s | e");
        String choice = stdin.nextLine();
        boolean pass = false;
        while (!pass){
            if (choice.equalsIgnoreCase("s") || choice.equalsIgnoreCase("e")) {
                pass = true;;
            }
            else {
                System.out.println("Selection not valid. Please try again");
                choice = stdin.nextLine();
            }
        }

        if(choice.equalsIgnoreCase("s")) {
            this.gameMode = GameMode.SIMPLEMODE;
            return GameMode.SIMPLEMODE;
        }
        else {
            this.gameMode = GameMode.EXPERTMODE;
            return GameMode.EXPERTMODE;
        }
    }

    @Override
    public int chooseNumberOfPlayers(){
        System.out.println("Choose the number of the players ( 2 | 3 )");
        int choice = inputParser.intParser();
        boolean pass = false;
        while (!pass) {
            if (choice==2 || choice==3)
                pass = true;
            else {
                System.out.println("Selection not valid. Please try again");
                choice = inputParser.intParser();
            }
        }
        return choice;
    }

    @Override
    public void chooseWizard(ArrayList<Wizard> avaiableWizards){
        Wizard wizardChosen = null;
        Scanner stdin = new Scanner(System.in);
        boolean checkwizard=false;
        System.out.println("Choose one Wizard between this available by typing his number associated");
        this.graphic.printWizards(avaiableWizards);
        int wizardInt = inputParser.intParser();
        while (!checkwizard) {
            if(wizardInt>=1 && wizardInt<= avaiableWizards.size()){
                wizardChosen=avaiableWizards.get(wizardInt-1);
                checkwizard=true;
            }
            else {
                System.out.println("Selection not valid. Try again");
                wizardInt = inputParser.intParser();
            }
        }
        client.setWizard(wizardChosen);
        ChooseWizardMessage message = new ChooseWizardMessage(wizardChosen);
        this.client.sendMessage(message);
        }

    @Override
    public void chooseAssistant(){
        ArrayList<Assistant> avaiableAssistant = this.player.getAssistantCards();
        Scanner stdin = new Scanner(System.in);
        Assistant assistantChosen= null;
        System.out.println("Choose one Assistant between this available by typing his number associated");
        this.graphic.printAssistants(avaiableAssistant, this.client.getWizard());
        int assistantInt = inputParser.intParser();
        boolean check = false;
        while(!check) {
            for (Assistant assistantCheck : avaiableAssistant)
                if (assistantInt== avaiableAssistant.indexOf(assistantCheck)+1) {
                    assistantChosen=assistantCheck;
                    check=true;
                    break;
                }
            if(!check) {
                System.out.println("Selection not valid. Try again");
                assistantInt = stdin.nextInt();
            }
        }
        ChooseAssistantMessage message = new ChooseAssistantMessage(assistantChosen);
        client.sendMessage(message);
    }

    @Override
    public PossibleAction chooseNextAction(PlayerState playerState) {
        Scanner stdin = new Scanner(System.in);
        boolean check = false;
        PossibleAction actionChosen = null;
        System.out.println("Choose your next action by typing the action's number");
        int actionNum = 0 ;
        while(!check){
            if(playerState == PlayerState.STUDENTPHASE && this.gameMode == GameMode.SIMPLEMODE){
                System.out.println("Possible actions:");
                System.out.println("1) Move one student to the hall");
                System.out.println("2) Move one student to one island");
                actionNum = inputParser.intParser();
                if(actionNum==1) {
                    actionChosen = PossibleAction.MOVESTUDENTTOHALL;
                    check = true;
                }
                else if(actionNum==2){
                    actionChosen = PossibleAction.MOVESTUDENTT0ISLAND;
                    check = true;
                }
                else {
                    System.out.println("Selection not valid. Try again");
                    System.out.println("");
                }
            }
            else if(playerState == PlayerState.STUDENTPHASE && this.gameMode == GameMode.EXPERTMODE){
                    System.out.println("Possible actions:");
                    System.out.println("1) Move one student to the hall");
                    System.out.println("2) Move one student to one island");
                    if(!client.isCharacterPlayed())
                        System.out.println("3) Use a character");
                    actionNum = inputParser.intParser();
                    if(actionNum==1) {
                        actionChosen = PossibleAction.MOVESTUDENTTOHALL;
                        check = true;
                    }
                    else if(actionNum==2){
                        actionChosen = PossibleAction.MOVESTUDENTT0ISLAND;
                        check = true;

                    }
                    else if(actionNum==3 && !client.isCharacterPlayed()){
                        actionChosen = PossibleAction.USECHARACTER;
                        check = true;

                    }
                    else {
                        System.out.println("Selection not valid. Try again");
                        System.out.println("");
                    }
                }

                else if(playerState == PlayerState.MOTHERNATUREPHASE){
                    System.out.println("Possible actions:");
                    System.out.println("1) Move Mother Nature");
                    System.out.println("2) Use a character");
                    actionNum = inputParser.intParser();
                    if(actionNum==1) {
                        actionChosen = PossibleAction.MOVEMOTHERNATURE;
                        check = true;
                    }
                    else if(actionNum==2){
                        actionChosen = PossibleAction.USECHARACTER;
                        check = true;
                    }
                    else {
                        System.out.println("Selection not valid. Try again");
                        System.out.println("");
                    }
                }

                else if(playerState == PlayerState.CLOUDPHASE){
                    System.out.println("Possible actions:");
                    System.out.println("1) Choose a cloud");
                    System.out.println("2) Use a character");
                    actionNum = inputParser.intParser();
                if(actionNum==1) {
                    actionChosen = PossibleAction.CHOOSECLOUD;
                    check = true;
                }
                else if(actionNum==2){
                    actionChosen = PossibleAction.USECHARACTER;
                    check = true;
                }
                else {
                    System.out.println("Selection not valid. Try again");
                    System.out.println("");
                }

            }
        }
            return actionChosen;
        }

    @Override
    public void moveStudentToHall() {
        ArrayList<PlayerInterface> playerP = new ArrayList<>();
        playerP.add(player);
        graphic.printPlances(playerP);
        Student studentChosen = this.chooseStudentToMove();
        MoveStudentToHallMessage message = new MoveStudentToHallMessage(studentChosen);
        this.client.sendMessage(message);
    }

    public Student chooseStudentToMove() {
        ArrayList<Student> entrance = this.player.getPlance().getEntrance();
        Student studentChosen=inputParser.studentParser();
        while(!entrance.contains(studentChosen)) {
            System.out.println("Student not available. Please try again");
            studentChosen=inputParser.studentParser();
        }
        return studentChosen;
    }

    @Override
    public void moveStudentToIsland() {
        graphic.printArchipelago(board.getArchipelago());
        ArrayList<PlayerInterface> playerP = new ArrayList<>();
        playerP.add(player);
        graphic.printPlances(playerP);
        int numOfIslands = this.board.getArchipelago().getNumOfIslands();
        Student studentChosen = this.chooseStudentToMove();
        int islandID = inputParser.IslandParser(numOfIslands);
        MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(islandID, studentChosen);
        client.sendMessage(message);

    }


    @Override
    public void moveMotherNature() {
        int num = 0;
        if(character4played)
            num=2;
        Assistant assistant = this.player.getLastassistantplayed();
        graphic.printArchipelago(this.board.getArchipelago());
        System.out.println("Choose the number of movements for Mother Nature");
        int numberOfMovements=inputParser.intParser();
        boolean check = false;
        while(!check){
            if(numberOfMovements > 0 && numberOfMovements<=assistant.getMovement() + num) {
                check=true;
            }
            else {
                System.out.println("Selection not valid. Try again");
                numberOfMovements = inputParser.intParser();
            }
        }
        MoveMotherNatureMessage message = new MoveMotherNatureMessage(numberOfMovements);
        this.client.sendMessage(message);
    }

    @Override
    public void chooseCloud() {
        ArrayList<Cloud> clouds = this.board.getClouds();
        this.graphic.printClouds(clouds);
        System.out.println("Choose one cloud between this available by typing his number associated");
        int cloudChosen = inputParser.intParser();
        boolean check = false;
        while(!check){
            if(cloudChosen > 0 && cloudChosen<= clouds.size()){
                check=true;
            }
            else{
                System.out.println("Selection not valid. Try again");
                cloudChosen = inputParser.intParser();
            }
        }
        this.client.setCharacterPlayed(false);
        ChooseCloudMessage message = new ChooseCloudMessage(cloudChosen);
        this.client.sendMessage(message);
    }

    @Override
    public void useCharacter(){
        ArrayList<Characters> availableCharacter = this.board.getCharacters();
        int numOfCoins = this.player.getCoins();
        System.out.println("Choose one character between this available by typing his number associated");
        Characters character = null;
        boolean check = false;
        graphic.printCharacters(availableCharacter, this.effectHandler);
        int characterChosen = inputParser.intParser();
        while(!check){
            if(characterChosen>=1 && characterChosen<=3 && availableCharacter.get(characterChosen-1).getCost()<= numOfCoins){
                character = availableCharacter.get(characterChosen-1);
                check=true;
            } else {
                System.out.println("Selection not valid. Try again");
                characterChosen = inputParser.intParser();
            }
        }
        this.client.setCharacterPlayed(true);
        ChooseCharacterMessage message = new ChooseCharacterMessage(character);
        this.client.sendMessage(message);


        switch(character.getTypeOfInputCharacter()){
            case EFFECT1INPUT ->
                this.characterInput.monkInput(this.client,this.effectHandler.getEffect1students(),this.board.getArchipelago().getNumOfIslands());
            case ISLAND ->
                this.characterInput.islandInput(this.client, this.board.getArchipelago().getNumOfIslands());
            case INT ->
                this.setCharacter4played(true);
            case EFFECT7INPUT ->
                this.characterInput.jesterInput(this.client,this.effectHandler.getEffect7students(),this.player.getPlance().getEntrance());
            case STUDENT ->
                this.characterInput.genericStudentInput(this.client);
            case EFFECT10INPUT ->
                this.characterInput.minstrelInput(this.client,this.player.getPlance().getEntrance(), this.player.getPlance().getHall());
            case EFFECT11INPUT ->
                this.characterInput.spoiledprincess(this.client, this.effectHandler.getEffect11students());
        }
    }

    @Override
    public void setPlayers(ArrayList<Player> players) {
        this.players.clear();
        this.players.addAll(players);
        for(PlayerInterface play : players)
            if(play.getNickname().equals(client.getNickname()))
                player=play;
    }

    @Override
    public void setBoard(Board board) {
        this.board=board;
    }

    @Override
    public void print() {

    }


    @Override
    public void printStartGame() {
        System.out.println("Game is Starting");
        graphic.printArchipelago(board.getArchipelago());
        graphic.printPlances(players);
    }

    @Override
    public void winner(String nick) {
        if(nick.equals(this.nickname))
            System.out.println("YOU ARE THE WINNER");
        else
            System.out.println(nick + " win the game. Try again, you'll be luckier");
    }

    @Override
    public void printAssistantChosen(String nick, Assistant assistant) {
        if(this.player.getPlayerState().equals(PlayerState.WAITING))
            System.out.println(nick + " is playing " + assistant);

    }

    @Override
    public void printTurn(String nick) {
        if(this.player.getPlayerState().equals(PlayerState.WAITING))
            System.out.println(nick + " is playing");
        else
            System.out.println("Is your turn");
    }

    @Override
    public void printCharacterChosen(String nick, Characters character) {
        if(this.player.getPlayerState().equals(PlayerState.WAITING))
            System.out.println(nick + " is playing " + character.getEffect().getName());
    }

    @Override
    public void printCloudChosen(String nick, int cloudID) {
        if(this.player.getPlayerState().equals(PlayerState.WAITING))
            System.out.println(nick + " has chosen cloud number " + cloudID);

    }

    @Override
    public void printStudentToHall(String nick, Student student) {
        if(this.player.getPlayerState().equals(PlayerState.WAITING))
            System.out.println(nick + " moved " + student + " to hall");
    }

    @Override
    public void printStudentToIsland(String nick, Student student, int islandID) {
        if(this.player.getPlayerState().equals(PlayerState.WAITING))
            System.out.println(nick + " moved " + student + " to island number " + islandID);
    }

    @Override
    public void printMotherNatureMovement(String nick, int islandID) {
        if(this.player.getPlayerState().equals(PlayerState.WAITING))
            System.out.println(nick + " moved mother nature to island number  " + islandID);
    }


    public void setCharacter4played(boolean character4played) {
        this.character4played = character4played;
    }
}

