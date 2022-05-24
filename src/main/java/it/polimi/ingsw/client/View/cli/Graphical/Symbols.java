package it.polimi.ingsw.client.View.cli.Graphical;

public enum Symbols {
    STUDENT_FULL("\u25CF"),
    STUDENT_EMPTY("\u25CB"),
    CORNER_TOP_RIGHT("\u256E"),
    CORNER_TOP_LEFT("\u256D"),
    CORNER_DOWN_RIGHT("\u256F"),
    CORNER_DOWN_LEFT("\u2570"),
    TOWER_FULL("\u25B2"),
    TOWER_EMPTY("\u25B3"),
    PROFESSOR_FULL("\u25A0"),
    PROFESSOR_EMPTY("\u25A1"),
    HALF_DASH_RIGHT("\u2576"),
    HALF_DASH_LEFT("\u2574");

    private final String code;

    Symbols(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
