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

public class GUI extends View {
    public void init(){}

    @Override
    public String chooseNickname(boolean validNickname) {
        return null;
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
    public void chooseAssistant(ArrayList<Assistant> avaiableAssistant) {}

    @Override
    public PossibleAction chooseNextAction(PlayerState playerState) {
        return null;
    }

    @Override
    public void moveStudentToHall(HashMap<Student, Integer> hall) {}

    @Override
    public void moveStudentToIsland (HashMap<Student,Integer> hall,int numOfIslands) {}

    @Override
    public void moveMotherNature(Assistant assistant) {}

    @Override
    public void chooseCloud(int numofClouds) {}

    @Override
    public void useCharacter(ArrayList<Characters> avaiableCharacter, int numOfCoins) {}
}
