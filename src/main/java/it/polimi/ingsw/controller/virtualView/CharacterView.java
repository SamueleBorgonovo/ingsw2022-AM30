package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.board.TypeOfInputCharacter;

import java.io.Serializable;

public class CharacterView implements Serializable {
    private int cost;
    private TypeOfInputCharacter typeOfInputCharacter;
    private String name;


    public CharacterView(int cost, TypeOfInputCharacter typeOfInputCharacter, String name) {
        this.cost = cost;
        this.typeOfInputCharacter = typeOfInputCharacter;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return typeOfInputCharacter;
    }

    public String getName() {
        return name;
    }
}
