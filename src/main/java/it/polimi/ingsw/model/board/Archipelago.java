package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;

import java.util.ArrayList;

public class Archipelago {
    private final ArrayList<Island> islands;
    private final MotherNature mothernature;

    public Archipelago(ArrayList<Island> islands, MotherNature mothernature) {
        this.islands = islands;
        this.mothernature = mothernature;
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }

    public ArrayList<Student> getStudentIslands(int islandID){
        for(int i=0;i<islands.size();i++)
            if(islands.get(i).getIslandID() == islandID)
                return islands.get(i).getStudents();
        return null;
    }

    public  Tower getTowerTypeIsland (int islandID){
        for(int i=0;i<islands.size();i++)
            if(islands.get(i).getIslandID() == islandID)
              return islands.get(i).getTowerColor();
        return null;
    }


    public void mergeIslands(int islandID1, int islandID2) {
        int max = Math.max(islandID1, islandID2);
        int min = Math.min(islandID1, islandID2);
        int indexMin=-1;
        int indexMax=-1;

        for(Island island: islands)
            if(island.getIslandID()==min)
                indexMin=islands.indexOf(island);
            else if(island.getIslandID()==max)
                indexMax=islands.indexOf(island);


        //with this if, I want to verify if the merge is possible (if the two islands are one near the other)
        if((indexMax - indexMin ) == 1
            || (indexMax - indexMin ) == islands.size()-1) {
            //I add the towers of the island I am going to remove in the other island
            for(int i=0; i<islands.get(indexMax).getNumOfTowers(); i++)
                islands.get(indexMin).addTower();
            //I add the students of the island I am going to remove in the other island
            for (Student student : islands.get(indexMax).getStudents())
                islands.get(indexMin).addStudent(student);
            //I remove the island with the bigger ID
            islands.remove(indexMax);
            //I have the island that now I have as the one with mother nature
            mothernature.setMotherNature(min);
        }
    }


    public int getNumOfIslands(){
        return islands.size();
    }
}
