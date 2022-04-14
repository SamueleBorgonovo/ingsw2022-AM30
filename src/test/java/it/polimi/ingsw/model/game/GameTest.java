package it.polimi.ingsw.model.game;

import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Wizard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    Game game2players = new Game(GameMode.SIMPLEMODE,2);
    Game game3players = new Game(GameMode.SIMPLEMODE,3);
    Player player1 = new Player("Daniele", Wizard.WIZARDYELLOW);
    Player player2 = new Player("Giuseppe", Wizard.WIZARDBLUE);
    Player player3 = new Player("Samuele", Wizard.WIZARDGREEN);

    @Test
    void getState() {
        // Test 2 Players Game
        game2players.addPlayer(player1);
        assertEquals(GameState.WAITINGFORPLAYERS,game2players.getState());
        game2players.addPlayer(player2);
        assertEquals(GameState.PLAYING,game2players.getState());
        game3players.addPlayer(player1);

        // Test 3 Players Game
        assertEquals(GameState.WAITINGFORPLAYERS,game3players.getState());
        game3players.addPlayer(player2);
        assertEquals(GameState.WAITINGFORPLAYERS,game3players.getState());
        game3players.addPlayer(player3);
        assertEquals(GameState.PLAYING,game3players.getState());
    }

    @Test
    void addPlayer() {
        // Test 2 Players Game
        assertEquals(0,game2players.getListOfPlayers().size());
        game2players.addPlayer(player1);
        assertTrue(game2players.getListOfPlayers().contains(player1));
        assertEquals(1,game2players.getListOfPlayers().size());
        assertEquals(Tower.WHITE,player1.getPlance().getTower());
        assertEquals(8,player1.getPlance().getNumOfTowers());
        assertEquals(7,player1.getPlance().getEntrance().size());
        game2players.addPlayer(player2);
        assertTrue(game2players.getListOfPlayers().contains(player2));
        assertEquals(2,game2players.getListOfPlayers().size());
        assertEquals(Tower.BLACK,player2.getPlance().getTower());
        assertEquals(8,player2.getPlance().getNumOfTowers());
        assertEquals(7,player2.getPlance().getEntrance().size());

        // Test 3 Players Game
        assertEquals(0,game3players.getListOfPlayers().size());
        game3players.addPlayer(player1);
        assertTrue(game3players.getListOfPlayers().contains(player1));
        assertEquals(1,game3players.getListOfPlayers().size());
        assertEquals(Tower.WHITE,player1.getPlance().getTower());
        assertEquals(6,player1.getPlance().getNumOfTowers());
        assertEquals(9,player1.getPlance().getEntrance().size());
        game3players.addPlayer(player2);
        assertTrue(game3players.getListOfPlayers().contains(player2));
        assertEquals(2,game3players.getListOfPlayers().size());
        assertEquals(Tower.BLACK,player2.getPlance().getTower());
        assertEquals(6,player2.getPlance().getNumOfTowers());
        assertEquals(9,player2.getPlance().getEntrance().size());
        game3players.addPlayer(player3);
        assertTrue(game3players.getListOfPlayers().contains(player3));
        assertEquals(3,game3players.getListOfPlayers().size());
        assertEquals(Tower.GREY,player3.getPlance().getTower());
        assertEquals(6,player3.getPlance().getNumOfTowers());
        assertEquals(9,player3.getPlance().getEntrance().size());
    }

    @Test
    void getGameMode() {
    }

    @Test
    void getBoard() {
    }

    @Test
    void getNumOfPlayers() {
    }

    @Test
    void getPlayer() {
    }

    @Test
    void winner() {
    }

    @Test
    void verifyProfessorControl() {
    }

    @Test
    void startTurn() {
    }

    @Test
    void endTurn() {
    }

    @Test
    void moveStudentToEntrance() {
    }

    @Test
    void moveStudentToHall() {
    }

    @Test
    void moveStudentToIsland() {
    }

    @Test
    void useAssistant() {
    }

    @Test
    void moveMotherNature() {
    }

    @Test
    void useCharacter() {
    }

    @Test
    void getVerifyType() {
    }

    @Test
    void verifyMergeableIsland() {
    }

    @Test
    void chooseStudent() {
    }

    @Test
    void chooseIsland() {
    }

    @Test
    void chooseStudentFromPlance() {
    }

    @Test
    void getListOfPlayers() {
    }

    @Test
    void verifyIslandInfluence() {
    }

    @Test
    void verifyProfessorControll() {
    }

    @Test
    void verifyPlayerOrder() {
        game3players.addPlayer(player1);
        game3players.addPlayer(player2);
        game3players.addPlayer(player3);

        //Set last played assistant
        player1.removeAssistant(Assistant.SNAKE);
        player2.removeAssistant(Assistant.TURTLE);
        player3.removeAssistant(Assistant.CAT);

        ArrayList<Player> playerorder = new ArrayList<>();
        playerorder.addAll(game3players.verifyPlayerOrder());

        int var=1;
        if(playerorder.get(0).getPlayerID() != player3.getPlayerID())
            var=-1;
        if(playerorder.get(1).getPlayerID() != player1.getPlayerID())
            var=-2;
        if(playerorder.get(2).getPlayerID() != player2.getPlayerID())
            var=-3;

        assertEquals(1,var);
    }

    @Test
    void startGame() {
    }
}