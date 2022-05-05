package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.GameState;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Wizard;

import java.util.ArrayList;

public interface GameInterface {

    public int addPlayer(String nickname, Wizard wizard);

    public GameState getState();

    public int getNumOfPlayers();

    public GameMode getGameMode();

    public void selectCloud(int playerID, int cloudID) throws InvalidTurnException, InvalidCloudException;

    public void moveStudentToHall(int playerID, Student student) throws InvalidTurnException, InvalidStudentException;

    public void moveStudentToIsland(int playerID, int islandID, Student student) throws InvalidTurnException, InvalidStudentException;

    public void useAssistant(int playerID, Assistant assistant) throws InvalidAssistantException, InvalidTurnException;

    public void moveMotherNature(int playerID, int numberOfMovement) throws InvalidTurnException, InvalidValueException;

    public void useCharacter(int playerID, Characters character)  throws InvalidStopException, InvalidTurnException, OutOfCoinsException, InvalidCharacterException;

    public void CharacterIslandPhase(int playerID,int islandID) throws InvalidTurnException, InvalidIslandException, InvalidStudentEffectException;

    public void CharacterStudentsPhase(int playerID, ArrayList<Student> students) throws InvalidTurnException, InvalidStudentEffectException;

}
