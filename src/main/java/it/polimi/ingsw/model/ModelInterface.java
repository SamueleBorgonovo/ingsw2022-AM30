package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.game.GameState;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Wizard;

import java.util.ArrayList;

public interface ModelInterface {

    public void addPlayer(String nickname, Wizard wizard);

    public void setGameID(int gameID);

    public GameState getState();

    public void selectCloud(int playerID, int cloudID) throws InvalidTurnException, WrongCloudException;

    public void moveStudentToHall(int playerID, Student student) throws InvalidTurnException, WrongStudentException;

    public void moveStudentToIsland(int playerID, int islandID, Student student) throws InvalidTurnException, WrongStudentException;

    public void useAssistant(int playerID, Assistant assistant) throws WrongAssistantException, InvalidTurnException;

    public void moveMotherNature(int playerID, int numberOfMovement) throws InvalidTurnException, WrongValueException;

    public void useCharacter(int playerID, Characters character)  throws InvalidStopException, InvalidTurnException, OutOfCoinsException, InvalidCharacterException;

    public void CharacterIslandPhase(int playerID,int islandID) throws InvalidTurnException, WrongIslandException, WrongStudentEffectException;

    public void CharacterStudentsPhase(int playerID, ArrayList<Student> students) throws InvalidTurnException, WrongStudentEffectException;

}
