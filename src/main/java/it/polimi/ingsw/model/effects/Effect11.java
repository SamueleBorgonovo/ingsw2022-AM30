package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;

public class Effect11 extends Effect{
    private PlayerState prevPlayerState;

    @Override
    public int getCost(){ return 2;}

    @Override
    public String getName(){ return "PRINCESS";}

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        prevPlayerState = game.getPlayer(playerID).getPlayerState();
        game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERSTUDENTSPHASE);
    }

    @Override
    public void inizialize(Game game) {
        game.getEffectHandler().setEffect11students(game.getBoard().getAndRemoveRandomBagStudent(4));
    }

    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {
        Student choosedstudent = game.getEffectHandler().getStudentschoose().get(0);

        if(game.getEffectHandler().getEffect11students().contains(choosedstudent)){
            game.getPlayer(playerID).getPlance().addStudentHall(choosedstudent);
            game.getEffectHandler().removeStudentFromEffect11students(choosedstudent);
            game.getEffectHandler().addStudentInEffect11students(game.getBoard().getAndRemoveRandomBagStudent(1).get(0));
            game.setCharacterInUse(null);
            game.getPlayer(playerID).setPlayerState(prevPlayerState);
        }
        else
            throw new InvalidStudentEffectException();
    }
}
