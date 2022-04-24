package it.polimi.ingsw.model.board;

import it.polimi.ingsw.exceptions.OutOfCoinsException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Wizard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    Game game2players = new Game(GameMode.EXPERTMODE,2);
    Game game3players = new Game(GameMode.EXPERTMODE,3);

    @BeforeEach
    void init() {
        game2players.addPlayer("Daniele", Wizard.WIZARDYELLOW);
        game2players.addPlayer("Giuseppe", Wizard.WIZARDBLUE);
        game3players.addPlayer("Daniele", Wizard.WIZARDYELLOW);
        game3players.addPlayer("Giuseppe", Wizard.WIZARDBLUE);
        game3players.addPlayer("Samuele", Wizard.WIZARDGREEN);
    }

    @Test
    void getNumOfStudentsBag() {
        // Also test getAndRemoveRandomBagStudent

        ArrayList<Student> students;
        int randomeffectsstudents;

        // Test 2 Players Game
        randomeffectsstudents = game2players.getEffectHandler().getEffect1students().size() + game2players.getEffectHandler().getEffect11students().size();
        assertEquals(100 - randomeffectsstudents, game2players.getBoard().getNumOfStudentsBag());
        students = game2players.getBoard().getAndRemoveRandomBagStudent(5);
        assertEquals(95 - randomeffectsstudents, game2players.getBoard().getNumOfStudentsBag());
        game2players.getBoard().addStudentBag(students);
        assertEquals(100 - randomeffectsstudents, game2players.getBoard().getNumOfStudentsBag());

        // Test 3 Players Game
        randomeffectsstudents = game3players.getEffectHandler().getEffect1students().size() + game3players.getEffectHandler().getEffect11students().size();
        assertEquals(81 - randomeffectsstudents, game3players.getBoard().getNumOfStudentsBag());
        students = game3players.getBoard().getAndRemoveRandomBagStudent(5);
        assertEquals(76 - randomeffectsstudents, game3players.getBoard().getNumOfStudentsBag());
        game3players.getBoard().addStudentBag(students);
        assertEquals(81 - randomeffectsstudents, game3players.getBoard().getNumOfStudentsBag());
    }

    @Test
    void getCoinReserve() throws OutOfCoinsException {
        // Also tests addCoinToReserve and removeCoinsFromReserve

        // Test 2 Players Game
        assertEquals(18,game2players.getBoard().getCoinReserve());
        game2players.getBoard().removeCoinsFromReserve(3);
        assertEquals(15, game2players.getBoard().getCoinReserve());
        game2players.getBoard().addCoinstoReserve(1);
        assertEquals(16, game2players.getBoard().getCoinReserve());

        // Test 3 Players Game
        assertEquals(17,game3players.getBoard().getCoinReserve());
        game3players.getBoard().removeCoinsFromReserve(4);
        assertEquals(13, game3players.getBoard().getCoinReserve());
        game3players.getBoard().addCoinstoReserve(2);
        assertEquals(15, game3players.getBoard().getCoinReserve());
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