package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;

public class Effect10 extends Effect{
    private int numberofstudents=0;
    private ArrayList<Student> choosedstudents = new ArrayList<>();
    private ArrayList<Student> choosedstudentfromhall = new ArrayList<>();
    private int cost = 1;

    @Override
    public int getCost(){ return cost;}

    public void setNumberofstudents(int numberofstudents){
        if(numberofstudents >0 && numberofstudents <3)
            this.numberofstudents=numberofstudents;
    }
    //numberofstudents has to be taken from the player
    @Override
    public void effect(Game game, int playerID) {

        for(int count=0;count<numberofstudents;count++){
            choosedstudents.add(game.chooseStudent());
            game.getPlayer(playerID).getPlance().removeStudentEntrance(choosedstudents.get(count));

        }
        for(int count=0;count<numberofstudents;count++){
            choosedstudentfromhall.add(game.chooseStudentFromPlance());
            //Use chooseStudentFromPlance just to do test
            game.getPlayer(playerID).getPlance().removeStudentFromHall(choosedstudentfromhall.get(count));
        }

        for(int count=0;count<numberofstudents;count++){
            game.getPlayer(playerID).getPlance().addStudentHall(choosedstudents.get(count));
            game.getPlayer(playerID).getPlance().addStudentEntrance(choosedstudentfromhall.get(count));
        }



    }
}
