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

class Effect7Test {

    @Test
    void setStudentsOnCard() {
    }

    @Test
    void getStudentOnCard() {
    }

    @Test
    void setNumberofstudent() {
    }

    @Test
    void effect01() {
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

        Effect7 effect7 = new Effect7();


        //Set students on card
        studentbag.add(Student.GREEN);
        studentbag.add(Student.BLUE);
        studentbag.add(Student.RED);
        studentbag.add(Student.BLUE);
        studentbag.add(Student.RED);
        studentbag.add(Student.BLUE);
        effect7.setStudentsOnCard(game);

        //Set number of student to change
        effect7.setNumberofstudent(3);

        //Set students on entrance
        game.getPlayer(1).getPlance().addStudentEntrance(Student.YELLOW);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.YELLOW);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.YELLOW);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.YELLOW);

        effect7.effect(game,1);

        int var=1;
        int counter=0;

        for(int count=0;count<effect7.getStudentOnCard().size();count++){
            if(effect7.getStudentOnCard().get(count)==Student.YELLOW)
                counter++;
        }
        if(counter!=3)
            var=-1;

        ArrayList<Student> studentsentrance = new ArrayList<>();
        studentsentrance.addAll(game.getPlayer(1).getPlance().getEntrance());
        counter=0;
        for(int count=0;count<studentsentrance.size();count++){
            if(studentsentrance.get(count)==Student.BLUE)
                counter++;
        }
        if(counter!=3)
            var=-2;

        assertEquals(1,var);
    }

    @Test
    public void effect02(){
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
        Player player = new Player(null,1,PlayerState.PLAYINGYOURTURN,plance,null,null);
        game.addPlayer(player,player.getPlayerID());

        Effect7 effect7 = new Effect7();

        //Set students on card
        studentbag.add(Student.GREEN);
        studentbag.add(Student.BLUE);
        studentbag.add(Student.RED);
        studentbag.add(Student.BLUE);
        studentbag.add(Student.RED);
        studentbag.add(Student.BLUE);
        effect7.setStudentsOnCard(game);

        //Set number of student to change
        effect7.setNumberofstudent(1);

        //Set students on entrance
        game.getPlayer(1).getPlance().addStudentEntrance(Student.YELLOW);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.YELLOW);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.YELLOW);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.YELLOW);

        effect7.effect(game,1);

        int var=1;
        int counter=0;
        //Test if right students are on cards
        for(int count=0;count<effect7.getStudentOnCard().size();count++){
            if(effect7.getStudentOnCard().get(count)==Student.YELLOW)
                counter++;
        }
        if(counter!=1)
            var=-1;

        //Test if right students are on entrance
        ArrayList<Student> studentsentrance = new ArrayList<>();
        studentsentrance.addAll(game.getPlayer(1).getPlance().getEntrance());
        counter=0;
        for(int count=0;count<studentsentrance.size();count++){
            if(studentsentrance.get(count)==Student.BLUE)
                counter++;
        }
        if(counter!=1)
            var=-2;

        counter=0;
        for(int count=0;count<studentsentrance.size();count++){
            if(studentsentrance.get(count)==Student.YELLOW)
                counter++;
        }
        if(counter!=3)
            var=-3;

        assertEquals(1,var);
    }

}