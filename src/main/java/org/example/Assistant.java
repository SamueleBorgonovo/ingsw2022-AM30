package src.main.java.org.example;

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

    private final int valueAssistant;
    private final int movementAssistant;

    private Assistant(int valueAssistant, int movementAssistant){
        this.valueAssistant = valueAssistant;
        this.movementAssistant = movementAssistant;
    }

    public int getValueAssistant(){
        return valueAssistant;
    }

    public int getMovementAssistant(){
        return movementAssistant;
    }

}
