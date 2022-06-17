package it.polimi.ingsw.model.game;

/**
 * Enumeration representing all the Student available in the game
 */
public enum Student {
    GREEN,
    RED,
    YELLOW,
    PINK,
    BLUE;

   public String getText(Student student){
        String s;
        if(student==Student.GREEN)
            s="Green";
        else if(student==Student.RED)
            s="Red";
        else if(student==Student.YELLOW)
            s="Yellow";
        else if(student==Student.PINK)
            s="Pink";
        else
            s="Blue";
        return s;
    }

}
