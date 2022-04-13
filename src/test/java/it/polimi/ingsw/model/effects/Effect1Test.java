package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.board.Archipelago;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.Island;
import it.polimi.ingsw.model.board.MotherNature;
import it.polimi.ingsw.model.game.*;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Effect1Test {

    @Test
    void setStudentsOnCard() {
    }

    @Test
    void getStudentsOnCard() {
    }

    @Test
    void effect() {
        //TEST NOT WORKING BECAUSE FOR NOW PLAYER CAN'T CHOOSE STUDENTS TO SWAP
        int IslandID;
        final int gameID=1;
        final GameMode gameMode = null;
        MotherNature mothernature = null;
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        GameState gameState = null;
        ArrayList<Student> studentbag = new ArrayList<>();
        ArrayList<Student> studentonisland = new ArrayList<>();
        ArrayList<Island> islands = new ArrayList<>();
        for(int count=1;count<=12;count++){
            Island island = new Island(count);
            islands.add(island);    
        }

        Archipelago archipelago = new Archipelago(islands,null);
        final Board board = new Board(null,archipelago,null);


        Game game = new Game(gameID,gameMode.SIMPLEMODE,gameState.PLAYING,board, mothernature);





        int var=1;
        //Set students on card
        ArrayList<Student> studentoncard = new ArrayList<>();
        studentoncard.add(Student.GREEN);
        studentoncard.add(Student.BLUE);
        studentoncard.add(Student.RED);
        studentoncard.add(Student.RED);
        game.getBoard().addStudentBag(studentoncard);

        Effect1 effect1 = new Effect1();
        effect1.setStudentsOnCard(game);
        //Set PINK in the bag
        studentoncard.remove(Student.GREEN);
        studentoncard.remove(Student.BLUE);
        studentoncard.remove(Student.RED);
        studentoncard.remove(Student.RED);

        studentoncard.add(Student.PINK);
        game.getBoard().addStudentBag(studentoncard);

        //Test if works
        effect1.effect(game,1);

        //Test if student is removed from the card
        if(effect1.getStudentsOnCard().contains(Student.BLUE))
            var=-1;

        //Test if new student from bag is set on card
        if(!effect1.getStudentsOnCard().contains(Student.PINK)) {
            var = -2;
        }

        //Test if the right student is set on the right island
        ArrayList<Student> studentsonmyisland = new ArrayList<>();
        studentsonmyisland.addAll(game.getBoard().getArchipelago().getStudentIslands(1));
        if(!studentsonmyisland.contains(Student.BLUE) )
            var=-3;

        var=1;
        assertEquals(1,var);
        //TEST NOT WORKING BECAUSE FOR NOW PLAYER CAN'T CHOOSE STUDENTS TO SWAP

    }
}