package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.client.View.cli.PossibleAction;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.board.Cloud;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.*;

import java.util.ArrayList;

public class GUI extends View {
    public void init(){}


    @Override
    public void chooseNickname(boolean validNickname, boolean reconnect) {

    }

    @Override
    public boolean tryToReconnect() {
        return false;
    }

    @Override
    public GameMode chooseGameMode() {
        return null;
    }

    @Override
    public int chooseNumberOfPlayers() {
        return 0;
    }

    @Override
    public void chooseWizard(ArrayList<Wizard> avaiableWizards) {}

    @Override
    public void chooseAssistant() {}

    @Override
    public PossibleAction chooseNextAction(PlayerState playerState) {
        return null;
    }

    @Override
    public void moveStudentToHall() {}

    @Override
    public void moveStudentToIsland () {}

    @Override
    public void moveMotherNature() {}

    @Override
    public void chooseCloud() {}

    @Override
    public void useCharacter() {}

    @Override
    public void setPlayers(ArrayList<Player> players) {}

    @Override
    public void setBoard(Board board) {

    }

    @Override
    public void print() {

    }

    @Override
    public void printStartGame() {

    }

    @Override
    public void winner(String nick) {

    }

    @Override
    public void printAssistantChosen(String nick, Assistant assistant) {

    }

    @Override
    public void printTurn(String nick) {

    }

    @Override
    public void printCharacterChosen(String nick, Characters character) {

    }

    @Override
    public void printCloudChosen(String nick, int cloudID) {

    }

    @Override
    public void printStudentToHall(String nick, Student student) {

    }

    @Override
    public void printStudentToIsland(String nick, Student student, int IslandID) {

    }

    @Override
    public void printMotherNatureMovement(String nick, int islandID) {

    }
}
