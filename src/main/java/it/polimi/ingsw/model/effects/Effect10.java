package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;

import java.util.ArrayList;

public class Effect10 extends Effect{
    private PlayerState prevPlayerState;
    @Override
    public int getCost(){ return 1;}

    @Override
    public String getName(){ return "MINSTREL";}

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        prevPlayerState=game.getPlayer(playerID).getPlayerState();
        game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERSTUDENTSPHASE);
    }

    @Override
    public void inizialize(Game game) {

    }

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

            for(int count=0;count<toEntrance.size();count++)
                game.getPlayer(playerID).getPlance().removeStudentFromHall(toEntrance.get(count));

            for(int count=0;count<toHall.size();count++)
                game.getPlayer(playerID).getPlance().removeStudentEntrance(toHall.get(count));

            for(int count=0;count<toEntrance.size();count++)
                game.getPlayer(playerID).getPlance().addStudentEntrance(toEntrance.get(count));

            for(int count=0;count<toHall.size();count++)
                game.getPlayer(playerID).getPlance().addStudentHall(toHall.get(count));

            game.setCharacterInUse(null);
            game.getPlayer(playerID).setPlayerState(prevPlayerState);
        } else
            throw new InvalidStudentEffectException();
    }

}
