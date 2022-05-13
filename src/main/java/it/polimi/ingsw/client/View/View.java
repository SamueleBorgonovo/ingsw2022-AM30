package it.polimi.ingsw.client.View;

import it.polimi.ingsw.client.View.cli.PossibleAction;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class View {
    public abstract String chooseNickname(boolean validNickname);

    public abstract boolean tryToReconnect();

    public abstract GameMode chooseGameMode();

    public abstract int chooseNumberOfPlayers();

    public abstract void chooseWizard(ArrayList<Wizard> avaiableWizards);

    public abstract void chooseAssistant(ArrayList<Assistant> avaiableAssistant);

    public abstract PossibleAction chooseNextAction(PlayerState playerState);

    public abstract void moveStudentToHall(HashMap<Student,Integer> hall);

    public abstract void moveStudentToIsland(HashMap<Student,Integer> hall, int numOfIslands);

    public abstract void moveMotherNature(Assistant assistant);

    public abstract void chooseCloud(int numOfClouds);

    public abstract void useCharacter(ArrayList<Characters> avaiableCharacter, int numOfCoins);
}
