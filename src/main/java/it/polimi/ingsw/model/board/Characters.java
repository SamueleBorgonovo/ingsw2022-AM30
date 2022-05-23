package it.polimi.ingsw.model.board;

import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.model.effects.Effect;
import it.polimi.ingsw.model.game.Student;

import java.io.Serializable;

public class Characters{
    private int cost;
    private boolean used = false;
    private final Effect effect;
    private TypeOfInputCharacter typeOfInputCharacter;

    public Characters(Effect effect) {
        cost = effect.getCost();
        this.effect = effect;
        this.typeOfInputCharacter=effect.getTypeOfInputCharacter();
    }

    public int getCost() {
        return cost;
    }

    public boolean isUsed() {
        return used;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setUsed(boolean used) {
        this.used = used;
        cost++;
    }

    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return typeOfInputCharacter;
    }

    public CharacterView getCharacterView(){
        return new CharacterView(this.cost, this.typeOfInputCharacter, this.effect.getName());
    }
}
