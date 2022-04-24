package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;

public class Effect7 extends Effect{
    private ArrayList<Student> students = new ArrayList<>();
    private int numberofstudent;
    private ArrayList<Student> choosedstudents = new ArrayList<>();
    private ArrayList<Student> choosedstudentsFromplance = new ArrayList<>();
    public void setStudentsOnCard (Game game){
        students.addAll(game.getBoard().getAndRemoveRandomBagStudent(6));
    }
    public ArrayList<Student> getStudentOnCard(){ return students;}
    public void setNumberofstudent (int numberofstudent){ this.numberofstudent = numberofstudent;}

    @Override
    public int getCost(){ return 1;}

    //Capire come prendere numberofstudent dall'utente

    @Override
    public void effect(Game game, int playerID) {
        for(int count=0;count<numberofstudent;count++){
            choosedstudents.add(game.chooseStudent());
            students.remove(choosedstudents.get(count));
        }
        for(int count=0;count<numberofstudent;count++){
            choosedstudentsFromplance.add(game.chooseStudentFromPlance()); //Non sono sicuro che chooseStudent sia il metodo giusto
            game.getPlayer(playerID).getPlance().removeStudentEntrance(choosedstudentsFromplance.get(count));
        }

        for(int count=0;count<numberofstudent;count++){
            game.getPlayer(playerID).getPlance().addStudentEntrance(choosedstudents.get(count));
            students.add(choosedstudentsFromplance.get(count));
        }


    }

    @Override
    public void inizialize(Game game) {

    }

    @Override
    public void secondPartEffect(Game game, int playerID) {

    }
}
