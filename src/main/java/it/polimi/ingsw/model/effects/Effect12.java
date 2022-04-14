package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;
public class Effect12 extends Effect{
    private Student choosedcolor;
    private ArrayList<Student> studentTobag = new ArrayList<>();
    private int cost = 3;

    @Override
    public int getCost(){ return cost;}

    @Override
    public void effect(Game game, int playerID) {
        choosedcolor=game.chooseStudent();
        for(Player player : game.getListOfPlayers()){
            for(int counter=0;counter<3 && player.getPlance().getNumberOfStudentHall(choosedcolor)>0;counter++){
                    player.getPlance().removeStudentFromHall(choosedcolor);
                    studentTobag.add(choosedcolor);
            }
        }
        game.getBoard().addStudentBag(studentTobag);
    }
}
