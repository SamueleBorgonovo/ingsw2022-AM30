package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGame {

    @Test
    public void VerifyPlayOrder(){

        //Set game class
        final int gameID = 1;
        final GameMode gameMode = null;
        final int numOfPlayers = 4;
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

        Game game = new Game(gameID, gameMode.SIMPLEMODE,gameState.PLAYING, board, numOfPlayers, verifyType, mothernature);

        //Set arraylist of assistants
        ArrayList<Assistant> assistants = new ArrayList<>();
        assistants.add(Assistant.EAGLE);
        assistants.add(Assistant.CAT);
        assistants.add(Assistant.OSTRICH);
        assistants.add(Assistant.SNAKE);
        assistants.add(Assistant.TURTLE);
        assistants.add(Assistant.DOG);
        assistants.add(Assistant.ELEPHANTS);
        assistants.add(Assistant.FOX);
        assistants.add(Assistant.LION);
        assistants.add(Assistant.OCTOPUS);


        //Set all players
        Plance plance1 = new Plance();
        Player player1 = new Player(null,1,PlayerState.PLAYINGYOURTURN,plance1,null,assistants);
        game.addPlayer(player1,player1.getPlayerID());

        Plance plance2 = new Plance();
        Player player2 = new Player(null,2,PlayerState.PLAYINGYOURTURN,plance2,null,assistants);
        game.addPlayer(player2,player2.getPlayerID());

        Plance plance3 = new Plance();
        Player player3 = new Player(null,3,PlayerState.PLAYINGYOURTURN,plance3,null,assistants);
        game.addPlayer(player3,player3.getPlayerID());

        Plance plance4 = new Plance();
        Player player4 = new Player(null,4,PlayerState.PLAYINGYOURTURN,plance4,null,assistants);
        game.addPlayer(player4,player4.getPlayerID());

        //Set last played assistant
        player1.removeAssistant(Assistant.SNAKE);
        player2.removeAssistant(Assistant.TURTLE);
        player3.removeAssistant(Assistant.CAT);
        player4.removeAssistant(Assistant.LION);

        ArrayList<Player> playerorder = new ArrayList<>();
        playerorder.addAll(game.VerifyPlayerOrder());

        int var=1;
        if(playerorder.get(0).getPlayerID() != player4.getPlayerID())
            var=-1;
        if(playerorder.get(1).getPlayerID() != player3.getPlayerID())
            var=-2;
        if(playerorder.get(2).getPlayerID() != player1.getPlayerID())
            var=-3;
        if(playerorder.get(3).getPlayerID() != player2.getPlayerID())
            var=-4;

        assertEquals(1,var);







    }
}
