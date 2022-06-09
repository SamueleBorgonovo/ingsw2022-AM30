package it.polimi.ingsw.model.board;

import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.model.effects.Effect;
import it.polimi.ingsw.model.effects.Effect1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharactersTest {
    Effect effect=new Effect1();
    Characters character = new Characters(effect);

    @Test
    void getCost() {
        assertEquals(1,character.getCost());
    }

    @Test
    void isUsed() {
        character.setUsed(true);
        assertTrue(character.isUsed());
        assertEquals(2,character.getCost());
    }

    @Test
    void getEffect() {
        Effect tempeffect;
        tempeffect= character.getEffect();
        assertEquals("MONK",tempeffect.getName());
    }

    @Test
    void setUsed() {
        character.setUsed(true);
        assertTrue(character.isUsed());
        assertEquals(2,character.getCost());
    }

    @Test
    void getTypeOfInputCharacter(){
        TypeOfInputCharacter type=character.getTypeOfInputCharacter();
        assertEquals(TypeOfInputCharacter.EFFECT1INPUT,type);
    }

    @Test
    void getCharacterView(){
        character.setUsed(true);
        CharacterView view=character.getCharacterView();
        assertEquals(2,view.getCost());
        assertEquals(TypeOfInputCharacter.EFFECT1INPUT,view.getTypeOfInputCharacter());
        assertEquals("MONK",view.getName());
        assertTrue(view.isUsed());
    }
}