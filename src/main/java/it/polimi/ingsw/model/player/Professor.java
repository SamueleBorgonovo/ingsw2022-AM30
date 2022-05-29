package it.polimi.ingsw.model.player;

public enum Professor{
    GREEN_FROG,
    RED_DRAGON,
    YELLOW_ELF,
    PINK_FAIRY,
    BLUE_UNICORN;

    public String getText(Professor prof){
        String s="";
        if(prof==GREEN_FROG)
            s="Green";
        if(prof==RED_DRAGON)
            s="Red";
        if(prof==YELLOW_ELF)
            s="Yellow";
        if(prof==PINK_FAIRY)
            s="Pink";
        if(prof==BLUE_UNICORN)
            s="Blue";
        return s;
    }
}