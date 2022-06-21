package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;

import java.util.ArrayList;

/**
 * Class used to exchange up to three students of this effect with as many
 * students present in the player's entrance
 */
public class Effect7 extends Effect{
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
    public String getName(){ return "JESTER";}

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
     * Method used to take and remove 6 students from the Bag and use them to fill
     * the effect7Students array in EffectHandler
     * @param game main class where there are EffectHandler class and Board class
     */
    @Override
    public void initialize(Game game) {
        game.getEffectHandler().setEffect7students(game.getBoard().getAndRemoveRandomBagStudent(6));
    }

    /**
     * Method used to exchange up to three students of this effect with as many students
     * present in the player's entrance. Then the player's previous state is restored
     * @param game main class that contains methods for applying the effect
     * @param playerID player who used the effect
     * @throws InvalidStudentEffectException if the chosen students are not valid
     */
    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {

        if(game.getEffectHandler().getStudentschoose().size()>=1 && game.getEffectHandler().getStudentschoose().size()<=6 && game.getEffectHandler().getStudentschoose().size()%2==0) {
            ArrayList<Student> toEntrance = new ArrayList<>();
            ArrayList<Student> toCard = new ArrayList<>();

            //Expect first half from card to entrance, other part from entrance to card
            for (int count = 0; count < game.getEffectHandler().getStudentschoose().size() / 2; count++)
                toEntrance.add(game.getEffectHandler().getStudentschoose().get(count));
            for (int count = game.getEffectHandler().getStudentschoose().size() / 2; count < game.getEffectHandler().getStudentschoose().size(); count++)
                toCard.add(game.getEffectHandler().getStudentschoose().get(count));
            for (Student student : toCard)
                game.getPlayer(playerID).getPlance().removeStudentEntrance(student);
            for (Student student : toEntrance)
                game.getEffectHandler().removeStudentFromEffect7(student);
            for (Student student : toCard)
                game.getEffectHandler().addStudentInEffect7(student);
            for (Student student : toEntrance)
                game.getPlayer(playerID).getPlance().addStudentEntrance(student);
            game.setCharacterInUse(null);
            game.getPlayer(playerID).setPlayerState(prevPlayerState);
        }
        else
            throw new InvalidStudentEffectException();
    }

    /**
     * Method used to get the type of input required after using a character
     * @return the type of input required after using a character
     */
    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return TypeOfInputCharacter.EFFECT7INPUT;
    }
}
