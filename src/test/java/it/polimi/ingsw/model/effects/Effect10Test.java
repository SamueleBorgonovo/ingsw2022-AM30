package it.polimi.ingsw.model.effects;

import com.sun.prism.GraphicsResource;
import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Effect10Test {

    Effect effect10 = new Effect10();

   @Test
    void getTests(){
       assertEquals("MINSTREL",effect10.getName());
       assertEquals(1,effect10.getCost());
   }

   @Test
    void effect(){
       Game game = new Game(GameMode.EXPERTMODE,2);
       game.addPlayer("tom");
       game.getPlayer(1).setPlayerState(PlayerState.MOTHERNATUREPHASE);
       try{
           effect10.effect(game,1);
       }catch (InvalidStopException e){}
       assertEquals(PlayerState.CHARACTHERSTUDENTSPHASE,game.getPlayer(1).getPlayerState());

       ArrayList<Student> students = new ArrayList<>();
       students.add(Student.RED);
       students.add(Student.BLUE);
       students.add(Student.YELLOW);
       game.getEffectHandler().setStudentschoose(students);
       boolean check=false;
       try{
           effect10.secondPartEffect(game,1);
       } catch (InvalidStudentEffectException e) {
          check=true;
       }
       assertTrue(check);

       students.add(Student.GREEN);

       for(Student student : game.getPlayer(1).getPlance().getEntrance())
           game.getPlayer(1).getPlance().removeStudentEntrance(student);
       game.getPlayer(1).getPlance().addStudentEntrance(Student.RED);
       game.getPlayer(1).getPlance().addStudentEntrance(Student.BLUE);

       game.getPlayer(1).getPlance().addStudentHall(Student.YELLOW);
       game.getPlayer(1).getPlance().addStudentHall(Student.GREEN);

       try{
           effect10.secondPartEffect(game,1);
       }catch (InvalidStudentEffectException e){}
       assertEquals(PlayerState.MOTHERNATUREPHASE,game.getPlayer(1).getPlayerState());

       assertTrue(game.getPlayer(1).getPlance().getEntrance().contains(Student.YELLOW));
       assertTrue(game.getPlayer(1).getPlance().getEntrance().contains(Student.GREEN));
       assertFalse(game.getPlayer(1).getPlance().getEntrance().contains(Student.RED));
       assertFalse(game.getPlayer(1).getPlance().getEntrance().contains(Student.BLUE));

       assertEquals(1,game.getPlayer(1).getPlance().getNumberOfStudentHall(Student.RED));
       assertEquals(1,game.getPlayer(1).getPlance().getNumberOfStudentHall(Student.BLUE));
       assertEquals(0,game.getPlayer(1).getPlance().getNumberOfStudentHall(Student.YELLOW));
       assertEquals(0,game.getPlayer(1).getPlance().getNumberOfStudentHall(Student.GREEN));


   }
}