package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.effects.*;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;

public class Board {
    private ArrayList<Cloud> clouds = new ArrayList<>();
    private Archipelago archipelago;
    private ArrayList<Student> bag = new ArrayList<>();
    private int coinReserve;
    private ArrayList<Character> characters = new ArrayList<>();

    public Board(GameMode gamemode, int numofplayers){
        for (Student student : Student.values())
            for (int i = 0; i < 2; i++)
                bag.add(student);
        archipelago = new Archipelago();
        for (Island island : archipelago.getIslands()){
            if (island.getIslandID() != archipelago.getMothernature().isOn() && island.getIslandID() != (((archipelago.getMothernature().isOn() +5) %12) +1))
                island.addStudent(this.getAndRemoveRandomBagStudent(1).get(0));
        }
        for (Student student : Student.values())
            for (int i = 0; i < 24; i++)
                bag.add(student);
        for (int i = 1; i <= numofplayers; i++ )
            clouds.add(new Cloud(i));
        for (Cloud cloud : clouds)
            cloud.setStudents(this.getAndRemoveRandomBagStudent(numofplayers + 1));
        if (gamemode == GameMode.EXPERTMODE){
            coinReserve = 20;
            ArrayList<Effect> effects = new ArrayList<>();
            effects.add(new Effect1());
            effects.add(new Effect2());
            effects.add(new Effect3());
            effects.add(new Effect5());
            effects.add(new Effect6());
            effects.add(new Effect7());
            effects.add(new Effect8());
            effects.add(new Effect9());
            effects.add(new Effect10());
            effects.add(new Effect11());
            effects.add(new Effect12());
            for (int i = 0; i < 3; i++){
                int j = (int) (Math.random() * effects.size());
                characters.add(new Character(effects.get(j)));
                effects.remove(j);
            }
        }
    }

    public int getNumOfStudentsBag() {
        return bag.size();
    }

    public ArrayList<Student> getAndRemoveRandomBagStudent(int NumOfStudents) {
        ArrayList<Student> toReturn = new ArrayList<>();
        int index;
        for (int counter = 0; counter < NumOfStudents; counter++) {
            if (bag.size() > 0) {
                index = (int) (Math.random() * bag.size());
                toReturn.add(bag.get(index));
                bag.remove(index);
            }
        }
        return toReturn;
    }

    public ArrayList<Cloud> getClouds(){
        return clouds;
    }

    public int getCoinReserve(){
        return coinReserve;
    }

    public void addCoinstoReserve(int Coins){
        coinReserve = coinReserve +Coins;
    }

    public void removeCoinsFromReserve(int Coins){
        if(coinReserve < Coins)
            coinReserve =0;
        else
            coinReserve = coinReserve -Coins;
    }

    public ArrayList<Character> getCharacters(){
        return characters;
    }

    public void addStudentBag(ArrayList<Student> Students){
        bag.addAll(Students);
    }

    public Archipelago getArchipelago(){
        return archipelago;
    }
}