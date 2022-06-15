package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Effect12Test {

    Effect effect12 = new Effect12();
   @Test
    void getName(){
       assertEquals("PICAROON",effect12.getName());
   }

   @Test
    void effect(){
       Game game = new Game(GameMode.EXPERTMODE,2);
       game.addPlayer("tom");
       game.getPlayer(1).setPlayerState(PlayerState.MOTHERNATUREPHASE);
       game.addPlayer("sam");

       try{
           effect12.effect(game,1);
       } catch (InvalidStopException e) {}
       assertEquals(PlayerState.CHARACTHERSTUDENTSPHASE,game.getPlayer(1).getPlayerState());

       game.getPlayer(1).getPlance().addStudentHall(Student.RED);
       game.getPlayer(1).getPlance().addStudentHall(Student.RED);
       game.getPlayer(2).getPlance().addStudentHall(Student.RED);
       game.getPlayer(2).getPlance().addStudentHall(Student.RED);
       game.getPlayer(2).getPlance().addStudentHall(Student.RED);

       ArrayList<Student> studentchoose = new ArrayList<>();
       studentchoose.add(Student.RED);
       game.getEffectHandler().setStudentschoose(studentchoose);
       try{
           effect12.secondPartEffect(game,1);
       } catch (InvalidStudentEffectException e) {}
       assertEquals(PlayerState.MOTHERNATUREPHASE,game.getPlayer(1).getPlayerState());
       assertEquals(0,game.getPlayer(1).getPlance().getNumberOfStudentHall(Student.RED));
       assertEquals(0,game.getPlayer(2).getPlance().getNumberOfStudentHall(Student.RED));

       game.getPlayer(1).getPlance().addStudentHall(Student.RED);
       game.getPlayer(1).getPlance().addStudentHall(Student.RED);
       game.getPlayer(1).getPlance().addStudentHall(Student.RED);
       game.getPlayer(1).getPlance().addStudentHall(Student.RED);

       game.getPlayer(1).setPlayerState(PlayerState.CHARACTHERSTUDENTSPHASE);
       try{
           effect12.secondPartEffect(game,1);
       }catch (InvalidStudentEffectException e) {}
       assertEquals(PlayerState.MOTHERNATUREPHASE,game.getPlayer(1).getPlayerState());
       assertEquals(1,game.getPlayer(1).getPlance().getNumberOfStudentHall(Student.RED));
       assertEquals(0,game.getPlayer(2).getPlance().getNumberOfStudentHall(Student.RED));

   }
}