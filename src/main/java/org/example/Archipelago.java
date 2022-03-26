package org.example;
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
    }
}
