package it.polimi.ingsw.client.View;

import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class View {
    public abstract String chooseNickname(boolean validNickname);

    public abstract GameMode chooseGameMode();

    public abstract int chooseNumberOfPlayers();

    public abstract Wizard chooseWizard(ArrayList<Wizard> avaiableWizards);

    public abstract Assistant chooseAssistant(ArrayList<Assistant> avaiableAssistant);

    public abstract PlayerState chooseNextAction(ArrayList<PlayerState> possibleAction);

    public abstract Student chooseStudentToMove(HashMap<Student,Integer> hall);
}
