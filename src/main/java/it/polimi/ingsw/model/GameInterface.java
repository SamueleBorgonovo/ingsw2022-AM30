package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.game.EffectHandler;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.GameState;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Wizard;

import java.util.ArrayList;

/**
 * Interface used to control game's methods calls
 */
public interface GameInterface {

    int addPlayer(String nickname);

    ArrayList<Wizard> getWizardAvailable();

    void setWizard(int playerID,Wizard wizard) throws InvalidWizardException;

    GameState getState();

    int getNumOfPlayers();

    GameMode getGameMode();

    boolean setDisconnectPlayer(int playerID);

    void setReconnectedPlayer(int playerid) throws ReconnectedException;

    boolean selectCloud(int playerID, int cloudID) throws InvalidTurnException, InvalidCloudException;

    boolean checkPlayerState(int playerid);

    void moveStudentToHall(int playerID, Student student) throws InvalidTurnException, InvalidStudentException;

    void moveStudentToIsland(int playerID, int islandID, Student student) throws InvalidTurnException, InvalidStudentException;

    void useAssistant(int playerID, Assistant assistant) throws InvalidAssistantException, InvalidTurnException;

    void moveMotherNature(int playerID, int numberOfMovement) throws InvalidTurnException, InvalidValueException;

    void useCharacter(int playerID, Characters character)  throws InvalidStopException, InvalidTurnException, OutOfCoinsException, InvalidCharacterException;

    void CharacterIslandPhase(int playerID,int islandID) throws InvalidTurnException, InvalidIslandException, InvalidStudentEffectException;

    void CharacterStudentsPhase(int playerID, ArrayList<Student> students) throws InvalidTurnException, InvalidStudentEffectException;

    ArrayList<Player> getPlayers();

    Board getBoard();

    void startGame();

    int getMotherNatureIsland();

    ArrayList<Player> getPlayerorder();

    ArrayList<PlayerView> getPlayersView();

    Characters searchCharacter(CharacterView characterView);

    int winnerInstantly();

    int winnerEndRound();

    ArrayList<Player> verifyWinner();

    int getNumPlayerDisconnected();

    EffectHandler getEffectHandler();
}
