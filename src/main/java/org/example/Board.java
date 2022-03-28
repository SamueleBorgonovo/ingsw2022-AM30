package org.example;

import java.util.ArrayList;

public class Board {
    private ArrayList<Cloud> Clouds = new ArrayList<>();
    private Archipelago Archipelago;
    private ArrayList<Student> Bag = new ArrayList<>();
    private int CoinsReserve;
    private ArrayList<Character> Characters = new ArrayList<>();

    public Board(ArrayList<Cloud> Clouds, Archipelago Archipelago, ArrayList<Student> Bag, int CoinsReserve, ArrayList<Character> Characters) {
        this.Clouds = Clouds;
        this.Archipelago = Archipelago;
        this.Bag = Bag;
        this.CoinsReserve = CoinsReserve;
        this.Characters = Characters;
    }

    public int getNumOfStudentsBag() {
        return Bag.size();
    }


    public ArrayList<Student> getAndRemoveRandomBagStudent(int NumOfStudents) {
        ArrayList<Student> toReturn = new ArrayList<>();
        int index;
        for (int counter = 0; counter < NumOfStudents; counter++) {
            if (Bag.size() > 0) {
                index = (int) ((Math.random() * Bag.size()));
                toReturn.add(Bag.get(index));
                Bag.remove(index);
            }
        }
        return toReturn;
    }

    public ArrayList<Cloud> getClouds(){
        return Clouds;
    }

    public int getCoinsReserve(){
        return CoinsReserve;
    }

    public void addCoinstoReserve(int Coins){
        CoinsReserve=CoinsReserve+Coins;
    }

    public void removeCoinsFromReserve(int Coins){
        if(CoinsReserve < Coins)
            CoinsReserve=0;
        else
            CoinsReserve=CoinsReserve-Coins;
    }

    public ArrayList<Character> getCharacters(){
        return Characters;
    }

    public void addStudentBag(ArrayList<Student> Students){
        Bag.addAll(Students);
    }

    public Archipelago getArchipelago(){
        return Archipelago;
    }
}