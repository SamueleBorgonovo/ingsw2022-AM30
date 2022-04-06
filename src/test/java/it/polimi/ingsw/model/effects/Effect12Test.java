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

class Effect12Test {

    @Test
    void effect() {
        int IslandID;
        final int gameID=1;
        final GameMode gameMode = null;
        VerifyType verifyType = null;
        MotherNature mothernature = null;
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        GameState gameState = null;
        ArrayList<Student> studentbag = new ArrayList<>();
        ArrayList<Student> studentonisland = new ArrayList<>();
        Island island = new Island(1);
        ArrayList<Island> islands = new ArrayList<>();
        islands.add(island);
        Archipelago archipelago = new Archipelago(islands,null);
        final Board board = new Board(null,archipelago,null);


        Game game = new Game(gameID,gameMode.SIMPLEMODE,gameState.PLAYING,board,verifyType,mothernature);

        Plance plance1 = new Plance();
        Player player1 = new Player(null, 1, PlayerState.PLAYINGYOURTURN, plance1, null, null);
        game.addPlayer(player1, player1.getPlayerID());

        Plance plance2 = new Plance();
        Player player2 = new Player(null, 2, PlayerState.PLAYINGYOURTURN, plance2, null, null);
        game.addPlayer(player2, player2.getPlayerID());

        //Set hall player1
        player1.getPlance().addStudentHall(Student.BLUE);
        player1.getPlance().addStudentHall(Student.RED);
        player1.getPlance().addStudentHall(Student.RED);

        //Set hall player2
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.PINK);

        Effect12 effect12 = new Effect12();

        effect12.effect(game,player1.getPlayerID());

        int var=1;
        //Test that choosedstudent is removed from player1's hall
        if(player1.getPlance().getNumberOfStudent(Student.BLUE)!=0)
            var=-1;
        //Test that choosedstudent is removed from player2's hall
        if(player2.getPlance().getNumberOfStudent(Student.BLUE)!=0)
            var=-2;
        //Test that bag is filled with removed students
        if(game.getBoard().getNumOfStudentsBag()!=4){
            var=-3;
        }
        else {
            ArrayList<Student> bag = new ArrayList<>();
            bag = game.getBoard().getAndRemoveRandomBagStudent(4);
            for(int count=0;count<bag.size();count++){
                if(bag.get(count)!=Student.BLUE)
                    var=-4;
            }
        }
        //Test that not choosedstudent are not changed in player1's hall
        if(player1.getPlance().getNumberOfStudent(Student.RED)!=2)
            var=-5;
        //Test that not choosedstudent are not changed in player2's hall
        if(player2.getPlance().getNumberOfStudent(Student.PINK)!=1)
            var=-6;

        assertEquals(1,var);
    }

    @Test
    public void TestEffect02(){
        int IslandID;
        final int gameID=1;
        final GameMode gameMode = null;
        VerifyType verifyType = null;
        MotherNature mothernature = null;
        ArrayList<Player>listOfPlayers = new ArrayList<>();
        GameState gameState = null;
        ArrayList<Student> studentbag = new ArrayList<>();
        ArrayList<Student> studentonisland = new ArrayList<>();
        Island island = new Island(1);
        ArrayList<Island> islands = new ArrayList<>();
        islands.add(island);
        Archipelago archipelago = new Archipelago(islands,null);
        final Board board = new Board(null,archipelago,null);


        Game game = new Game(gameID,gameMode.SIMPLEMODE,gameState.PLAYING,board,verifyType,mothernature);

        Plance plance1 = new Plance();
        Player player1 = new Player(null, 1, PlayerState.PLAYINGYOURTURN, plance1, null,  null);
        game.addPlayer(player1, player1.getPlayerID());

        Plance plance2 = new Plance();
        Player player2 = new Player(null, 2, PlayerState.PLAYINGYOURTURN, plance2, null,  null);
        game.addPlayer(player2, player2.getPlayerID());

        Plance plance3 = new Plance();
        Player player3 = new Player(null, 3, PlayerState.PLAYINGYOURTURN, plance3, null,  null);
        game.addPlayer(player3, player3.getPlayerID());

        //Set hall player1
        player1.getPlance().addStudentHall(Student.BLUE);
        player1.getPlance().addStudentHall(Student.RED);
        player1.getPlance().addStudentHall(Student.RED);

        //Set hall player2
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.PINK);
        player2.getPlance().addStudentHall(Student.BLUE);


        //Set hall player3
        player3.getPlance().addStudentHall((Student.BLUE));
        player3.getPlance().addStudentHall((Student.BLUE));
        player3.getPlance().addStudentHall((Student.YELLOW));
        player3.getPlance().addStudentHall((Student.GREEN));

        Effect12 effect12 = new Effect12();

        effect12.effect(game,player1.getPlayerID());

        int var=1;
        //Test that choosedstudent is removed from player1's hall
        if(player1.getPlance().getNumberOfStudent(Student.BLUE)!=0)
            var=-1;
        //Test that 3 choosedstudent is removed from player2's hall
        if(player2.getPlance().getNumberOfStudent(Student.BLUE)!=1)
            var=-2;
        //Test that choosedstudent is removed from player3's hall
        if(player3.getPlance().getNumberOfStudent(Student.BLUE)!=0)
            var=-3;
        //Test that bag is filled with removed students
        if(game.getBoard().getNumOfStudentsBag()!=6){
            var=-4;
        }
        else {
            ArrayList<Student> bag = new ArrayList<>();
            bag = game.getBoard().getAndRemoveRandomBagStudent(6);
            for(int count=0;count<bag.size();count++){
                if(bag.get(count)!=Student.BLUE)
                    var=-5;
            }
        }
        //Test that not choosedstudent are not changed in player1's hall
        if(player1.getPlance().getNumberOfStudent(Student.RED)!=2)
            var=-6;
        //Test that not choosedstudent are not changed in player2's hall
        if(player2.getPlance().getNumberOfStudent(Student.PINK)!=1)
            var=-7;
        if(player2.getPlance().getNumberOfStudent(Student.BLUE)!=1)
            var=-8;
        //Test that not choosedstudent are not changed in player3's hall
        if(player3.getPlance().getNumberOfStudent(Student.GREEN)!=1)
            var=-9;
        if(player3.getPlance().getNumberOfStudent(Student.YELLOW)!=1)
            var=-10;
        assertEquals(1,var);
    }
}