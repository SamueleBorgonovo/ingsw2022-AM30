package it.polimi.ingsw.client.View.cli;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.client.View.cli.Graphical.Graphic;
import it.polimi.ingsw.controller.virtualView.*;
import it.polimi.ingsw.messages.toServer.*;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.EffectHandler;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Wizard;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Assistant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CLI implements View {

    private static final int MIN_PORT = 1024;
    private static final int MAX_PORT = 49151;

    private final Graphic graphic = new Graphic();
    private Client client;
    private InputParser inputParser = new InputParser();
    private ArrayList<PlayerView> players = new ArrayList<>();
    private BoardView board;
    private CharacterInput characterInput = new CharacterInput();
    private EffectHandler effectHandler;
    private String nickname;
    private PlayerView player;
    private boolean isFirst = true;
    private boolean character4played = false;
    private CharacterView characterPlayed = new CharacterView(0, null, "");


    public void init() throws IOException, ClassNotFoundException, NumberFormatException {
        boolean check = false;
        this.graphic.printLogo();

        while (!check) {
            Scanner stdin = new Scanner(System.in);
            System.out.println("Please insert the ip-address");
            String ip = stdin.nextLine();
            System.out.println("Please insert port");
            int port = inputParser.intParser();
            while (port < MIN_PORT || port > MAX_PORT) {
                System.out.println("Port Number is not valid, please insert a new one");
                port = inputParser.intParser();
            }
            Client client = new Client(ip, port, this);
            check = client.setupConnection();
            if (check)
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
                    if (tryToReconnect()) {
                        client.sendMessage(new ChooseNicknameMessage(nickname, true));
                    } else {
                        client.sendMessage(new ChooseNicknameMessage(nickname, false));
                        client.gameSetup();
                    }
                } else {
                    client.gameSetup();
                }
            } else {
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
        String input = stdin.nextLine().toLowerCase();

        boolean check = false;
        while (!check) {
            if (input.equals("y") || input.equals("n"))
                check = true;
            else {
                System.out.println("Not valid. Try again");
                input = stdin.nextLine().toLowerCase();
            }
        }
        return input.equals("y");
    }


    public void chooseSettings(){
        GameMode gamemode;
        Scanner stdin = new Scanner(System.in);
        System.out.println("Press s for SimpleMode or e for ExpertMode  s | e");
        String choice = stdin.nextLine();
        boolean pass = false;
        while (!pass) {
            if (choice.equalsIgnoreCase("s") || choice.equalsIgnoreCase("e")) {
                pass = true;
                ;
            } else {
                System.out.println("Selection not valid. Please try again");
                choice = stdin.nextLine();
            }
        }

        if (choice.equalsIgnoreCase("s")) {
            client.setGamemode(GameMode.SIMPLEMODE);
            gamemode=GameMode.SIMPLEMODE;
        } else {
            client.setGamemode(GameMode.EXPERTMODE);
            gamemode=GameMode.EXPERTMODE;
        }

        System.out.println("Choose the number of the players ( 2 | 3 )");
        int choice1 = inputParser.intParser();
        boolean pass1 = false;
        while (!pass1) {
            if (choice1 == 2 || choice1 == 3)
                pass1 = true;
            else {
                System.out.println("Selection not valid. Please try again");
                choice1 = inputParser.intParser();
            }
        }
        client.setNumofPlayers(choice1);

        CreatePlayerInGameMessage message = new CreatePlayerInGameMessage(nickname,gamemode,choice1);
        client.sendMessage(message);
    }
    @Override
    public void chooseWizard(ArrayList<Wizard> avaiableWizards) {
        Wizard wizardChosen = null;
        boolean checkwizard = false;
        System.out.println("Choose one Wizard between this available by typing his number associated");
        this.graphic.printWizards(avaiableWizards);
        int wizardInt = inputParser.intParser();
        while (!checkwizard) {
            if (wizardInt >= 1 && wizardInt <= avaiableWizards.size()) {
                wizardChosen = avaiableWizards.get(wizardInt - 1);
                checkwizard = true;
            } else {
                System.out.println("Selection not valid. Try again");
                wizardInt = inputParser.intParser();
            }
        }
        this.player = new PlayerView(this.nickname, wizardChosen, null, null, 1, null);
        client.setWizard(wizardChosen);
        ChooseWizardMessage message = new ChooseWizardMessage(wizardChosen);
        client.sendMessage(message);
    }

    @Override
    public void chooseAssistant() {
        ArrayList<Assistant> avaiableAssistant = this.player.getAssistantCards();
        Scanner stdin = new Scanner(System.in);
        Assistant assistantChosen = null;
        System.out.println("Choose one Assistant between this available by typing his number associated");
        this.graphic.printAssistants(avaiableAssistant, this.client.getWizard());
        int assistantInt = inputParser.intParser();
        boolean check = false;
        while (!check) {
            for (Assistant assistantCheck : avaiableAssistant)
                if (assistantInt == avaiableAssistant.indexOf(assistantCheck) + 1) {
                    assistantChosen = assistantCheck;
                    check = true;
                    break;
                }
            if (!check) {
                System.out.println("Selection not valid. Try again");
                assistantInt = stdin.nextInt();
            }
        }
        ChooseAssistantMessage message = new ChooseAssistantMessage(assistantChosen);
        client.sendMessage(message);
    }

    @Override
    public PossibleAction chooseNextAction(PlayerState playerState) {
        boolean check = false;
        PossibleAction actionChosen = null;
        int actionNum = 0;
        while (!check) {
            if (playerState == PlayerState.STUDENTPHASE && client.getGamemode() == GameMode.SIMPLEMODE) {
                System.out.println("Choose your next action by typing the action's number");
                System.out.println("Possible actions:");
                System.out.println("1) Move one student to the hall");
                System.out.println("2) Move one student to one island");
                actionNum = inputParser.intParser();
                if (actionNum == 1 && moveToHallPossible()) {
                    actionChosen = PossibleAction.MOVESTUDENTTOHALL;
                    check = true;
                } else if (actionNum == 2) {
                    actionChosen = PossibleAction.MOVESTUDENTT0ISLAND;
                    check = true;
                } else {
                    System.out.println("Selection not valid. Try again");
                    System.out.println("");
                }
            } else if (playerState == PlayerState.STUDENTPHASE && client.getGamemode() == GameMode.EXPERTMODE) {
                System.out.println("Choose your next action by typing the action's number");
                System.out.println("Possible actions:");
                System.out.println("1) Move one student to the hall");
                System.out.println("2) Move one student to one island");
                if (!client.isCharacterPlayed())
                    System.out.println("3) Use a character");
                actionNum = inputParser.intParser();
                if (actionNum == 1 && moveToHallPossible()) {
                    actionChosen = PossibleAction.MOVESTUDENTTOHALL;
                    check = true;
                } else if (actionNum == 2) {
                    actionChosen = PossibleAction.MOVESTUDENTT0ISLAND;
                    check = true;

                } else if (actionNum == 3 && !client.isCharacterPlayed()) {
                    actionChosen = PossibleAction.USECHARACTER;
                    check = true;

                } else {
                    System.out.println("Selection not valid. Try again");
                    System.out.println("");
                }
            } else if (playerState == PlayerState.MOTHERNATUREPHASE) {
                if(client.isCharacterPlayed()) {
                    actionChosen = PossibleAction.MOVEMOTHERNATURE;
                    check=true;
                }else {
                    System.out.println("Choose your next action by typing the action's number");
                    System.out.println("Possible actions:");
                    System.out.println("1) Move Mother Nature");
                    System.out.println("2) Use a character");
                    actionNum = inputParser.intParser();
                    if (actionNum == 1) {
                        actionChosen = PossibleAction.MOVEMOTHERNATURE;
                        check = true;
                    } else if (actionNum == 2) {
                        actionChosen = PossibleAction.USECHARACTER;
                        check = true;
                    } else {
                        System.out.println("Selection not valid. Try again");
                        System.out.println("");
                    }
                }
            } else if (playerState == PlayerState.CLOUDPHASE) {
                if(client.isCharacterPlayed()) {
                    actionChosen = PossibleAction.CHOOSECLOUD;
                    check=true;
                } else {
                    System.out.println("Choose your next action by typing the action's number");
                    System.out.println("Possible actions:");
                    System.out.println("1) Choose a cloud");
                    System.out.println("2) Use a character");
                    actionNum = inputParser.intParser();
                    if (actionNum == 1) {
                        actionChosen = PossibleAction.CHOOSECLOUD;
                        check = true;
                    } else if (actionNum == 2) {
                        actionChosen = PossibleAction.USECHARACTER;
                        check = true;
                    } else {
                        System.out.println("Selection not valid. Try again");
                        System.out.println("");
                    }
                }

            }
            System.out.println(actionChosen);
        }
        return actionChosen;
    }

    public boolean moveToHallPossible() {
        boolean check = false;
        for (Student student : player.getPlance().getEntrance())
            if (player.getPlance().getHall().get(student) < 10)
                check = true;
        return check;
    }

    @Override
    public void moveStudentToHall() {
        ArrayList<PlayerView> playerList = new ArrayList<>();
        playerList.add(player);
        graphic.printPlances(playerList);
        Student studentChosen = this.chooseStudentToMove();
        MoveStudentToHallMessage message = new MoveStudentToHallMessage(studentChosen);
        this.client.sendMessage(message);
    }

    public Student chooseStudentToMove() {
        ArrayList<Student> entrance = this.player.getPlance().getEntrance();
        Student studentChosen = inputParser.studentParser();
        while (!entrance.contains(studentChosen)) {
            System.out.println("Student not available or max of students. Please try again");
            studentChosen = inputParser.studentParser();
        }
        return studentChosen;
    }

    @Override
    public void moveStudentToIsland() {
        graphic.printArchipelago(this.board.getIslandViews(), this.board.getMotherNature());
        ArrayList<PlayerView> playerP = new ArrayList<>();
        playerP.add(player);
        graphic.printPlances(playerP);
        int numOfIslands = this.board.getIslandViews().size();
        Student studentChosen = this.chooseStudentToMove();
        int islandID = inputParser.IslandParser(this.board.getIslandViews(),this.board.getMotherNature());
        MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(islandID, studentChosen);
        client.sendMessage(message);

    }


    @Override
    public void moveMotherNature() {
        int num = 0;
        if (character4played)
            num = 2;
        Assistant assistant = this.player.getLastassistantplayed();
        graphic.printArchipelago(this.board.getIslandViews(), this.board.getMotherNature());
        System.out.println("Choose the number of movements for Mother Nature");
        int numberOfMovements = inputParser.intParser();
        boolean check = false;
        while (!check) {
            if (numberOfMovements > 0 && numberOfMovements <= assistant.getMovement() + num) {
                check = true;
            } else {
                System.out.println("Selection not valid. Try again");
                numberOfMovements = inputParser.intParser();
            }
        }
        MoveMotherNatureMessage message = new MoveMotherNatureMessage(numberOfMovements);
        this.client.sendMessage(message);
        this.character4played = false;
    }

    @Override
    public void chooseCloud() {
        ArrayList<CloudView> clouds = this.board.getClouds();
        this.graphic.printClouds(clouds);
        System.out.println("Choose one cloud between this available by typing his number associated");
        int cloudChosen = inputParser.intParser();
        boolean check = false;
        while (!check) {
            if (cloudChosen > 0 && cloudChosen <= clouds.size()) {
                check = true;
            } else {
                System.out.println("Selection not valid. Try again");
                cloudChosen = inputParser.intParser();
            }
        }
        this.client.setCharacterPlayed(false);
        ChooseCloudMessage message = new ChooseCloudMessage(cloudChosen);
        this.client.sendMessage(message);
    }

    @Override
    public void useCharacter(PlayerState playerState) {
        ArrayList<CharacterView> availableCharacter = this.board.getCharacters();
        int numOfCoins = this.player.getCoins();
        int count = 0;
        boolean checkMinstrelpossible = true;
        for (CharacterView character : availableCharacter)
            if (numOfCoins >= character.getCost())
                count++;
        for (CharacterView character2 : availableCharacter)
            if (character2.getTypeOfInputCharacter() == TypeOfInputCharacter.EFFECT10INPUT)
                checkMinstrelpossible = this.checkMinstrel();
        if (count > 0 && checkMinstrelpossible) {
            System.out.println("Choose one character between this available by typing his number associated");
            CharacterView character = new CharacterView(0, null, "");
            boolean check = false;
            graphic.printCharacters(availableCharacter, this.effectHandler);
            int characterChosen = inputParser.intParser();
            while (!check) {
                if (characterChosen >= 1 && characterChosen <= 3 && availableCharacter.get(characterChosen - 1).getCost() <= numOfCoins) {
                    character = availableCharacter.get(characterChosen - 1);
                    check = true;
                } else {
                    System.out.println("Selection not valid. Try again");
                    characterChosen = inputParser.intParser();
                }

                if (character.getTypeOfInputCharacter() == TypeOfInputCharacter.EFFECT10INPUT) {
                    int counter = 0;
                    for (Student s : Student.values())
                        counter = counter + this.player.getPlance().getHall().get(s);
                    if (counter < 1) {
                        check = false;
                        System.out.println("You don't have enough students in the hall. Please repeat your choice");
                        characterChosen = inputParser.intParser();
                    }
                }

            }
            this.characterPlayed = character;
            this.client.setCharacterPlayed(true);
            ChooseCharacterMessage message = new ChooseCharacterMessage(characterPlayed);
            this.client.sendMessage(message);
            System.out.println(character.getName());

            if (character.getTypeOfInputCharacter() == TypeOfInputCharacter.INT)
                this.setCharacter4played(true);
        } else {
            System.out.println("You don't have enough coins  or you can't use some characters yet. Please change your move");
            client.nextMove(playerState);
        }

    }

    @Override
    public void inputStudentCharacter() {
        System.out.println(characterPlayed.getName());
        switch (this.characterPlayed.getTypeOfInputCharacter()) {
            case EFFECT1INPUT -> this.characterInput.studentFromCard(this.client, this.effectHandler.getEffect1students());
            case EFFECT7INPUT -> this.characterInput.jesterInput(this.client, this.effectHandler.getEffect7students(), this.player.getPlance().getEntrance());
            case STUDENT -> this.characterInput.genericStudentInput(this.client);
            case EFFECT10INPUT -> this.characterInput.minstrelInput(this.client, this.player.getPlance().getEntrance(), this.player.getPlance().getHall());
            case EFFECT11INPUT -> this.characterInput.studentFromCard(this.client, this.effectHandler.getEffect11students());
        }
        if (this.characterPlayed.getTypeOfInputCharacter() == TypeOfInputCharacter.EFFECT1INPUT)
            this.characterPlayed = null;
    }

    @Override
    public void inputIslandCharacter() {
        this.characterInput.islandInput(this.client, this.board.getIslandViews(),this.board.getMotherNature());
        this.characterPlayed = null;
    }

    @Override
    public void setPlayers(ArrayList<PlayerView> players) {
        this.players.clear();
        this.players.addAll(players);
        for (PlayerView play : players)
            if (play.getNickname().equals(client.getNickname()))
                player = play;
    }

    @Override
    public void setBoard(BoardView board) {
        this.board = board;
    }

    public void setEffectHandler(EffectHandler effectHandler) {
        this.effectHandler = effectHandler;
    }

    @Override
    public void print() {

    }


    @Override
    public void printStartGame() {
        System.out.println("Game is Starting");
        graphic.printArchipelago(board.getIslandViews(), board.getMotherNature());
        graphic.printPlances(players);
        if (client.getGamemode() == GameMode.EXPERTMODE)
            this.graphic.printCharacters(this.board.getCharacters(), this.effectHandler);
    }

    @Override
    public void winner(ArrayList<String> nicknamesWinner) {
        for (String nick : nicknamesWinner) {
            if (nick.equals(this.nickname))
                System.out.println("YOU ARE THE WINNER");
            else
                System.out.println(nick + " win the game. Try again, you'll be luckier");
        }
    }

    @Override
    public void printAssistantChosen(String nick, Assistant assistant) {
        if (!nick.equals(client.getNickname()))
            System.out.println(nick + " is playing " + assistant);

    }

    @Override
    public void printTurn(String nick) {
        if (!client.isMyTurn())
            System.out.println(nick + " is playing");
        else {
            System.out.println("Is your turn");
            graphic.printArchipelago(board.getIslandViews(), board.getMotherNature());
            graphic.printPlances(players);
        }
    }

    @Override
    public void printCharacterChosen(String nick, CharacterView character) {
        if (!client.isMyTurn())
            System.out.println(nick + " is playing " + character.getName());
    }

    @Override
    public void printCloudChosen(String nick, int cloudID) {
        if (!client.isMyTurn())
            System.out.println(nick + " has chosen cloud number " + cloudID);

    }

    @Override
    public void printStudentToHall(String nick, Student student) {
        if (!client.isMyTurn())
            System.out.println(nick + " moved " + student + " to hall");
    }

    @Override
    public void printStudentToIsland(String nick, Student student, int islandID) {
        if (!client.isMyTurn())
            System.out.println(nick + " moved " + student + " to island number " + islandID);
    }

    @Override
    public void printMotherNatureMovement(String nick, int islandID) {
        if (!client.isMyTurn())
            System.out.println(nick + " moved mother nature to island number  " + islandID);
    }


    public void setCharacter4played(boolean character4played) {
        this.character4played = character4played;
    }

    public void printPlayerDisconnection(String nick) {
        System.out.println(nick + " disconnected from game");
    }

    public void printPlayerConnection(String nick, boolean reconnect) {
        if (nick.equals(client.getNickname())) {
            if (reconnect) {
                client.setWizard(player.getWizard());
                System.out.println("Reconnecting to the game");
            }
        } else {
            if (reconnect)
                System.out.println(nick + " is reconnecting to the game");
            else System.out.println(nick + " is connecting to the game");
        }
    }

    public void printInvalidAssistant() {
        System.out.println("Assistant already chosen. Try again");
    }

    public void printInvalidCloud() {
        System.out.println("Cloud not available. Try again");
    }

    public void printInvalidIsland() {
        System.out.println("Island number not available. Try again");
    }

    public void printInvalidStudent() {
        System.out.println("Student not available. Try again");
    }

    public void printInvalidTurn() {
        System.out.println("Action not available. Is not your turn");
    }

    public void printInvalidStop() {
        System.out.println("Character GRANDMA doesn't have enough stop");
    }

    public void printInvalidWizard() {
        System.out.println("Wizard is not more available.");
    }

    public void printWinnerInstantly(ArrayList<String> nickname, int type) {
        if (nickname.contains(client.getNickname()))
            System.out.println("YOU WON");
        else {
            if (type == 1)
                System.out.println(nickname + " won because only 3 islands remained");
            if (type == 2)
                System.out.println(nickname + " won because built his last tower");
        }
    }

    public void printWinnerEndRound(ArrayList<String> nickname, int type) {
        if (nickname.contains(client.getNickname()))
            System.out.println("YOU WON");
        else {
            if (type == 1)
                System.out.println("Game ended because bag is empty. \n" + nickname + " won");
            if (type == 2)
                System.out.println("Game ended because all assistant cards has been used.\n" + nickname + " won");
        }
    }

    public void printWaitingForPlayers(boolean lobby) {
        if (lobby)
            System.out.println("Waiting for players to start the game");
        else
            System.out.println("Waiting for players to reconnection to the game. Timer to win started");
    }

    public void printGameEndedTimeout() {
        System.out.println("You won. Game ended because players did not reconnect");
    }

    public void printWinClose() {
        System.out.println("Game Ended. Disconnection from server");
    }

    public void printConnectionClosed(boolean timeout) {
        if (timeout)
            System.out.println("Connection with server closed. Time expired");
        else System.out.println("Connection with server closed");
    }

    public boolean checkMinstrel() {
        ArrayList<CharacterView> availableCharacter = this.board.getCharacters();
        int numOfCoins = this.player.getCoins();
        boolean check;
        int count = 0;

        for (CharacterView character : availableCharacter)
            if (numOfCoins >= character.getCost() && character.getTypeOfInputCharacter() != TypeOfInputCharacter.EFFECT10INPUT)
                count++;
        if (count > 0)
            check = true;
        else {
            int counter = 0;
            for (Student s : Student.values())
                counter = counter + this.player.getPlance().getHall().get(s);
            check = counter >= 1; //if counter>=1 check is true, else check is false
        }
        return check;
    }

}