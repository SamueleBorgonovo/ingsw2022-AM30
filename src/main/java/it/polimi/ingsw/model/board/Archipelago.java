package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Archipelago class handles the action related to the islands in the game
 */
public class Archipelago implements Serializable {
    private final ArrayList<Island> islands = new ArrayList<>();
    private MotherNature mothernature;

    /**
     * Constructor Archipelago creates the islands of the game
     */
    public Archipelago() {
        Random rnd = new Random();

        for(int i = 1; i < 13; i++)
            islands.add(new Island(i));
        mothernature = new MotherNature(rnd.nextInt(12) + 1);
    }

    /**
     * Method getMotherNature returns the id of the island with mother nature on
     *
     * @return the id the island with mother nature on
     */
    public MotherNature getMothernature() {
        return mothernature;
    }

    /**
     * Method getIslands returns the islands of the Game
     *
     * @return the islands of the Game
     */
    public ArrayList<Island> getIslands() {
        return islands;
    }

    /**
     * Method getStudentIslands returns the students on the island
     *
     * @param islandID the island id
     * @return the students on the islands
     */
    public ArrayList<Student> getStudentIslands(int islandID){
        for (Island island : islands)
            if (island.getIslandID() == islandID)
                return island.getStudents();
        return null;
    }

    /**
     * Method getTowerTypeIsland return the tower located on the island
     *
     * @param islandID the island id
     * @return the tower located on the island
     */
    public  Tower getTowerTypeIsland (int islandID){
        for (Island island : islands)
            if (island.getIslandID() == islandID)
                return island.getTowerColor();
        return null;
    }

    /**
     * Method mergeIslands merge two Island that have the same tower's color
     *
     * @param islandID1 the first island to merge
     * @param islandID2 the second island to merge
     */
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
            if(islands.get(indexMax).isStop())
                islands.get(indexMin).setStop(true);
            //I remove the island with the bigger ID
            islands.remove(indexMax);
            //I have the island that now I have as the one with mother nature
            if(mothernature.isOn()>=max)
                mothernature.setMotherNature(mothernature.isOn()-1);
        }
        int i=1;
        for(Island island : islands){
            island.setIslandID(i);
            i++;
        }
    }

    /**
     * Method getNumOfIslands return the total number of the islands in the game
     *
     * @return the total number of the islands in the game
     */
    public int getNumOfIslands(){
        return islands.size();
    }

    /**
     * Method getSingleIsland return the Island instance by his unique id
     *
     * @param islandID the island id
     * @return the Island instance by his unique id
     */
    public Island getSingleIsland(int islandID){
        for (Island island : islands)
            if (island.getIslandID() == islandID)
                return island;
        return null;
    }

    /**
     * Method verifyMergeableIsland check if there are any islands to merge
     */
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
