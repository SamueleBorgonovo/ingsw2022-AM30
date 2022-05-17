package it.polimi.ingsw.client.View.cli;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.View.cli.Graphical.Graphic;
import it.polimi.ingsw.client.View.View;
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
import java.util.HashMap;
import java.util.Scanner;

public class CLI extends View {

    private static final int MIN_PORT = 1024;
    private static final int MAX_PORT = 65535;

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

        if(choice.equalsIgnoreCase("s"))
            return GameMode.SIMPLEMODE;
        else
            return GameMode.EXPERTMODE;
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
        System.out.println("Choose your wizard by typing his color ( g | y | p | b )");
        this.graphic.printWizards(avaiableWizards);
        String choice = stdin.nextLine().toLowerCase();

        while (!checkwizard) {
            switch (choice) {
                case ("g") -> {
                    if(avaiableWizards.contains(Wizard.WIZARD_GREEN)) {
                        wizardChosen = Wizard.WIZARD_GREEN;
                        checkwizard = true;
                    }
                    else {
                        System.out.println("Typing error or invalid wizard: Try again");
                        choice = stdin.nextLine().toLowerCase();
                    }
                }
                case ("y") -> {
                    if(avaiableWizards.contains(Wizard.WIZARD_YELLOW)) {
                        wizardChosen = Wizard.WIZARD_YELLOW;
                        checkwizard = true;
                    }
                    else{
                        System.out.println("Typing error or invalid wizard: Try again");
                        choice = stdin.nextLine().toLowerCase();
                    }
                }
                case ("p") -> {
                    if(avaiableWizards.contains(Wizard.WIZARD_PINK)) {
                        wizardChosen = Wizard.WIZARD_PINK;
                        checkwizard = true;
                    }
                    else {
                        System.out.println("Typing error or invalid wizard: Try again");
                        choice = stdin.nextLine().toLowerCase();
                    }
                }
                case ("b") -> {
                    if(avaiableWizards.contains(Wizard.WIZARD_BLUE)) {
                        wizardChosen = Wizard.WIZARD_BLUE;
                        checkwizard = true;
                    }
                    else {
                        System.out.println("Typing error or invalid wizard: Try again");
                        choice = stdin.nextLine().toLowerCase();
                    }
                }
                default -> {
                    System.out.println("Typing error or invalid wizard: Try again");
                    choice = stdin.nextLine().toLowerCase();
                }

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
        System.out.println("Choose one assistant between this available by typing his number associated");
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
            if(playerState == PlayerState.STUDENTPHASE){
                    System.out.println("Possible actions:" +
                            "1) Move one student to the hall" +
                            "2) Move one student to one island");
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
                        actionNum= Integer.parseInt(stdin.nextLine());
                    }
                }

                else if(playerState == PlayerState.MOTHERNATUREPHASE){
                    System.out.println("Possible actions:" +
                            "1) Move Mother Nature" +
                            "2) Use a character") ;
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
                        actionNum= Integer.parseInt(stdin.nextLine());
                    }
                }

                else if(playerState == PlayerState.CLOUDPHASE){
                    System.out.println("Possible actions:" +
                            "1) Choose a cloud" +
                            "2) Use a character") ;
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
                    actionNum= Integer.parseInt(stdin.nextLine());
                }

            }
        }
            return actionChosen;
        }

    @Override
    public void moveStudentToHall() {
        graphic.printPlances(players);
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
        int numOfIslands = this.board.getArchipelago().getNumOfIslands();
        Student studentChosen = this.chooseStudentToMove();
        int islandID = inputParser.IslandParser(numOfIslands);
        MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(islandID, studentChosen);
        this.client.sendMessage(message);
    }


    @Override
    public void moveMotherNature() {
        Assistant assistant = this.player.getLastassistantplayed();
        Scanner stdin = new Scanner(System.in);
        System.out.println("Choose one assistant between this available by typing his number associated");
        String input=stdin.nextLine();
        int numberOfMovements=inputParser.intParser();
        boolean check = false;
        while(!check){
            if(numberOfMovements > 0 && numberOfMovements<=assistant.getMovement()) {
                check=true;
            }
            else {
                System.out.println("Selection not valid. Try again");
                numberOfMovements = Integer.parseInt(input);
            }
        }
        MoveMotherNatureMessage message = new MoveMotherNatureMessage(numberOfMovements);
        this.client.sendMessage(message);
    }

    @Override
    public void chooseCloud() {
        ArrayList<Cloud> clouds = this.board.getClouds();
        Scanner stdin = new Scanner(System.in);
        System.out.println("Choose one cloud between this available by typing his number associated");
        this.graphic.printClouds(clouds);
        String input=stdin.nextLine();
        int cloudChosen = inputParser.intParser();
        boolean check = false;
        while(!check){
            if(cloudChosen > 0 && cloudChosen<= clouds.size()){
                check=true;
            }
            else{
                System.out.println("Selection not valid. Try again");
                cloudChosen = Integer.parseInt(input);
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
        Scanner stdin = new Scanner(System.in);
        System.out.println("Choose one character between this available by typing his number associated");
        Characters character = null;
        boolean check = false;
        graphic.printCharacters(availableCharacter, this.effectHandler);
        String input=stdin.nextLine();
        int characterChosen = inputParser.intParser();
        while(!check)
        switch(characterChosen){
            case(1)->{
                if(availableCharacter.get(0).getCost()<= numOfCoins) {
                    character = availableCharacter.get(0);
                    check=true;
                }
            }
            case(2)->{
                if(availableCharacter.get(1).getCost()<= numOfCoins) {
                    character = availableCharacter.get(1);
                    check=true;
                }
            }
            case(3)->{
                if(availableCharacter.get(2).getCost()<= numOfCoins) {
                    character = availableCharacter.get(2);
                    check = true;
                }
            }

            default ->{
                System.out.println("Selection not valid. Try again");
                characterChosen = Integer.parseInt(input);
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
                this.characterInput.intInput(this.client);
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



}

