package it.polimi.ingsw;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Archipelago {
    private final ArrayList<Island> islands;

    public Archipelago(ArrayList<Island> islands) {
        this.islands = islands;
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }

    public ArrayList<Student> getStudentIslands(int islandID){
        for(int i=0;i<islands.size();i++)
            if(islands.get(i).getIslandID() == i)
                return islands.get(i).getStudents();
        return null;
    }

    public  ArrayList<Tower> getTowersIsland (int islandID){
        for(int i=0;i<islands.size();i++)
            if(islands.get(i).getIslandID() == i)
                return islands.get(i).getTowers();
        return null;
    }

    public int getMotherNatureIsland (){
        for(Island island: islands)
            if(island.isMotherNature())
                return island.getIslandID();
        return -1;
    }

    public void setMotherNatureIsland(@NotNull Island island){
        island.setMotherNature(true);
    }

    public void mergeIslands(Island island1, Island island2) {
        int max = Math.max(island1.getIslandID(), island2.getIslandID());
        int min = Math.min(island1.getIslandID(), island2.getIslandID());

        //con il seguente if, verifico se il merge che sto cercando di fare ha senso,
        //ovvero se le isole sono effettivamente vicine tra loro
        if(Math.abs((islands.indexOf(island1)% islands.size()) - (islands.indexOf(island2)% islands.size()))==1) {
            //I add the towers of the island I am going to remove in the other island
            islands.get(min).addTowers(islands.get(max).getTowers());
            //I add the students of the island I am going to remove in the previous island
            for (Student student : islands.get(max).getStudents())
                islands.get(min).addStudents(student);
        }
    }

    public int getNumOfIslands(){
        return islands.size();
    }
}
