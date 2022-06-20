package it.polimi.ingsw.model.board;

import java.io.Serializable;

/**
 * Mothernature class is the reference to the island with mother nature
 */
public class MotherNature implements Serializable {
        private int islandID;

    /**
     * Constructor MotherNature creates the reference tho the Island with mother nature
     *
     * @param islandID the id of the island
     */
    public MotherNature(int islandID) {
            this.islandID = islandID;
        }

    /**
     * Method isOn returns the id of the island with mother nature on
     *
     * @return the id of the island with mother nature on
     */
    public int isOn() {
        return islandID;
    }

    /**
     * Metod move is used to move mother nature among the Archipelago
     *
     * @param numOfIslands the number of movements
     */
    public void move(int numOfIslands) {
        if(isOn()==numOfIslands){
            this.islandID=1;
        }
        else{
            this.islandID=this.islandID+1;
        }
    }

    /**
     * Method setMotherNature set the island with mother nature
     *
     * @param islandID set the island wirìth mother nature
     */
    public void setMotherNature(int islandID){
        if(islandID >= 1 && islandID <=12)
            this.islandID=islandID;
    }
}

