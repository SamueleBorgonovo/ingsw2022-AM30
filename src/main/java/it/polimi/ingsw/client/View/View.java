package it.polimi.ingsw.client.View;

import it.polimi.ingsw.client.View.cli.PossibleAction;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.PlayerInterface;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;

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

    public abstract void setPlayers(ArrayList<PlayerInterface> players);

    public abstract void setBoard(Board board);

    public abstract void print();

    public abstract void printStartGame();
}
