package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Effect11 extends Effect{

    private ArrayList<Student> students = new ArrayList<>();
    private Student choosedstudent;

    public void setStudentsOnCard (Game game){
        students.addAll(game.getBoard().getAndRemoveRandomBagStudent(4));
    }
    public ArrayList<Student> getStudentsOnCard (){ return students;} //Just to test it

    @Override
    public void effect(Game game, int playerID) {
        choosedstudent = game.chooseStudent();
        game.getPlayer(playerID).getPlance().addStudentHall(choosedstudent);
        students.addAll(game.getBoard().getAndRemoveRandomBagStudent(1));
    }
}
