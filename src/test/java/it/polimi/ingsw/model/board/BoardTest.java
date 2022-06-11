package it.polimi.ingsw.model.board;

import it.polimi.ingsw.controller.virtualView.BoardView;
import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.controller.virtualView.CloudView;
import it.polimi.ingsw.controller.virtualView.IslandView;
import it.polimi.ingsw.exceptions.OutOfCoinsException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {
    Game game2players = new Game(GameMode.EXPERTMODE,2);
    Game game3players = new Game(GameMode.EXPERTMODE,3);

    @BeforeEach
    void init() {
        game2players.addPlayer("Daniele");
        game2players.addPlayer("Giuseppe");
        game3players.addPlayer("Daniele");
        game3players.addPlayer("Giuseppe");
        game3players.addPlayer("Samuele");
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
    void getAndRemoveRandomBagStudent(){
        ArrayList<Student> students=new ArrayList<>();
        students=game2players.getBoard().getAndRemoveRandomBagStudent(5);
        assertEquals(5,students.size());

        game2players.getBoard().getAndRemoveRandomBagStudent(game2players.getBoard().getNumOfStudentsBag());
        assertEquals(0,game2players.getBoard().getNumOfStudentsBag());

        students.clear();
        students.add(Student.RED);
        students.add(Student.GREEN);
        students.add(Student.RED);
        game2players.getBoard().addStudentBag(students);
        assertEquals(3,game2players.getBoard().getNumOfStudentsBag());
        assertTrue(game2players.getBoard().getAndRemoveRandomBagStudent(3).containsAll(students));
    }

    @Test
    void getClouds() {
        ArrayList<Cloud> clouds2Player = game2players.getBoard().getClouds();
        ArrayList<Cloud> clouds3Player = game3players.getBoard().getClouds();
        assertEquals(clouds2Player,game2players.getBoard().getClouds());
        assertEquals(clouds3Player,game3players.getBoard().getClouds());
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
    void removeCoinsFromReserve(){
        Board board=game2players.getBoard();
        assertEquals(18,board.getCoinReserve());
        try {
            board.removeCoinsFromReserve(16);
        }catch (OutOfCoinsException e){}
        assertEquals(2,board.getCoinReserve());
        boolean catched=false;
        try{
            board.removeCoinsFromReserve(4);
        }catch (OutOfCoinsException e){
            catched=true;
        }
        finally {
            assertTrue(catched);
            assertEquals(2,board.getCoinReserve());
        }
    }

    @Test
    void getCharacters() {
        ArrayList<Characters> characters = game3players.getBoard().getCharacters();
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

    @Test
    void getCloud(){
        Board board=game2players.getBoard();
        ArrayList<Student> students=new ArrayList<>();
        students.add(Student.RED);
        students.add(Student.GREEN);
        board.getCloud(2).setStudents(students);
        board.getCloud(2).setChoosen(true);

        Cloud cloud=board.getCloud(2);
        assertTrue(cloud.isChoosen());
        assertTrue(cloud.getStudents().containsAll(students));
    }

    @Test
    void getBoardView(){
        // check only size because other tests check if all attributes are equals
        Board board=game2players.getBoard();

        BoardView boardView=board.getBoardView();
        assertEquals(2,boardView.getClouds().size());
        assertEquals(12,boardView.getIslandViews().size());
        assertEquals(3,boardView.getCharacters().size());
    }
}