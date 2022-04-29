package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;

import java.util.ArrayList;
import java.util.Random;

public class Archipelago {
    private final ArrayList<Island> islands = new ArrayList<>();
    private MotherNature mothernature;


    public Archipelago() {
        Random rnd = new Random();

        for(int i = 1; i < 13; i++)
            islands.add(new Island(i));
        mothernature = new MotherNature(rnd.nextInt(12) + 1);
    }

    public MotherNature getMothernature() {
        return mothernature;
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }

    public ArrayList<Student> getStudentIslands(int islandID){
        for (Island island : islands)
            if (island.getIslandID() == islandID)
                return island.getStudents();
        return null;
    }

    public  Tower getTowerTypeIsland (int islandID){
        for (Island island : islands)
            if (island.getIslandID() == islandID)
                return island.getTowerColor();
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
        int i=1;
        for(Island island : islands){
            island.setIslandID(i);
            i++;
        }
    }

    public int getNumOfIslands(){
        return islands.size();
    }

    public Island getSingleIsland(int islandID){
        for (Island island : islands)
            if (island.getIslandID() == islandID)
                return island;
        return null;
    }

    public void verifyMergeableIsland() {
        for (int i = 0; i < this.getNumOfIslands(); i++)
            if (i != this.getNumOfIslands() -1 &&
                    islands.get(i).getTowerColor() ==
                            islands.get(i + 1).getTowerColor()
                    && islands.get(i).getTowerColor()!= null
                    && islands.get(i+1).getTowerColor()!= null ) {
                this.mergeIslands(islands.get(i).getIslandID(),
                        islands.get(i + 1).getIslandID());
                i=0;
            } else if (i == this.getNumOfIslands() - 1 &&
                    islands.get(i).getTowerColor() ==
                            islands.get(0).getTowerColor()
                    && islands.get(i).getTowerColor()!= null
                    && islands.get(0).getTowerColor()!= null) {
                this.mergeIslands(islands.get(i).getIslandID(), 1);
                i = 0;
            }
    }
}
