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
        randomeffectsstudents = game2players.getEffectHandler().getEffect1students().size() + game2players.getEffectHandler().getEffect11students().size() + game2players.getEffectHandler().getEffect7students().size();
        assertEquals(100 - randomeffectsstudents, game2players.getBoard().getNumOfStudentsBag());
        students = game2players.getBoard().getAndRemoveRandomBagStudent(5);
        assertEquals(95 - randomeffectsstudents, game2players.getBoard().getNumOfStudentsBag());
        game2players.getBoard().addStudentBag(students);
        assertEquals(100 - randomeffectsstudents, game2players.getBoard().getNumOfStudentsBag());

        // Test 3 Players Game
        randomeffectsstudents = game3players.getEffectHandler().getEffect1students().size() + game3players.getEffectHandler().getEffect11students().size() + game3players.getEffectHandler().getEffect7students().size();
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
    @Test
    void addCoinstoReserve() throws OutOfCoinsException {
        //also test removeeCoinFromReserve
        assertEquals(17,game3players.getBoard().getCoinReserve());
        game3players.getBoard().addCoinstoReserve(2);
        assertEquals(19,game3players.getBoard().getCoinReserve());
        game3players.getBoard().removeCoinsFromReserve(3);
        assertEquals(16,game3players.getBoard().getCoinReserve());
    }


    @Test
    void getClouds() {
        ArrayList<Cloud> clouds2Player = game2players.getBoard().getClouds();
        ArrayList<Cloud> clouds3Player = game3players.getBoard().getClouds();
        assertEquals(clouds2Player,game2players.getBoard().getClouds());
        assertEquals(clouds3Player,game3players.getBoard().getClouds());
    }

    @Test
    void getCharacters() {
        ArrayList<Character> characters = game3players.getBoard().getCharacters();
        assertEquals(characters,game3players.getBoard().getCharacters());
    }

    @Test
    void addStudentBag(){
        //also tests get and remove random bag student
        ArrayList<Student> students = new ArrayList<>();
        int num= game2players.getBoard().getNumOfStudentsBag();
        game2players.getBoard().getAndRemoveRandomBagStudent(3);
        students.add(Student.BLUE);
        students.add(Student.RED);
        game2players.getBoard().addStudentBag(students);
        assertEquals(num-1, game2players.getBoard().getNumOfStudentsBag());
    }

    @Test
    void getArchipelago() {
        Archipelago archipelago=new Archipelago();
        archipelago=game2players.getBoard().getArchipelago();
        assertEquals(archipelago,game2players.getBoard().getArchipelago());
    }
}