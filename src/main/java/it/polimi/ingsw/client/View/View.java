package it.polimi.ingsw.client.View;

import it.polimi.ingsw.controller.virtualView.BoardView;
import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.controller.virtualView.PlayerView;

import it.polimi.ingsw.model.game.EffectHandler;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Wizard;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Assistant;


import java.util.ArrayList;

/**
 * Interface of the player view
 */
public interface View {
    void chooseNickname(boolean validNickname, boolean reconnect);

    void chooseSettings();

    void chooseWizard(ArrayList<Wizard> avaiableWizards);

    void chooseAssistant();

    void moveMotherNature();

    void chooseCloud();

    void setPlayers(ArrayList<PlayerView> players);

    void setBoard(BoardView board);

    void setEffectHandler(EffectHandler effectHandler);

    void printStartGame(boolean restart);

    void printAssistantChosen(String nick, Assistant assistant);

    void printTurn(String nick,boolean isAssistantPhase);

     void printCharacterChosen(String nick, CharacterView character);

     void printCloudChosen(String nick, int cloudID);

     void printStudentToHall(String nick, Student student);

     void printStudentToIsland(String nick, Student student, int IslandID);

     void printMotherNatureMovement(String nick, int islandID);

     void printPlayerDisconnection(String nick);

     void printPlayerConnection(String nick,boolean reconnect);

     void printInvalidAssistant();

     void printInvalidCloud();

     void printInvalidIsland();

     void printInvalidStudent();

     void printInvalidTurn();

     void printInvalidStop();

     void printInvalidWizard();

     void printWinnerInstantly(ArrayList<String> nickname,int type);

     void printWinnerEndRound(ArrayList<String> nickname, int type);

     void printWaitingForPlayers(boolean lobby);

     void printGameEndedTimeout();

     void printWinClose();

     void printConnectionClosed(boolean timeout);

     void correctlyConnected();

     void nextMove(PlayerState playerState);

     void setExit();

     void displayGame();

     void noGameReconnect();
}
