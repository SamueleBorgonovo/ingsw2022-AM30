package it.polimi.ingsw.client.View;

import it.polimi.ingsw.client.View.cli.PossibleAction;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.PlayerInterface;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;

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
    public void setPlayers(ArrayList<PlayerInterface> players) {}

    @Override
    public void setBoard(Board board) {

    }

    @Override
    public void print() {

    }

    @Override
    public void printStartGame() {

    }
}
