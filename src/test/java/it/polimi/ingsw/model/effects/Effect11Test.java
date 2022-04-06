package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.board.Archipelago;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.Island;
import it.polimi.ingsw.model.board.MotherNature;
import it.polimi.ingsw.model.game.*;
import it.polimi.ingsw.model.player.Plance;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Effect11Test {

    @Test
    void setStudentsOnCard() {
    }

    @Test
    void getStudentsOnCard() {
    }

    @Test
    void effect() {
        final int gameID = 1;
        final GameMode gameMode = null;
        VerifyType verifyType = null;
        MotherNature mothernature = null;
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        GameState gameState = null;
        ArrayList<Student> studentbag = new ArrayList<>();
        ArrayList<Student> studentonisland = new ArrayList<>();
        Island island = new Island( 1);
        ArrayList<Island> islands = new ArrayList<>();
        islands.add(island);
        Archipelago archipelago = new Archipelago(islands, null);
        final Board board = new Board(null, archipelago, null);

        Game game = new Game(gameID, gameMode.SIMPLEMODE,gameState.PLAYING, board, verifyType, mothernature);
        ArrayList<Student> entrance = new ArrayList<>();
        Plance plance = new Plance();
        Player player = new Player(null,1, PlayerState.PLAYINGYOURTURN,plance,null,null);
        game.addPlayer(player,player.getPlayerID());

        Effect11 effect11 = new Effect11();
        //Set students on card
        ArrayList<Student> studentoncard = new ArrayList<>();
        studentoncard.add(Student.BLUE);
        studentoncard.add(Student.RED);
        studentoncard.add(Student.BLUE);
        studentoncard.add(Student.YELLOW);
        game.getBoard().addStudentBag(studentoncard);
        effect11.setStudentsOnCard(game);


        //Set the bag
        ArrayList<Student> addtobag = new ArrayList<>();
        addtobag.add(Student.PINK);
        game.getBoard().addStudentBag(addtobag);

        effect11.effect(game,player.getPlayerID());

        studentoncard.remove(Student.BLUE);
        studentoncard.add(Student.PINK);
        int var=1;
        if(!effect11.getStudentsOnCard().containsAll(studentoncard))
            var=-1;

        if(player.getPlance().getNumberOfStudent(Student.BLUE)!=1)
            var=-2;
        if(player.getPlance().getNumberOfStudent(Student.RED)!=0)
            var=-3;
        if(player.getPlance().getNumberOfStudent(Student.YELLOW)!=0)
            var=-4;
        if(player.getPlance().getNumberOfStudent(Student.PINK)!=0)
            var=-5;
        if(player.getPlance().getNumberOfStudent(Student.GREEN)!=0)
            var=-6;

        assertEquals(1,var);

    }
}