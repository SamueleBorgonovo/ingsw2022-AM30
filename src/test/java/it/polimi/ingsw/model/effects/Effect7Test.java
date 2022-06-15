package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Effect7Test {

   Effect effect7 = new Effect7();

   @Test
    void getterTest(){
        assertEquals("JESTER",effect7.getName());
        assertEquals(1,effect7.getCost());
        assertEquals(TypeOfInputCharacter.EFFECT7INPUT,effect7.getTypeOfInputCharacter());

        Game game = new Game(GameMode.EXPERTMODE,2);
        effect7.initialize(game);
        boolean check=false;
        if(game.getEffectHandler().getEffect7students().size()>0)
            check=true;
        assertTrue(check);
   }

   @Test
    void effectTest() {
       Game game = new Game(GameMode.EXPERTMODE,2);
       game.addPlayer("tom");
       game.getPlayer(1).setPlayerState(PlayerState.MOTHERNATUREPHASE);
       try {
           effect7.effect(game, 1);
       } catch (InvalidStopException e) {}
       assertEquals(PlayerState.CHARACTHERSTUDENTSPHASE,game.getPlayer(1).getPlayerState());

       ArrayList<Student> students = new ArrayList<>();
       students.add(Student.RED);
       students.add(Student.BLUE);
       students.add(Student.GREEN);
       game.getEffectHandler().setStudentschoose(students);
       boolean check=false;
        try{
            effect7.secondPartEffect(game,1);
        }catch (InvalidStudentEffectException e){
            check=true;
        }
        assertTrue(check);

        students.add(Student.YELLOW);
        ArrayList<Student> studentsoncard = new ArrayList<>();
        studentsoncard.add(Student.RED);
        studentsoncard.add(Student.BLUE);
        game.getEffectHandler().setEffect7students(studentsoncard);
        for(Student student : game.getPlayer(1).getPlance().getEntrance())
            game.getPlayer(1).getPlance().removeStudentEntrance(student);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.GREEN);
        game.getPlayer(1).getPlance().addStudentEntrance(Student.YELLOW);
        try{
            effect7.secondPartEffect(game,1);
        } catch (InvalidStudentEffectException e) {}
        assertEquals(PlayerState.MOTHERNATUREPHASE,game.getPlayer(1).getPlayerState());
        assertTrue(game.getEffectHandler().getEffect7students().contains(Student.GREEN));
        assertTrue(game.getEffectHandler().getEffect7students().contains(Student.YELLOW));
        assertFalse(game.getEffectHandler().getEffect7students().contains(Student.RED));
        assertFalse(game.getEffectHandler().getEffect7students().contains(Student.BLUE));

        assertTrue(game.getPlayer(1).getPlance().getEntrance().contains(Student.RED));
       assertTrue(game.getPlayer(1).getPlance().getEntrance().contains(Student.BLUE));
       assertFalse(game.getPlayer(1).getPlance().getEntrance().contains(Student.GREEN));
       assertFalse(game.getPlayer(1).getPlance().getEntrance().contains(Student.YELLOW));

   }

}