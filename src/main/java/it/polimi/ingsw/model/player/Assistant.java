package it.polimi.ingsw.model.player;

/**
 * Enumeration representing all the Assistants available in the game
 */
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

    /**
     * Constructor Assistant creates a new assistant card
     * @param value the value of the assistant
     * @param movement the movement possible by the assistant
     */
    Assistant(int value, int movement){
        this.value = value;
        this.movement = movement;
    }

    /**
     * Method getValue returns the value of the assistant
     *
     * @return the value of the assistant
     */
    public int getValue(){
        return value;
    }

    /**
     * Method getMovement return the movement of the assistant
     *
     * @return the movement of the assistant
     */
    public int getMovement(){
        return movement;
    }

    /** Method getText returns the name of the assistant
     *
     * @param assistant the assistant requested
     * @return the name of the assistant
     */
    public String getText(Assistant assistant){
        return assistant.toString().toLowerCase();
    }

}
