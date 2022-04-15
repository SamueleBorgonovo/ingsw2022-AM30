package it.polimi.ingsw.model.board;

import it.polimi.ingsw.exceptions.OutOfCoinsException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Wizard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    Game game2players = new Game(GameMode.EXPERTMODE,2);
    Game game3players = new Game(GameMode.EXPERTMODE,3);
    Player player1 = new Player("Daniele", Wizard.WIZARDYELLOW);
    Player player2 = new Player("Giuseppe", Wizard.WIZARDBLUE);
    Player player3 = new Player("Samuele", Wizard.WIZARDGREEN);

    @BeforeEach
    void init() {
        game2players.addPlayer(player1);
        game2players.addPlayer(player2);
        game3players.addPlayer(player1);
        game3players.addPlayer(player2);
        game3players.addPlayer(player3);
    }

    @Test
    void getNumOfStudentsBag() {
        // Also test getAndRemoveRandomBagStudent

        ArrayList<Student> students;

        // Test 2 Players Game
        assertEquals(100, game2players.getBoard().getNumOfStudentsBag());
        students = game2players.getBoard().getAndRemoveRandomBagStudent(5);
        assertEquals(95, game2players.getBoard().getNumOfStudentsBag());
        game2players.getBoard().addStudentBag(students);
        assertEquals(100, game2players.getBoard().getNumOfStudentsBag());

        // Test 3 Players Game
        assertEquals(81, game3players.getBoard().getNumOfStudentsBag());
        students = game3players.getBoard().getAndRemoveRandomBagStudent(5);
        assertEquals(76, game3players.getBoard().getNumOfStudentsBag());
        game3players.getBoard().addStudentBag(students);
        assertEquals(81, game3players.getBoard().getNumOfStudentsBag());
    }

    @Test
    void getCoinReserve() throws OutOfCoinsException {
        // Also tests addCoinToReserve and removeCoinsFromReserve

        // Test 2 Players Game
        assertEquals(20,game2players.getBoard().getCoinReserve());
        game2players.getBoard().removeCoinsFromReserve(3);
        assertEquals(17, game2players.getBoard().getCoinReserve());
        game2players.getBoard().addCoinstoReserve(1);
        assertEquals(18, game2players.getBoard().getCoinReserve());

        // Test 3 Players Game
        assertEquals(20,game3players.getBoard().getCoinReserve());
        game3players.getBoard().removeCoinsFromReserve(4);
        assertEquals(16, game3players.getBoard().getCoinReserve());
        game3players.getBoard().addCoinstoReserve(3);
        assertEquals(19, game3players.getBoard().getCoinReserve());
    }

    /*@Test
    void getClouds() {
        ArrayList<Cloud> cloudsExpected = new ArrayList<>();
        cloudsExpected.add(cloud1);
        cloudsExpected.add(cloud2);
        clouds= board.getClouds();
        assertTrue(cloudsExpected.equals(clouds));
    }

    @Test
    void getCharacters() {
        assertTrue(characters.equals(board.getCharacters()));

    }

    @Test
    void getArchipelago() {
        assertTrue(archipelago.equals(board.getArchipelago()));
    }*/
}