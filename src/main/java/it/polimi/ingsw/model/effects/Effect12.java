package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.exceptions.InvalidStopException;
import it.polimi.ingsw.exceptions.InvalidStudentEffectException;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.PlayerState;

import java.util.ArrayList;
public class Effect12 extends Effect{
    private PlayerState prevPlayerState;
    private final TypeOfInputCharacter typeOfInputCharacter = TypeOfInputCharacter.STUDENT;

    @Override
    public int getCost(){ return 3;}

    @Override
    public String getName(){ return "PICAROON";}

    @Override
    public void effect(Game game, int playerID)  throws InvalidStopException {
        prevPlayerState = game.getPlayer(playerID).getPlayerState();
        game.getPlayer(playerID).setPlayerState(PlayerState.CHARACTHERSTUDENTSPHASE);
    }

    @Override
    public void initialize(Game game) {}

    @Override
    public void secondPartEffect(Game game, int playerID) throws InvalidStudentEffectException {
        ArrayList<Student> studentTobag = new ArrayList<>();
        Student choosedcolor = game.getEffectHandler().getStudentschoose().get(0);

        for(Player player : game.getListOfPlayers())
            for(int i = 0; i < 3 && player.getPlance().getNumberOfStudentHall(choosedcolor)>0; i++){
                player.getPlance().removeStudentFromHall(choosedcolor);
                studentTobag.add(choosedcolor);
            }
        game.getBoard().addStudentBag(studentTobag);
        game.setCharacterInUse(null);
        game.getPlayer(playerID).setPlayerState(prevPlayerState);
    }

    @Override
    public TypeOfInputCharacter getTypeOfInputCharacter() {
        return this.typeOfInputCharacter;
    }
}
