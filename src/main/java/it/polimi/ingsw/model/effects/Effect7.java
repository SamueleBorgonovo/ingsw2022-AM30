package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.WrongStudentEffectException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;

import java.util.ArrayList;

public class Effect7 extends Effect{
    private PlayerState prevPlayerState;

    @Override
    public int getCost(){ return 1;}

    @Override
    public void effect(Game game, int playerID) {
       prevPlayerState=game.getPlayer(playerID).getPlayerState();
       game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERSTUDENTSPHASE);

    }

    @Override
    public void inizialize(Game game) {
        game.getEffectHandler().setEffect7students(game.getBoard().getAndRemoveRandomBagStudent(6));

    }

    @Override
    public void secondPartEffect(Game game, int playerID) throws WrongStudentEffectException {

        if(game.getEffectHandler().getStudentschoose().size()>=1 && game.getEffectHandler().getStudentschoose().size()<=6 && game.getEffectHandler().getStudentschoose().size()%2==0) {
            ArrayList<Student> toEntrance = new ArrayList<>();
            ArrayList<Student> toCard = new ArrayList<>();

            //Expect first half from card to entrance, other part from entrance to card
            for (int count = 0; count < game.getEffectHandler().getStudentschoose().size() / 2; count++)
                toEntrance.add(game.getEffectHandler().getStudentschoose().get(count));

            for (int count = game.getEffectHandler().getStudentschoose().size() / 2; count < game.getEffectHandler().getStudentschoose().size(); count++)
                toCard.add(game.getEffectHandler().getStudentschoose().get(count));

            for (int count = 0; count < toCard.size(); count++)
                game.getPlayer(playerID).getPlance().removeStudentEntrance(toCard.get(count));

            for (int count = 0; count < toEntrance.size(); count++)
                game.getEffectHandler().removeStudentFromEffect7(toEntrance.get(count));

            for (int count = 0; count < toCard.size(); count++)
                game.getEffectHandler().addStudentInEffect7(toCard.get(count));

            for (int count = 0; count < toEntrance.size(); count++)
                game.getPlayer(playerID).getPlance().addStudentEntrance(toEntrance.get(count));

            game.setCharacterInUse(null);
            game.getPlayer(playerID).setPlayerState(prevPlayerState);
        }
        else throw new WrongStudentEffectException();
        }
}
