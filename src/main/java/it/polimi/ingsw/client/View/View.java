package it.polimi.ingsw.client.View;

import it.polimi.ingsw.client.View.cli.PossibleAction;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.board.Cloud;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.*;

import java.util.ArrayList;

public abstract class View {
    public abstract void chooseNickname(boolean validNickname, boolean reconnect);

    public abstract boolean tryToReconnect();

    public abstract GameMode chooseGameMode();

    public abstract int chooseNumberOfPlayers();

    public abstract void chooseWizard(ArrayList<Wizard> avaiableWizards);

    public abstract void chooseAssistant();

    public abstract PossibleAction chooseNextAction(PlayerState playerState);

    public abstract void moveStudentToHall();

    public abstract void moveStudentToIsland();

    public abstract void moveMotherNature();

    public abstract void chooseCloud();

    public abstract void useCharacter();

    public abstract void setPlayers(ArrayList<Player> players);

    public abstract void setBoard(Board board);

    public abstract void print();

    public abstract void printStartGame();

    public abstract void winner(String nick);

    public abstract void printAssistantChosen(String nick, Assistant assistant);

    public abstract void printTurn(String nick);

    public abstract void printCharacterChosen(String nick, Characters character);

    public abstract void printCloudChosen(String nick, int cloudID);

    public abstract void printStudentToHall(String nick, Student student);

    public abstract void printStudentToIsland(String nick, Student student, int IslandID);

    public abstract void printMotherNatureMovement(String nick, int islandID);

    public abstract void printPlayerDisconnection(String nick);

    public abstract void printPlayerConnection(String nick,boolean reconnect);

    public abstract void printInvalidAssistant();

    public abstract void printInvalidCloud();

    public abstract void printInvalidIsland();

    public abstract void printInvalidStudent();

    public abstract void printInvalidTurn();

    public abstract void printInvalidStop();

    public abstract void printInvalidWizard();
}
