package it.polimi.ingsw.model.effects;
import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.exceptions.InvalidStudentException;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.game.*;
import it.polimi.ingsw.model.player.PlayerState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Effect1Test {

    Effect effect1 = new Effect1();

    @Test
    public void getCostTest(){
        assertEquals(1,effect1.getCost());
    }

    @Test
    public void inizializeTest() {
        int i = 0;
        Game game;
        do {
            game = new Game(GameMode.EXPERTMODE, 2);
            for (Characters characters : game.getBoard().getCharacters())
                if(characters.getEffect().getClass().equals(effect1.getClass()))
                    i = 1;
        } while (i != 1);
        effect1.initialize(game);
        int var=1;
        if(game.getEffectHandler().getEffect1students().size()!=4)
            var=-1;

        assertEquals(1,var);

    }

    @Test
    void effect(){
        Game game = new Game(GameMode.EXPERTMODE,2);
        game.addPlayer("tom");
        game.getPlayer(1).setPlayerState(PlayerState.MOTHERNATUREPHASE);
        try {
            effect1.effect(game, 1);
        }catch (InvalidStopException ignored){}
        assertEquals(PlayerState.CHARACTHERSTUDENTSPHASE,game.getPlayer(1).getPlayerState());

        try {
            effect1.secondPartEffect(game, 1);
        }catch (InvalidStudentEffectException e){}
        assertEquals(PlayerState.CHARACTHERISLANDPHASE,game.getPlayer(1).getPlayerState());

        ArrayList<Student> students = new ArrayList<>();
        students.add(Student.RED);
        game.getEffectHandler().setStudentschoose(students);
        ArrayList<Student> stud = new ArrayList<>();
        stud.clear();
        stud.add(Student.BLUE);
        game.getEffectHandler().setEffect1students(stud);
        boolean check=false;
        try{
            effect1.secondPartEffect(game,1);
        }catch (InvalidStudentEffectException e){
            check=true;
        }
        assertTrue(check);

        students.add(Student.RED);
        students.add(Student.BLUE);
        students.add(Student.YELLOW);
        game.getEffectHandler().setEffect1students(students);
        game.getEffectHandler().setIslandIDchoose(1);
        try {
            effect1.secondPartEffect(game, 1);
        }catch (InvalidStudentEffectException e){}

        assertEquals(PlayerState.MOTHERNATUREPHASE,game.getPlayer(1).getPlayerState());

    }
}