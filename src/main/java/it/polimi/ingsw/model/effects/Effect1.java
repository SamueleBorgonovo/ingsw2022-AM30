package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;

public class Effect1 extends Effect {
    private PlayerState prevPlayerState;

    @Override
    public int getCost(){ return 1;}

    @Override
    public String getName(){ return "MONK";}

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        prevPlayerState = game.getPlayer(playerID).getPlayerState();
        game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERSTUDENTSPHASE);
    }

    @Override
    public void initialize(Game game) {
        game.getEffectHandler().setEffect1students(game.getBoard().getAndRemoveRandomBagStudent(4));
    }

    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {
        Student choosedstudent;

        if(game.getPlayer(playerID).getPlayerState() == PlayerState.CHARACTHERSTUDENTSPHASE)
            game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERISLANDPHASE);
        else if(game.getListOfPlayers().get(playerID-1).getPlayerState() == PlayerState.CHARACTHERISLANDPHASE) {
            choosedstudent = game.getEffectHandler().getStudentschoose().get(0);
            if(game.getEffectHandler().getEffect1students().contains(choosedstudent)){
                game.getBoard().getArchipelago().getSingleIsland(game.getEffectHandler().getIslandIDchoose()).addStudent(choosedstudent);
                game.getEffectHandler().removeStudentFromEffect1students(choosedstudent);
                game.getEffectHandler().addStudentInEffect1students(game.getBoard().getAndRemoveRandomBagStudent(1).get(0));
                game.setCharacterInUse(null);
                game.getPlayer(playerID).setPlayerState(prevPlayerState);
            }
            else
                throw new InvalidStudentEffectException();
        }
    }

    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return TypeOfInputCharacter.EFFECT1INPUT;
    }
}
