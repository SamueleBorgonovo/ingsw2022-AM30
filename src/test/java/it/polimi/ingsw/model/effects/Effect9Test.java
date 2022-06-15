package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Effect9Test {

    Effect effect9 = new Effect9();

    @Test
    void getTests() {
        assertEquals(3,effect9.getCost());
        assertEquals("FUNGARO",effect9.getName());
    }

    @Test
    void effect() {
        Game game = new Game(GameMode.EXPERTMODE,2);
        game.addPlayer("tom");
        game.getPlayer(1).setPlayerState(PlayerState.MOTHERNATUREPHASE);
        try{
            effect9.effect(game,1);
        }catch (InvalidStopException e){}
        assertEquals(PlayerState.CHARACTHERSTUDENTSPHASE,game.getPlayer(1).getPlayerState());

        ArrayList<Student> student= new ArrayList<>();
        student.add(Student.RED);
        game.getEffectHandler().setStudentschoose(student);
        try{
            effect9.secondPartEffect(game,1);
        }catch (InvalidStudentEffectException e){}
        assertTrue(game.getEffectHandler().isNocolor());
        assertEquals(Student.RED,game.getEffectHandler().getStudent());
        assertEquals(PlayerState.MOTHERNATUREPHASE,game.getPlayer(1).getPlayerState());


    }
}