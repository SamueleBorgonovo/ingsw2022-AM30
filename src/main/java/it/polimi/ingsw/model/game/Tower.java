package it.polimi.ingsw.model.game;

public enum Tower {
    WHITE,
    BLACK,
    GREY;

    public String getText(Tower tower) {
        String s = "";
        switch (tower) {
            case GREY -> s="GREY";
            case BLACK -> s="BLACK";
            case WHITE -> s="WHITE";
        }
        return s;
    }
}
