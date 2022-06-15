package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.messages.toClient.InvalidMoveMessages.InvalidStudentEffectMessage;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Effect11Test {

    Effect effect11 = new Effect11();
  @Test
    void effect(){
      Game game = new Game(GameMode.EXPERTMODE,2);
      game.addPlayer("tom");
      game.getPlayer(1).setPlayerState(PlayerState.MOTHERNATUREPHASE);

      try{
          effect11.effect(game,1);
      }catch (InvalidStopException e){}
      assertEquals(PlayerState.CHARACTHERSTUDENTSPHASE,game.getPlayer(1).getPlayerState());

      ArrayList<Student> studentsoncard = new ArrayList<>();
      studentsoncard.add(Student.RED);
      studentsoncard.add(Student.BLUE);
      studentsoncard.add(Student.YELLOW);
      studentsoncard.add(Student.GREEN);
      game.getEffectHandler().setEffect11students(studentsoncard);

      ArrayList<Student> students = new ArrayList<>();
      students.add(Student.PINK);
      game.getEffectHandler().setStudentschoose(students);
      boolean check=false;
      try{
          effect11.secondPartEffect(game,1);
      }catch (InvalidStudentEffectException e){
          check=true;
      }
      assertTrue(check);

      students.remove(Student.PINK);
      students.add(Student.RED);
      try{
          effect11.secondPartEffect(game,1);
      }catch (InvalidStudentEffectException e){}

      assertEquals(PlayerState.MOTHERNATUREPHASE,game.getPlayer(1).getPlayerState());
      assertEquals(4,game.getEffectHandler().getEffect11students().size());
      assertEquals(1,game.getPlayer(1).getPlance().getNumberOfStudentHall(Student.RED));

  }

  @Test
    void getName(){
      assertEquals("PRINCESS",effect11.getName());
  }
}