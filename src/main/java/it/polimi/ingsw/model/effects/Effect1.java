package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.board.Island;
import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;

public class Effect1 extends Effect {
    private ArrayList<Student> studentsoncard = new ArrayList<>();
    private Student choosedstudent;
    private int choosedislandID;
    private ArrayList<Island> island = new ArrayList<>();
    private int cost = 1;

    public void setStudentsOnCard (Game game){ studentsoncard.addAll(game.getBoard().getAndRemoveRandomBagStudent(4));}
    public ArrayList<Student> getStudentsOnCard (){ return studentsoncard;}

    @Override
    public int getCost(){ return cost;}

    @Override
    public void effect(Game game, int playerID) {
        choosedstudent = game.chooseStudent();
        choosedislandID = game.chooseIsland();

        island.addAll(game.getBoard().getArchipelago().getIslands());
        for(int count=0;count < game.getBoard().getArchipelago().getNumOfIslands();count ++) {
            if (island.get(count).getIslandID() == choosedislandID) {
                island.get(count).addStudent(choosedstudent);
                studentsoncard.remove(choosedstudent);
            }
        }
        studentsoncard.addAll(game.getBoard().getAndRemoveRandomBagStudent(1));
    }

    @Override
    public void inizialize(Game game) {

    }

    @Override
    public void secondPartEffect() {

    }
}
