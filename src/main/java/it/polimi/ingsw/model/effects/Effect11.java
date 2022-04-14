package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;

public class Effect11 extends Effect{

    private ArrayList<Student> students = new ArrayList<>();
    private Student choosedstudent;
    private int cost = 2;

    @Override
    public int getCost(){ return cost;}

    public void setStudentsOnCard (Game game){
        students.addAll(game.getBoard().getAndRemoveRandomBagStudent(4));
    }
    public ArrayList<Student> getStudentsOnCard (){ return students;} //Just to test it

    @Override
    public void effect(Game game, int playerID) {
        choosedstudent = game.chooseStudent();
        game.getPlayer(playerID).getPlance().addStudentHall(choosedstudent);
        students.remove(choosedstudent);
        students.addAll(game.getBoard().getAndRemoveRandomBagStudent(1));
    }
}
