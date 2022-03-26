package org.example;

public enum Assistant {
    LION(1, 1),
    OSTRICH(2, 1),
    CAT(3, 2),
    EAGLE(4, 2),
    FOX(5, 3),
    SNAKE(6, 3),
    OCTOPUS(7, 4),
    DOG(8, 4),
    ELEPHANTS(9, 5),
    TURTLE(10, 5);

    private final int value;
    private final int movement;

    private Assistant(int value, int movement){
        this.value = value;
        this.movement = movement;
    }

    public int getValue(){
        return value;
    }

    public int getMovement(){
        return movement;
    }

}
