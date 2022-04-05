package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;
public class Effect12 extends Effect{
    private Student choosedcolor;
    private Student removedstudent;
    private ArrayList<Player> listofplayers = new ArrayList<>();
    private ArrayList<Student> studentTobag = new ArrayList<>();

    @Override
    public void effect(Game game, int playerID) {
        choosedcolor=game.chooseStudent();
        listofplayers = game.getListOfPlayers();
        for(int count=0;count<game.getNumOfPlayers();count++){
            for(int counter=0;counter<3;counter++){
                removedstudent = listofplayers.get(count).getPlance().removeStudentFromHall(choosedcolor);
                if(removedstudent != null) {
                    studentTobag.add(removedstudent);
                }
            }
        }
        game.getBoard().addStudentBag(studentTobag);
    }
}
