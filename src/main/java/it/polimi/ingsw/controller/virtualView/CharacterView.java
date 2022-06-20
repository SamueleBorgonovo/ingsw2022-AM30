package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.board.TypeOfInputCharacter;

import java.io.Serializable;

/**
 * Class CharacterView is used to send the updated characters to the client from the server
 */
public class CharacterView implements Serializable {
    private final int cost;
    private final TypeOfInputCharacter typeOfInputCharacter;
    private final String name;
    private final boolean isUsed;

    /**
     * Method CharacterView sets instances of all the attributes of the character
     * @param cost character's cost
     * @param typeOfInputCharacter character's type of input
     * @param name character's name
     * @param isUsed true if the character has been used in this round
     */
    public CharacterView(int cost, TypeOfInputCharacter typeOfInputCharacter, String name,boolean isUsed) {
        this.cost = cost;
        this.typeOfInputCharacter = typeOfInputCharacter;
        this.name = name;
        this.isUsed=isUsed;
    }

    /**
     * Method getCost returns the character's cost
     * @return the character's cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Method getTypeOfInputCharacter returns the character's type of input
     * @return the character's type of input
     */
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return typeOfInputCharacter;
    }

    /**
     * Method getName returns the character's name
     * @return the character's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method isUsed returns if the character has been used in this round
     * @return true if the character has been used in this round, false otherwise
     */
    public boolean isUsed(){return isUsed;}
}
