package org.example;

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

    public int getNumOfIslands(){
        return islands.size();
    }

    public void mergeIslands(int islandID1, int islandID2) {
        int max = Math.max(islandID1, islandID2);
        for (int i = 0; i <max; i++){
                if (max == islands.get(i).getIslandID()) {
                    //I add the towers of the island I am going to remove in the previous island
                    islands.get(i - 1).addTowers(islands.get(i).getTowers());
                    //I add the students of the island I am going to remove in the previous island
                    for(Student student : islands.get(i).getStudents())
                        islands.get(i - 1).addStudents(student);
                    islands.remove(i);
                }

        }
    }

}
