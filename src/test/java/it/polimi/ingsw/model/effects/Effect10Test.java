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

class Effect10Test {

    @Test
    void setNumberofstudents() {
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

        Game game = new Game(gameID, gameMode.SIMPLEMODE,gameState.PLAYING, board, mothernature);
        Plance plance = new Plance(Tower.WHITE,8);
        Player player = new Player(null,1, PlayerState.MOTHERNATUREPHASE,plance,null,null);
        game.addPlayer(player,player.getPlayerID());

        Effect10 effect10 = new Effect10();
        effect10.setNumberofstudents(1);
        player.getPlance().addStudentEntrance(Student.BLUE);
        player.getPlance().addStudentEntrance((Student.BLUE));

        player.getPlance().addStudentHall(Student.YELLOW);
        player.getPlance().addStudentHall(Student.YELLOW);

        effect10.effect(game,player.getPlayerID());

        int var=1;
        int yellow=0;
        int blue=0;
        for(int count=0;count<player.getPlance().getEntrance().size();count++){
            if(player.getPlance().getEntrance().get(count)==Student.YELLOW)
                yellow=1;
            else if(player.getPlance().getEntrance().get(count)==Student.BLUE)
                blue=1;
        }
        if(blue+yellow!=2)
            var=-1;

        int numberofinterestedstudent = player.getPlance().getNumberOfStudentHall(Student.BLUE);
        if (numberofinterestedstudent != 1)
            var=-2;

        numberofinterestedstudent = player.getPlance().getNumberOfStudentHall((Student.YELLOW));
        if(numberofinterestedstudent != 1)
            var=-3;

        assertEquals(1,var);
    }

    @Test
    public void effect02(){
        final int gameID = 1;
        final GameMode gameMode = null;
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

        Game game = new Game(gameID, gameMode.SIMPLEMODE,gameState.PLAYING, board, mothernature);
        ArrayList<Student> entrance = new ArrayList<>();
        Plance plance = new Plance(Tower.WHITE,8);
        Player player = new Player(null,1,PlayerState.MOTHERNATUREPHASE,plance,null,null);
        game.addPlayer(player,player.getPlayerID());

        Effect10 effect10 = new Effect10();
        effect10.setNumberofstudents(2);
        player.getPlance().addStudentEntrance(Student.BLUE);
        player.getPlance().addStudentEntrance((Student.BLUE));

        player.getPlance().addStudentHall(Student.YELLOW);
        player.getPlance().addStudentHall(Student.YELLOW);

        effect10.effect(game,player.getPlayerID());

        int var=1;
        int yellow=0;
        int blue=0;
        for(int count=0;count<player.getPlance().getEntrance().size();count++){
            if(player.getPlance().getEntrance().get(count)==Student.YELLOW)
                yellow++;
        }
        if(yellow!=2)
            var=-1;

        int numberofinterestedstudent = player.getPlance().getNumberOfStudentHall(Student.BLUE);
        if (numberofinterestedstudent != 2)
            var=-2;


        assertEquals(1,var);
    }
}