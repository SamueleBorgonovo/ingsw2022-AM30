package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;

public class Effect1 extends Effect {
    private PlayerState prevPlayerState;

    @Override
    public int getCost(){ return 1;}

    @Override
    public void effect(Game game, int playerID) {
        prevPlayerState = game.getPlayer(playerID).getPlayerState();
        game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERSTUDENTSPHASE);
    }

    @Override
    public void inizialize(Game game) {
        game.getEffectHandler().setEffect1students(game.getBoard().getAndRemoveRandomBagStudent(4));
    }

    @Override
    public void secondPartEffect(Game game, int playerID) {
        Student choosedstudent;

        if(game.getListOfPlayers().get(playerID-1).getPlayerState() == PlayerState.CHARACTHERSTUDENTSPHASE)
            game.getListOfPlayers().get(playerID-1).setPlayerState(PlayerState.CHARACTHERISLANDPHASE);
        else if(game.getListOfPlayers().get(playerID-1).getPlayerState() == PlayerState.CHARACTHERISLANDPHASE) {
            choosedstudent = game.getEffectHandler().getStudentschoose().get(0);
            if(game.getEffectHandler().getEffect1students().contains(choosedstudent)){
                game.getBoard().getArchipelago().getSingleIsland(game.getEffectHandler().getIslandIDchoose()).addStudent(choosedstudent);
                game.getEffectHandler().removeStudentFromEffect1students(choosedstudent);
                game.getEffectHandler().addStudentInEffect1students(game.getBoard().getAndRemoveRandomBagStudent(1).get(0));
            }
            game.setCharacterInUse(null);
            game.getPlayer(playerID).setPlayerState(prevPlayerState);
        }
    }
}
