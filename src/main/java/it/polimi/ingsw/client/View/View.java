package it.polimi.ingsw.client.View;

import it.polimi.ingsw.client.View.cli.PossibleAction;
import it.polimi.ingsw.controller.virtualView.BoardView;
import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.controller.virtualView.PlayerView;


import it.polimi.ingsw.model.game.EffectHandler;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Wizard;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Assistant;


import java.util.ArrayList;

public interface View {
    public void chooseNickname(boolean validNickname, boolean reconnect);

    public boolean tryToReconnect();

    public void chooseSettings();

    public void chooseWizard(ArrayList<Wizard> avaiableWizards);

    public void chooseAssistant();

    public PossibleAction chooseNextAction(PlayerState playerState);

    public void moveStudentToHall();

    public void moveStudentToIsland();

    public void moveMotherNature();

    public void chooseCloud();

    public void useCharacter(PlayerState playerState);

    public void inputStudentCharacter();

    public void inputIslandCharacter();

    public void setPlayers(ArrayList<PlayerView> players);

    public void setBoard(BoardView board);

    public void setEffectHandler(EffectHandler effectHandler);

    public void print();

    public void printStartGame();

    public void winner(ArrayList<String> nicknamesWinner);

    public void printAssistantChosen(String nick, Assistant assistant);

    public void printTurn(String nick);

    public void printCharacterChosen(String nick, CharacterView character);

    public void printCloudChosen(String nick, int cloudID);

    public void printStudentToHall(String nick, Student student);

    public void printStudentToIsland(String nick, Student student, int IslandID);

    public void printMotherNatureMovement(String nick, int islandID);

    public void printPlayerDisconnection(String nick);

    public void printPlayerConnection(String nick,boolean reconnect);

    public void printInvalidAssistant();

    public void printInvalidCloud();

    public void printInvalidIsland();

    public void printInvalidStudent();

    public void printInvalidTurn();

    public void printInvalidStop();

    public void printInvalidWizard();

    public void printWinnerInstantly(ArrayList<String> nickname,int type);

    public void printWinnerEndRound(ArrayList<String> nickname, int type);

    public void printWaitingForPlayers(boolean lobby);

    public void printGameEndedTimeout();

    public void printWinClose();

    public void printConnectionClosed(boolean timeout);

    public void correctlyConnected();

    public void nextMove(PlayerState playerState);
}
