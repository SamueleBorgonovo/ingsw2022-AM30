package it.polimi.ingsw.model.board;

import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.model.effects.Effect;

/**
 * Class used to handle the action related to the character cards
 */
public class Characters{
    private int cost;
    private boolean used;
    private final Effect effect;
    private final TypeOfInputCharacter typeOfInputCharacter;

    /**
     * Constructor of the Characters Class
     * @param effect the Effect Class associated with the Character
     */
    public Characters(Effect effect) {
        cost = effect.getCost();
        used = false;
        this.effect = effect;
        typeOfInputCharacter = effect.getTypeOfInputCharacter();
    }

    /**
     * Method used to get the cost of the Character
     * @return the cost of the Character
     */
    public int getCost() {
        return cost;
    }

    /**
     * Method used to get if the Character has already been used
     * @return if the Character has already been used
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Method used to get the Effect Class of the Character
     * @return the Effect Class of the Character
     */
    public Effect getEffect() {
        return effect;
    }

    /**
     * Method used to set if the Character has already been used
     * @param used the value to set
     */
    public void setUsed(boolean used) {
        if(!this.used) {
            this.used = used;
            if(cost== effect.getCost())
                cost++;
        }
    }

    /**
     * Method used to get the type of input required after using a character
     * @return the type of input required after using a character
     */
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return typeOfInputCharacter;
    }

    /**
     * Method used to create a simplified and serializable version of Characters Class
     * which can be sent to the client
     * @return the simplified and serializable class
     */
    public CharacterView getCharacterView(){
        return new CharacterView(cost, typeOfInputCharacter, effect.getName(), used);
    }
}
