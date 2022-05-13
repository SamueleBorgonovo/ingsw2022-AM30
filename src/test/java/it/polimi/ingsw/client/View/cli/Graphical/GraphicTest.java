package it.polimi.ingsw.client.View.cli.Graphical;

import it.polimi.ingsw.model.board.Archipelago;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Professor;
import it.polimi.ingsw.model.player.Wizard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GraphicTest {
    Game game = new Game(GameMode.EXPERTMODE,3, null);
    Graphic graphic = new Graphic();
    Player player1;
    Player player2;
    Player player3;
    ArrayList<Player> players = new ArrayList<>();
    Archipelago archipelago;

    @BeforeEach
    void init(){
        game.addPlayer("Giuseppe");
        game.addPlayer("Daniele");
        game.addPlayer("Samuele");
        player1 = game.getPlayer(1);
        player2 = game.getPlayer(2);
        player3 = game.getPlayer(3);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        archipelago = game.getBoard().getArchipelago();
    }

    @Test
    void printLogo() {
        graphic.printLogo();
    }

    @Test
    void printArchipelago() {
        graphic.printArchipelago(archipelago);
        archipelago.getSingleIsland(3).setTowerColor(Tower.BLACK);
        archipelago.getSingleIsland(3).addStudent(Student.BLUE);
        graphic.printArchipelago(archipelago);
        archipelago.getSingleIsland(4).setTowerColor(Tower.BLACK);
        archipelago.getSingleIsland(4).addStudent(Student.BLUE);
        graphic.printArchipelago(archipelago);
        archipelago.mergeIslands(3,4);
        graphic.printArchipelago(archipelago);
        archipelago.getSingleIsland(4).setTowerColor(Tower.BLACK);
        archipelago.getSingleIsland(4).addStudent(Student.BLUE);
        archipelago.getSingleIsland(4).setStop(true);
        graphic.printArchipelago(archipelago);
        archipelago.mergeIslands(3,4);
        graphic.printArchipelago(archipelago);
        archipelago.mergeIslands(3,4);
        graphic.printArchipelago(archipelago);
    }

    @Test
    void printPlances() {
        graphic.printPlances(players);
        for(Student student : Student.values())
            player1.getPlance().addStudentHall(student);
        player1.getPlance().addStudentHall(Student.YELLOW);
        player1.getPlance().addStudentHall(Student.RED);
        player1.getPlance().addStudentHall(Student.BLUE);
        player1.getPlance().addStudentHall(Student.BLUE);
        player1.getPlance().addProfessor(Professor.BLUE_UNICORN);
        player1.getPlance().removeStudentEntrance(player1.getPlance().getEntrance().get(1));
        player1.addCoins();
        player1.getPlance().removeTower();
        graphic.printPlances(players);
    }

    @Test
    void printAssistants() {
        graphic.printAssistants(player1.getAssistantCards(), Wizard.WIZARD_GREEN);
        player1.removeAssistant(Assistant.EAGLE);
        graphic.printAssistants(player1.getAssistantCards(), Wizard.WIZARD_YELLOW);
        player1.removeAssistant(Assistant.OSTRICH);
        graphic.printAssistants(player1.getAssistantCards(), Wizard.WIZARD_PINK);
        player1.removeAssistant(Assistant.DOG);
        graphic.printAssistants(player1.getAssistantCards(), Wizard.WIZARD_BLUE);
    }

    @Test
    void printClouds() {
        graphic.printClouds(game.getBoard().getClouds());
        game.getBoard().getClouds().get(1).setChoosen(true);
        graphic.printClouds(game.getBoard().getClouds());
        game.getBoard().getClouds().get(0).setChoosen(true);
        graphic.printClouds(game.getBoard().getClouds());
    }
}