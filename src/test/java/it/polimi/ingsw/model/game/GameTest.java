package it.polimi.ingsw.model.game;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.player.*;
import jdk.swing.interop.LightweightContentWrapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game2players = new Game(GameMode.SIMPLEMODE,2);
    Game game3players = new Game(GameMode.SIMPLEMODE,3);
    Player player1;
    Player player2;
    Player player3;

    @Test
    void settingsTest() {
        // Test 2 Players Game

        game2players.addPlayer("Daniele");
        assertEquals(GameState.WAITINGFORPLAYERS,game2players.getState());
        assertEquals(GameMode.SIMPLEMODE,game2players.getGameMode());
    }

    @Test
    void addPlayer() {
        // Test 2 Players Game
        assertEquals(0,game2players.getListOfPlayers().size());
        game2players.addPlayer("Daniele");
        player1 = game2players.getPlayer(1);
        assertTrue(game2players.getListOfPlayers().contains(player1));
        assertEquals(1,game2players.getListOfPlayers().size());
        assertEquals(Tower.WHITE,player1.getPlance().getTower());
        assertEquals(8,player1.getPlance().getNumOfTowers());
        assertEquals(7,player1.getPlance().getEntrance().size());
        game2players.addPlayer("Giuseppe");
        player2 = game2players.getPlayer(2);
        assertTrue(game2players.getListOfPlayers().contains(player2));
        assertEquals(2,game2players.getListOfPlayers().size());
        assertEquals(Tower.BLACK,player2.getPlance().getTower());
        assertEquals(8,player2.getPlance().getNumOfTowers());
        assertEquals(7,player2.getPlance().getEntrance().size());

        // Test 3 Players Game
        assertEquals(0,game3players.getListOfPlayers().size());
        game3players.addPlayer("Daniele");
        player1 = game3players.getPlayer(1);
        assertTrue(game3players.getListOfPlayers().contains(player1));
        assertEquals(1,game3players.getListOfPlayers().size());
        assertEquals(Tower.WHITE,player1.getPlance().getTower());
        assertEquals(6,player1.getPlance().getNumOfTowers());
        assertEquals(9,player1.getPlance().getEntrance().size());
        game3players.addPlayer("Giuseppe");
        player2 = game3players.getPlayer(2);
        assertTrue(game3players.getListOfPlayers().contains(player2));
        assertEquals(2,game3players.getListOfPlayers().size());
        assertEquals(Tower.BLACK,player2.getPlance().getTower());
        assertEquals(6,player2.getPlance().getNumOfTowers());
        assertEquals(9,player2.getPlance().getEntrance().size());
        game3players.addPlayer("Samuele");
        player3 = game3players.getPlayer(3);
        assertTrue(game3players.getListOfPlayers().contains(player3));
        assertEquals(3,game3players.getListOfPlayers().size());
        assertEquals(Tower.GREY,player3.getPlance().getTower());
        assertEquals(6,player3.getPlance().getNumOfTowers());
        assertEquals(9,player3.getPlance().getEntrance().size());
    }

    @Test
    void checkPlayerState(){
        game2players.addPlayer("d");
        player1=game2players.getPlayer(1);
        assertFalse(game2players.checkPlayerState(1));
        game2players.getPlayer(1).setPlayerState(PlayerState.DISCONNECTED);
        assertTrue(game2players.checkPlayerState(1));

        player2=game2players.getPlayer(4);
        assertNull(player2);
    }

    @Test
    void getBoard() {
    }

    @Test
    void getNumOfPlayers() {
        assertEquals(2,game2players.getNumOfPlayers());
        assertEquals(3,game3players.getNumOfPlayers());
    }

    @Test
    void getPlayer() {
    }

    @Test
    void winnerTest() {
        game2players.addPlayer("kek");
        game2players.addPlayer("tom");
        player1=game2players.getPlayer(1);
        player2=game2players.getPlayer(2);

        assertEquals(0,game2players.winnerEndRound());
        int num=game2players.getBoard().getNumOfStudentsBag();
        game2players.getBoard().getAndRemoveRandomBagStudent(num);
        assertEquals(1,game2players.winnerEndRound());
        ArrayList<Student> students=new ArrayList<>();
        students.add(Student.RED);
        students.add(Student.YELLOW);
        students.add(Student.BLUE);
        game2players.getBoard().addStudentBag(students);
        game2players.getPlayer(1).getAssistantCards().clear();
        assertEquals(2,game2players.winnerEndRound());

        game3players.addPlayer("kek");
        game3players.addPlayer("tom");
        game3players.addPlayer("sam");
        player1=game3players.getPlayer(1);
        assertEquals(0,game3players.winnerIstantly());
        num=game3players.getPlayer(1).getPlance().getNumOfTowers();
        for(int i=0;i<num;i++) {
            player1.getPlance().removeTower();
        }
        assertEquals(2,game3players.winnerIstantly());
        game3players.getPlayer(1).getPlance().addTower();

        for(int i=11;i>2;i--) {
            game3players.getBoard().getArchipelago().getIslands().remove(i);
        }
        assertEquals(1,game3players.winnerIstantly());

        ArrayList<Player> winners;
        winners=game3players.verifyWinner();
        assertTrue(winners.contains(game3players.getPlayer(1)));

        player2=game3players.getPlayer(2);
        for(int i=1;i<num;i++) {
            player2.getPlance().removeTower();
        }

        player1.getPlance().addProfessor(Professor.BLUE_UNICORN);
        player2.getPlance().addProfessor(Professor.RED_DRAGON);
        winners=game3players.verifyWinner();
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        assertTrue(winners.containsAll(players));
    }

    @Test
    void verifyProfessorControl() {
        game2players.addPlayer("Daniele");
        player1 = game2players.getPlayer(1);
        game2players.addPlayer("Giuseppe");
        player2 = game2players.getPlayer(2);
        player1.getPlance().addStudentEntrance(Student.RED);
        player1.getPlance().addStudentEntrance(Student.RED);
        player2.getPlance().addStudentEntrance(Student.RED);
        player1.getPlance().addStudentHall(Student.RED);
        player1.getPlance().addStudentHall(Student.RED);
        player2.getPlance().addStudentHall(Student.RED);
        game2players.verifyProfessorControl();
        assertEquals(Professor.RED_DRAGON, player1.getPlance().getProfessors().get(0));
    }

    @Test
    void startTurn() {
    }

    @Test
    void endTurn() {
    }

    @Test
    void moveStudentToHall() throws InvalidTurnException, InvalidStudentException {
        Game game = new Game(GameMode.EXPERTMODE,2);
        game.addPlayer("Daniele");
        player1 = game.getPlayer(1);
        game.addPlayer("Giuseppe");
        player2 = game.getPlayer(2);
        game.getPlayer(1).setPlayerState(PlayerState.STUDENTPHASE);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.RED);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.RED);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.RED);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.BLUE);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.YELLOW);
       // System.out.println(player1.getPlance().getEntrance());
        try {
            game.moveStudentToHall(1, Student.RED);
            game.moveStudentToHall(1, Student.BLUE);
            game.moveStudentToHall(1, Student.YELLOW);
            player1.setPlayerState(PlayerState.STUDENTPHASE);
            game.moveStudentToHall(1, Student.RED);
            game.moveStudentToHall(1, Student.RED);
        }
        catch (InvalidStudentException e) {System.out.println("Errore");};
       // System.out.println(player1.getPlance().getEntrance());
        assertEquals(3,game.getPlayer(1).getPlance().getNumberOfStudentHall(Student.RED));
        assertEquals(1,game.getPlayer(1).getPlance().getNumberOfStudentHall(Student.BLUE));
        assertEquals(1,game.getPlayer(1).getPlance().getNumberOfStudentHall(Student.YELLOW));
        assertEquals(2,game.getPlayer(1).getCoins());

        game.getPlayer(1).setPlayerState(PlayerState.ASSISTANTPHASE);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.RED);
        boolean check=false;
        try{
            game.moveStudentToHall(1,Student.YELLOW);
        }catch(InvalidStudentException | InvalidTurnException e){
            check=true;
        }
        assertTrue(check);

        check=false;
        game.getPlayer(1).setPlayerState(PlayerState.STUDENTPHASE);
        for(Student student : game.getPlayer(1).getPlance().getEntrance())
            game.getPlayer(1).getPlance().removeStudentEntrance(student);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.RED);
        try{
            game.moveStudentToHall(1,Student.BLUE);
        }catch(InvalidStudentException | InvalidTurnException e){
            check=true;
        }
        assertTrue(check);
    }

    @Test
    void moveStudentToIsland() throws InvalidTurnException, InvalidStudentException {
        ArrayList<Student> students = new ArrayList<>();
        students.add(Student.RED);
        students.add(Student.BLUE);
        students.add(Student.YELLOW);
        game2players.addPlayer("Daniele");
        player1 = game2players.getPlayer(1);
        game2players.addPlayer("Giuseppe");
        player2 = game2players.getPlayer(2);
        player1.setPlayerState(PlayerState.STUDENTPHASE);
        player1.getPlance().addStudentEntrance(Student.RED);
        player1.getPlance().addStudentEntrance(Student.BLUE);
        player1.getPlance().addStudentEntrance(Student.YELLOW);
        game2players.moveStudentToIsland(1,game2players.getBoard().getArchipelago().getMothernature().isOn(),Student.RED);
        game2players.moveStudentToIsland(1,game2players.getBoard().getArchipelago().getMothernature().isOn(),Student.BLUE);
        game2players.moveStudentToIsland(1,game2players.getBoard().getArchipelago().getMothernature().isOn(),Student.YELLOW);
        assertEquals(game2players.getBoard().getArchipelago().getSingleIsland(game2players.getBoard().getArchipelago().getMothernature().isOn()).getStudents(),students);

        player1.setPlayerState(PlayerState.ASSISTANTPHASE);
        player1.getPlance().addStudentEntrance(Student.RED);
        boolean check=false;
        try{
            game2players.moveStudentToIsland(1,game2players.getBoard().getArchipelago().getMothernature().isOn(),Student.RED);
        }catch(InvalidTurnException e){
            check=true;
        }
        assertTrue(check);
    }

    @Test
    void useAssistant() throws InvalidAssistantException, InvalidTurnException {
        ArrayList<Assistant> wanted1 = new ArrayList<>(Arrays.asList(Assistant.values()));
        ArrayList<Assistant> wanted2 = new ArrayList<>(Arrays.asList(Assistant.values()));
        wanted1.remove(Assistant.CAT);
        wanted2.remove(Assistant.DOG);
        game2players.addPlayer("Daniele");
        player1 = game2players.getPlayer(1);
        game2players.addPlayer("Giuseppe");
        player2 = game2players.getPlayer(2);
        ArrayList<Player> playerorder = new ArrayList<>();
        playerorder = game2players.getPlayerorder();
        playerorder.add(0,player1);
        playerorder.add(1,player2);
        player1.setPlayerState(PlayerState.ASSISTANTPHASE);
        game2players.useAssistant(1,Assistant.CAT);
        player2.setPlayerState(PlayerState.ASSISTANTPHASE);
        game2players.useAssistant(2,Assistant.DOG);
        assertEquals(wanted1, player1.getAssistantCards());
        assertEquals(wanted2, player2.getAssistantCards());

        game3players.addPlayer("kek");
        game3players.addPlayer("tom");
        game3players.addPlayer("sam");
        game3players.setPlayerorder(game3players.getPlayers());
        game3players.getPlayer(1).setPlayerState(PlayerState.ASSISTANTPHASE);
        game3players.useAssistant(1,Assistant.CAT);
        game3players.getPlayer(2).setPlayerState(PlayerState.ASSISTANTPHASE);
        game3players.useAssistant(2,Assistant.LION);
        game3players.getPlayer(3).setPlayerState(PlayerState.ASSISTANTPHASE);
        game3players.useAssistant(3,Assistant.OSTRICH);
        assertFalse(game3players.getPlayer(1).getAssistantCards().contains(Assistant.CAT));
        assertFalse(game3players.getPlayer(2).getAssistantCards().contains(Assistant.LION));
        assertFalse(game3players.getPlayer(3).getAssistantCards().contains(Assistant.OSTRICH));

        Game game = new Game(GameMode.EXPERTMODE,3);
        game.addPlayer("tom");
        game.addPlayer("sam");
        game.addPlayer("dan");
        game.setPlayerorder(game.getPlayers());
        game.getPlayer(2).setPlayerState(PlayerState.DISCONNECTED);
        game.getPlayer(1).setPlayerState(PlayerState.ASSISTANTPHASE);
        game.getPlayer(3).setPlayerState(PlayerState.WAITING);
        game.useAssistant(1,Assistant.DOG);
        assertFalse(game.getPlayer(1).getAssistantCards().contains(Assistant.DOG));
        assertEquals(PlayerState.ASSISTANTPHASE,game.getPlayer(3).getPlayerState());
        assertEquals(PlayerState.WAITING,game.getPlayer(1).getPlayerState());


        Game game3 = new Game(GameMode.EXPERTMODE,3);
        game3.addPlayer("tom");
        game3.addPlayer("sam");
        game3.addPlayer("dan");
        game3.getPlayer(1).setLastassistantplayed(Assistant.LION);
        game3.getPlayer(2).setLastassistantplayed(Assistant.CAT);
        game3.getPlayer(3).setLastassistantplayed(Assistant.OSTRICH);
        game3.getPlayer(1).setPlayerState(PlayerState.WAITING);
        game3.getPlayer(2).setPlayerState(PlayerState.WAITING);
        game3.getPlayer(3).setPlayerState(PlayerState.WAITING);
        game3.setPlayerorder(game3.getPlayers());
        game3.getPlayer(1).setPlayerState(PlayerState.ASSISTANTPHASE);
        game3.useAssistant(1,Assistant.EAGLE);
        assertEquals(PlayerState.WAITING,game3.getPlayer(1).getPlayerState());
        assertEquals(PlayerState.ASSISTANTPHASE,game3.getPlayer(2).getPlayerState());
        game3.getPlayer(3).setPlayerState(PlayerState.DISCONNECTED);
        game3.useAssistant(2,Assistant.FOX);
        assertEquals(PlayerState.WAITING,game3.getPlayer(2).getPlayerState());
        assertEquals(PlayerState.DISCONNECTED,game3.getPlayer(3).getPlayerState());
        assertEquals(PlayerState.STUDENTPHASE,game3.getPlayer(1).getPlayerState());


        boolean check=false;
        try{
            game3.useAssistant(1,Assistant.TURTLE);
        }catch (InvalidTurnException e){
            check=true;
        }
        assertTrue(check);

        game3.getPlayer(1).setPlayerState(PlayerState.ASSISTANTPHASE);
        check=false;
        try{
            game3.useAssistant(1,Assistant.EAGLE);
        }catch (InvalidAssistantException e){
            check=true;
        }
        assertTrue(check);

        game3.useAssistant(1,Assistant.SNAKE);
        game3.getPlayer(2).setPlayerState(PlayerState.ASSISTANTPHASE);


        game3.getPlayer(3).setPlayerState(PlayerState.ASSISTANTPHASE);
        check=false;
        try{
            game3.useAssistant(2,Assistant.SNAKE);
        }catch(InvalidAssistantException e){
            check=true;
        }
        assertTrue(check);

    }

    @Test
    void moveMotherNature() throws InvalidTurnException, InvalidValueException, InvalidAssistantException {
        game2players.addPlayer("Daniele");
        player1 = game2players.getPlayer(1);
        game2players.addPlayer("Giuseppe");
        player2 = game2players.getPlayer(2);
        int num=0;
        ArrayList<Player> playerorder = new ArrayList<>();
        playerorder = game2players.getPlayerorder();
        playerorder.add(0,player1);
        playerorder.add(1,player2);
        player1.setLastassistantplayed(Assistant.CAT);
        player2.setLastassistantplayed(Assistant.DOG);


        for(Assistant assistant: Assistant.values()) {
            int j= game2players.getBoard().getArchipelago().getMothernature().isOn();
            player1.setPlayerState(PlayerState.ASSISTANTPHASE);
            game2players.useAssistant(1,assistant);
            player1.setPlayerState(PlayerState.MOTHERNATUREPHASE);
            Random rnd = new Random();
            game2players.moveMotherNature(1, num = (rnd.nextInt(assistant.getMovement())+1));
            assertEquals(((j + num - 1) % 12) + 1, game2players.getBoard().getArchipelago().getMothernature().isOn());
        }

        Game game = new Game(GameMode.EXPERTMODE,2);
        game.addPlayer("tom");
        game.addPlayer("sam");
        game.getPlayer(1).setLastassistantplayed(Assistant.SNAKE);
        boolean check=false;
        try{
            game.moveMotherNature(1,1);
        }catch (InvalidTurnException e){
            check=true;
        }
        assertTrue(check);

        game.getPlayer(1).setPlayerState(PlayerState.MOTHERNATUREPHASE);
        check=false;
        try{
            game.moveMotherNature(1,10);
        }catch (InvalidValueException e){
            check=true;
        }
        assertTrue(check);

        check=false;
        game.getEffectHandler().setTwomoremoves(true);
        game.getPlayer(1).setPlayerState(PlayerState.MOTHERNATUREPHASE);
        game.getPlayer(1).setLastassistantplayed(Assistant.LION);
        try{
            game.moveMotherNature(1,6);
        }catch (InvalidValueException e){
            check=true;
        }
        assertTrue(check);

    }

    @Test
    void useCharacter() {
    }

    @Test
    void getVerifyType() {
    }



    @Test
    void getListOfPlayers() {
    }

    @Test
    void verifyIslandInfluence() {
        game2players.addPlayer("Daniele");
        player1 = game2players.getPlayer(1);
        game2players.addPlayer("Giuseppe");
        player2 = game2players.getPlayer(2);
        player1.getPlance().addProfessor(Professor.RED_DRAGON);
        player1.getPlance().addProfessor(Professor.RED_DRAGON);
        player1.getPlance().addProfessor(Professor.GREEN_FROG);
        player2.getPlance().addProfessor(Professor.BLUE_UNICORN);
        game2players.getBoard().getArchipelago().getSingleIsland(2).addStudent(Student.RED);
        game2players.getBoard().getArchipelago().getSingleIsland(2).addStudent(Student.BLUE);
        game2players.getBoard().getArchipelago().getSingleIsland(2).addStudent(Student.GREEN);
        game2players.verifyIslandInfluence(2);
        assertEquals(Tower.WHITE, game2players.getBoard().getArchipelago().getSingleIsland(2).getTowerColor());
    }


    @Test
    void verifyPlayerOrder() {
        game3players.addPlayer("Daniele");
        player1 = game3players.getPlayer(1);
        game3players.addPlayer("Giuseppe");
        player2 = game3players.getPlayer(2);
        game3players.addPlayer("Giuseppe");
        player3 = game3players.getPlayer(3);

        //Set last played assistant
        player1.removeAssistant(Assistant.SNAKE);
        player2.removeAssistant(Assistant.TURTLE);
        player3.removeAssistant(Assistant.CAT);

        ArrayList<Player> playerorder = new ArrayList<>();
        game3players.setPlayerorder(game3players.getPlayers());
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

    @Test
    void wizardChoosenTest() {
        game2players.addPlayer("tom");
        player1=game2players.getPlayer(1);
        try {
            game2players.setWizard(1, Wizard.WIZARD_GREEN);
        } catch (InvalidWizardException e) {}
        assertTrue(game2players.getWizardAvailable().contains(Wizard.WIZARD_PINK));
        assertTrue(game2players.getWizardAvailable().contains(Wizard.WIZARD_BLUE));
        assertTrue(game2players.getWizardAvailable().contains(Wizard.WIZARD_YELLOW));
        assertFalse(game2players.getWizardAvailable().contains(Wizard.WIZARD_GREEN));

        game2players.addPlayer("kek");
        player2=game2players.getPlayer(2);
        boolean check=false;
        try{
            game2players.setWizard(2,Wizard.WIZARD_GREEN);
        }catch (InvalidWizardException e){
            check=true;
        }
        assertTrue(check);

    }

    @Test
    void setReconnectedPlayer() throws ReconnectedException {
        game3players.addPlayer("Daniele");
        player1 = game3players.getPlayer(1);
        game3players.addPlayer("Giuseppe");
        player2 = game3players.getPlayer(2);
        game3players.addPlayer("Giuseppe");
        player3 = game3players.getPlayer(3);

        player1.setPlayerState(PlayerState.DISCONNECTED);
        game3players.setReconnectedPlayer(1);
        assertEquals(game3players.getPlayer(1).getPlayerState(),PlayerState.RECONNECTED);
    }


    @Test
    void verifyWinner() {
        ArrayList<Player> winners = new ArrayList<>();
        game2players.addPlayer("Daniele");
        game2players.addPlayer("Giuseppe");
        for(int i=0; i<6; i++)
            game2players.getPlayers().get(0).getPlance().removeTower();
        winners.add(game2players.getPlayers().get(0));
        assertEquals(winners, game2players.verifyWinner());

        game3players.addPlayer("A");
        game3players.addPlayer("B");
        game3players.addPlayer("C");
        for(int i=0; i<3; i++)
            game3players.getPlayers().get(0).getPlance().removeTower();
        for(int i=0; i<3; i++)
            game3players.getPlayers().get(2).getPlance().removeTower();
        for(int i=0; i<2; i++)
            game3players.getPlayers().get(1).getPlance().removeTower();
        game3players.getPlayers().get(0).getPlance().addProfessor(Professor.BLUE_UNICORN);
        game3players.getPlayers().get(0).getPlance().addProfessor(Professor.RED_DRAGON);
        game3players.getPlayers().get(2).getPlance().addProfessor(Professor.GREEN_FROG);
        game3players.getPlayers().get(2).getPlance().addProfessor(Professor.PINK_FAIRY);
        game3players.getPlayers().get(1).getPlance().addProfessor(Professor.YELLOW_ELF);
        winners.clear();
        winners.add(game3players.getPlayers().get(0));
        winners.add(game3players.getPlayers().get(2));
        assertEquals(winners,game3players.verifyWinner() );
    }
}