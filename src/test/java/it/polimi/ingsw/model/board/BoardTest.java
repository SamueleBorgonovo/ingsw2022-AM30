package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.effects.Effect;
import it.polimi.ingsw.model.effects.Effect10;
import it.polimi.ingsw.model.effects.Effect2;
import it.polimi.ingsw.model.effects.Effect6;
import it.polimi.ingsw.model.game.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Island i1;
    Island i2;
    Island i3;
    Island i4;
    Island i5;
    Island i6;
    Island i7;
    Island i8;
    Island i9;
    Island i10;
    Island i11;
    Island i12;

    MotherNature motherNature;
    ArrayList<Island> islands;
    Archipelago archipelago;
    Cloud cloud1;
    Cloud cloud2;
    ArrayList<Cloud> clouds;
    Effect effect2;
    Effect effect6;
    Effect effect10;
    Character character2;
    Character character6;
    Character character10;
    ArrayList<Character> characters;
    Board board;
    Board board2;

    @BeforeEach
    void init() {
        i1 = new Island(1);
        i2 = new Island(2);
        i3 = new Island(3);
        i4 = new Island(4);
        i5 = new Island(5);
        i6 = new Island(6);
        i7 = new Island(7);
        i8 = new Island(8);
        i9 = new Island(9);
        i10 = new Island(10);
        i11 = new Island(11);
        i12 = new Island(12);
        motherNature = new MotherNature(2);
        islands = new ArrayList<>();

        i1.addStudent(Student.RED);
        i1.addStudent(Student.RED);
        i1.addStudent(Student.YELLOW);
        i1.addStudent(Student.BLUE);
        i2.addStudent(Student.RED);
        i3.addStudent(Student.RED);
        islands.add(i1);
        islands.add(i2);
        islands.add(i3);
        islands.add(i4);
        islands.add(i5);
        islands.add(i6);
        islands.add(i7);
        islands.add(i8);
        islands.add(i9);
        islands.add(i10);
        islands.add(i11);
        islands.add(i12);
        archipelago = new Archipelago(islands, motherNature);
        cloud1= new Cloud(1);
        cloud2 = new Cloud(2);
        clouds = new ArrayList<>();
        clouds.add(cloud1);
        clouds.add(cloud2);
        effect2 = new Effect2();
        character2 =new Character(2, effect2);
        effect6 = new Effect6();
        character6=new Character(3,effect6);
        effect10 = new Effect10();
        character10 = new Character(1,effect10);
        characters = new ArrayList<>();
        characters.add(character2);
        characters.add(character6);
        characters.add(character10);
        board = new Board(clouds,archipelago,characters);
        board2 = new Board(clouds,archipelago);
    }

    @Test
    void getNumOfStudentsBag() {
        //Also tests addStudentBag
        assertEquals(130, board.getNumOfStudentsBag());
        board.getAndRemoveRandomBagStudent(5);
        assertEquals(125, board.getNumOfStudentsBag());
        ArrayList<Student> students = new ArrayList<>();
        students.add(Student.RED);
        students.add(Student.BLUE);
        board.addStudentBag(students);
        assertEquals(127,board.getNumOfStudentsBag());
    }

    @Test
    void getAndRemoveRandomBagStudent() {
        board.getAndRemoveRandomBagStudent(3);
        assertEquals(127, board.getNumOfStudentsBag());
    }

    @Test
    void getClouds() {
        ArrayList<Cloud> cloudsExpected = new ArrayList<>();
        cloudsExpected.add(cloud1);
        cloudsExpected.add(cloud2);
        clouds= board.getClouds();
        assertTrue(cloudsExpected.equals(clouds));
    }

    @Test
    void getCoinReserve() {
        //also tests addCoinToReserve and removeCoinsFromReserve
        assertEquals(20,board.getCoinReserve());
        board.removeCoinsFromReserve(3);
        assertEquals(17, board.getCoinReserve());
        board.addCoinstoReserve(1);
        assertEquals(18, board.getCoinReserve());
        board.removeCoinsFromReserve(19);
        assertEquals(0, board.getCoinReserve());
    }

    @Test
    void getCharacters() {
        assertTrue(characters.equals(board.getCharacters()));

    }

    @Test
    void getArchipelago() {
        assertTrue(archipelago.equals(board.getArchipelago()));
    }
}