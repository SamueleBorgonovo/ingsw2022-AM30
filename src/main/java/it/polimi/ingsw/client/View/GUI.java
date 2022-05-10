package it.polimi.ingsw.client.View;
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
    public GameMode chooseGameMode() {
        return null;
    }

    @Override
    public int chooseNumberOfPlayers() {
        return 0;
    }

    @Override
    public Wizard chooseWizard(ArrayList<Wizard> avaiableWizards) {
        return null;
    }

    @Override
    public Assistant chooseAssistant(ArrayList<Assistant> avaiableAssistant) {
    return null;
    }

    @Override
    public PlayerState chooseNextAction(ArrayList<PlayerState> possibleAction) {
        return null;
    }

    @Override
    public Student chooseStudentToMove(HashMap<Student, Integer> hall) {
        return null;
    }
}
