package it.polimi.ingsw.model.game;

import it.polimi.ingsw.exceptions.InvalidTurnExceptions;
import it.polimi.ingsw.exceptions.WrongAssistantException;
import it.polimi.ingsw.exceptions.WrongStudentException;
import it.polimi.ingsw.model.player.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    Game game2players = new Game(GameMode.SIMPLEMODE,2);
    Game game3players = new Game(GameMode.SIMPLEMODE,3);
    Player player1 = new Player("Daniele", Wizard.WIZARDYELLOW);
    Player player2 = new Player("Giuseppe", Wizard.WIZARDBLUE);
    Player player3 = new Player("Samuele", Wizard.WIZARDGREEN);

    @Test
    void getState() {
        // Test 2 Players Game
        game2players.addPlayer(player1);
        assertEquals(GameState.WAITINGFORPLAYERS,game2players.getState());
        game2players.addPlayer(player2);
        assertEquals(GameState.PLAYING,game2players.getState());
        game3players.addPlayer(player1);

        // Test 3 Players Game
        assertEquals(GameState.WAITINGFORPLAYERS,game3players.getState());
        game3players.addPlayer(player2);
        assertEquals(GameState.WAITINGFORPLAYERS,game3players.getState());
        game3players.addPlayer(player3);
        assertEquals(GameState.PLAYING,game3players.getState());
    }

    @Test
    void addPlayer() {
        // Test 2 Players Game
        assertEquals(0,game2players.getListOfPlayers().size());
        game2players.addPlayer(player1);
        assertTrue(game2players.getListOfPlayers().contains(player1));
        assertEquals(1,game2players.getListOfPlayers().size());
        assertEquals(Tower.WHITE,player1.getPlance().getTower());
        assertEquals(8,player1.getPlance().getNumOfTowers());
        assertEquals(7,player1.getPlance().getEntrance().size());
        game2players.addPlayer(player2);
        assertTrue(game2players.getListOfPlayers().contains(player2));
        assertEquals(2,game2players.getListOfPlayers().size());
        assertEquals(Tower.BLACK,player2.getPlance().getTower());
        assertEquals(8,player2.getPlance().getNumOfTowers());
        assertEquals(7,player2.getPlance().getEntrance().size());

        // Test 3 Players Game
        assertEquals(0,game3players.getListOfPlayers().size());
        game3players.addPlayer(player1);
        assertTrue(game3players.getListOfPlayers().contains(player1));
        assertEquals(1,game3players.getListOfPlayers().size());
        assertEquals(Tower.WHITE,player1.getPlance().getTower());
        assertEquals(6,player1.getPlance().getNumOfTowers());
        assertEquals(9,player1.getPlance().getEntrance().size());
        game3players.addPlayer(player2);
        assertTrue(game3players.getListOfPlayers().contains(player2));
        assertEquals(2,game3players.getListOfPlayers().size());
        assertEquals(Tower.BLACK,player2.getPlance().getTower());
        assertEquals(6,player2.getPlance().getNumOfTowers());
        assertEquals(9,player2.getPlance().getEntrance().size());
        game3players.addPlayer(player3);
        assertTrue(game3players.getListOfPlayers().contains(player3));
        assertEquals(3,game3players.getListOfPlayers().size());
        assertEquals(Tower.GREY,player3.getPlance().getTower());
        assertEquals(6,player3.getPlance().getNumOfTowers());
        assertEquals(9,player3.getPlance().getEntrance().size());
    }

    @Test
    void getBoard() {
    }

    @Test
    void getNumOfPlayers() {
        assertEquals(2,game2players.getNumOfPlayers());
        assertEquals(3,game3players.getNumOfPlayers());
    }

    @Test
    void getPlayer() {
    }

    @Test
    void winner() {
    }

    @Test
    void verifyProfessorControl() {
        game2players.addPlayer(player1);
        game2players.addPlayer(player2);
        player1.getPlance().addStudentEntrance(Student.RED);
        player1.getPlance().addStudentEntrance(Student.RED);
        player2.getPlance().addStudentEntrance(Student.RED);
        player1.getPlance().addStudentHall(Student.RED);
        player1.getPlance().addStudentHall(Student.RED);
        player2.getPlance().addStudentHall(Student.RED);
        game2players.verifyProfessorControl();
        assertEquals(Professor.RED_DRAGON, player1.getPlance().getProfessors().get(0));
    }

    @Test
    void startTurn() {
    }

    @Test
    void endTurn() {
    }

    @Test
    void moveStudentToEntrance() {
    }

    @Test
    void moveStudentToHall() throws InvalidTurnExceptions, WrongStudentException {
        game2players.addPlayer(player1);
        game2players.addPlayer(player2);
        player1.setPlayerState(PlayerState.STUDENTPHASE);
        player1.getPlance().addStudentEntrance(Student.RED);
        player1.getPlance().addStudentEntrance(Student.BLUE);
        player1.getPlance().addStudentEntrance(Student.YELLOW);
       // System.out.println(player1.getPlance().getEntrance());
        try {
            game2players.moveStudentToHall(1, Student.RED);
            game2players.moveStudentToHall(1, Student.BLUE);
            game2players.moveStudentToHall(1, Student.YELLOW);
        }
        catch (WrongStudentException e) {System.out.println("Errore");};
       // System.out.println(player1.getPlance().getEntrance());
        assertEquals(player1.getPlance().getNumberOfStudentHall(Student.RED),1);
        assertEquals(player1.getPlance().getNumberOfStudentHall(Student.BLUE),1);
        assertEquals(player1.getPlance().getNumberOfStudentHall(Student.YELLOW),1);
       // game2players.moveStudentToHall(1, Student.RED);
    }

    @Test
    void moveStudentToIsland() throws InvalidTurnExceptions, WrongStudentException {
        ArrayList<Student> students = new ArrayList<>();
        students.add(game2players.getBoard().getArchipelago().getSingleIsland(2).getStudents().get(0));
        students.add(Student.RED);
        students.add(Student.BLUE);
        students.add(Student.YELLOW);
        game2players.addPlayer(player1);
        game2players.addPlayer(player2);
        player1.setPlayerState(PlayerState.STUDENTPHASE);
        player1.getPlance().addStudentEntrance(Student.RED);
        player1.getPlance().addStudentEntrance(Student.BLUE);
        player1.getPlance().addStudentEntrance(Student.YELLOW);
        game2players.moveStudentToIsland(1,2,Student.RED);
        game2players.moveStudentToIsland(1,2,Student.BLUE);
        game2players.moveStudentToIsland(1,2,Student.YELLOW);
        assertEquals(game2players.getBoard().getArchipelago().getSingleIsland(2).getStudents(),students);
    }

    @Test
    void useAssistant() throws WrongAssistantException, InvalidTurnExceptions {
      /*  ArrayList<Assistant> wanted1 = new ArrayList<>(Arrays.asList(Assistant.values()));
        ArrayList<Assistant> wanted2 = new ArrayList<>(Arrays.asList(Assistant.values()));
        wanted1.remove(Assistant.CAT);
        wanted2.remove(Assistant.DOG);
        game2players.addPlayer(player1);
        game2players.addPlayer(player2);
        player1.setPlayerState(PlayerState.ASSISTANTPHASE);
        game2players.useAssistant(1,Assistant.CAT);
        player2.setPlayerState(PlayerState.ASSISTANTPHASE);
        game2players.useAssistant(2,Assistant.DOG);
        assertEquals(wanted1, player1.getAssistantCards());
        assertEquals(wanted2, player2.getAssistantCards()); */
    }

    @Test
    void moveMotherNature() {
    }

    @Test
    void useCharacter() {
    }

    @Test
    void getVerifyType() {
    }

    @Test
    void verifyMergeableIsland() {
    }

    @Test
    void chooseStudent() {
    }

    @Test
    void chooseIsland() {
    }

    @Test
    void chooseStudentFromPlance() {
    }

    @Test
    void getListOfPlayers() {
    }

    @Test
    void verifyIslandInfluence() {
    }


    @Test
    void verifyPlayerOrder() {
        game3players.addPlayer(player1);
        game3players.addPlayer(player2);
        game3players.addPlayer(player3);

        //Set last played assistant
        player1.removeAssistant(Assistant.SNAKE);
        player2.removeAssistant(Assistant.TURTLE);
        player3.removeAssistant(Assistant.CAT);

        ArrayList<Player> playerorder = new ArrayList<>();
        playerorder.addAll(game3players.verifyPlayerOrder());

        int var=1;
        if(playerorder.get(0).getPlayerID() != player3.getPlayerID())
            var=-1;
        if(playerorder.get(1).getPlayerID() != player1.getPlayerID())
            var=-2;
        if(playerorder.get(2).getPlayerID() != player2.getPlayerID())
            var=-3;

        assertEquals(1,var);
    }

    @Test
    void startGame() {
    }
}