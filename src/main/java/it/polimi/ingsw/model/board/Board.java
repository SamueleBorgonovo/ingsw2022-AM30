package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;

public class Board {
    private ArrayList<Cloud> clouds = new ArrayList<>();
    private Archipelago archipelago;
    private ArrayList<Student> bag = new ArrayList<>();
    private int coinReserve = 20;
    private ArrayList<java.lang.Character> characters = new ArrayList<>();


    public Board(ArrayList<Cloud> clouds, Archipelago archipelago, ArrayList<java.lang.Character> Characters) {
        this.clouds = clouds;
        this.archipelago = archipelago;
        this.characters = Characters;
    }

    public int getNumOfStudentsBag() {
        return bag.size();
    }


    public ArrayList<Student> getAndRemoveRandomBagStudent(int NumOfStudents) {
        ArrayList<Student> toReturn = new ArrayList<>();
        int index;
        for (int counter = 0; counter < NumOfStudents; counter++) {
            if (bag.size() > 0) {
                index = (int) ((Math.random() * bag.size()));
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

    public ArrayList<java.lang.Character> getCharacters(){
        return characters;
    }

    public void addStudentBag(ArrayList<Student> Students){
        bag.addAll(Students);
    }

    public Archipelago getArchipelago(){
        return archipelago;
    }
}