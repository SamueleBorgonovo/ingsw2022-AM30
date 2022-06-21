package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;

import java.util.ArrayList;

/**
 * Class used to exchange up to two students in the player's hall with the same
 * number in the player's entrance
 */
public class Effect10 extends Effect{
    private PlayerState prevPlayerState;

    /**
     * Method used to get the cost of the Effect
     * @return the cost of the Effect
     */
    @Override
    public int getCost(){ return 1;}

    /**
     * Method used to get the name of the Effect
     * @return the name of the Effect
     */
    @Override
    public String getName(){ return "MINSTREL";}

    /**
     * Method used to save the player's current state and change it so that
     * he can choose students
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStopException if the four stops of Effect5 have already been used
     */
    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        prevPlayerState=game.getPlayer(playerID).getPlayerState();
        game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERSTUDENTSPHASE);
    }

    /**
     * Method used by Effect1, Effect7 and Effect11 to initialize the
     * corresponding Array in EffectHandler class
     * @param game main class where there are EffectHandler class and Board class
     */
    @Override
    public void initialize(Game game) {}

    /**
     * Method used to exchange up to two students in the player's hall with the same number
     * in the entrance. Then the player's previous state is restored
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStudentEffectException if the chosen students are not valid
     */
    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {

        if(game.getEffectHandler().getStudentschoose().size()>=1 && game.getEffectHandler().getStudentschoose().size()<=4 && game.getEffectHandler().getStudentschoose().size()%2==0){
            ArrayList<Student> toHall = new ArrayList<>();
            ArrayList<Student> toEntrance = new ArrayList<>();

            //Expect first half part of students from entrance to hall, other part from hall to entrance
            for(int count=0;count<game.getEffectHandler().getStudentschoose().size()/2;count++)
                toHall.add(game.getEffectHandler().getStudentschoose().get(count));
            for(int count=game.getEffectHandler().getStudentschoose().size()/2;count<game.getEffectHandler().getStudentschoose().size();count++)
                toEntrance.add(game.getEffectHandler().getStudentschoose().get(count));
            for (Student student : toEntrance)
                game.getPlayer(playerID).getPlance().removeStudentFromHall(student);
            for (Student student : toHall)
                game.getPlayer(playerID).getPlance().removeStudentEntrance(student);
            for (Student student : toEntrance)
                game.getPlayer(playerID).getPlance().addStudentEntrance(student);
            for (Student student : toHall)
                game.getPlayer(playerID).getPlance().addStudentHall(student);
            game.setCharacterInUse(null);
            game.getPlayer(playerID).setPlayerState(prevPlayerState);
        } else
            throw new InvalidStudentEffectException();
    }

    /**
     * Method used to get the type of input required after using a character
     * @return the type of input required after using a character
     */
    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return TypeOfInputCharacter.EFFECT10INPUT;
    }

}
