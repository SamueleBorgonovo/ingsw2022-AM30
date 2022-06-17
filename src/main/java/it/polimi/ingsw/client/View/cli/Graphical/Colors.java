package it.polimi.ingsw.client.View.cli.Graphical;

public enum Colors {
    GREEN("\u001B[32m"),
    BRIGHT_RED("\u001B[91m"),
    BRIGHT_YELLOW("\u001B[93m"),
    PURPLE("\u001B[35m"),
    BLUE("\u001B[34m"),
    BRIGHT_WHITE("\u001B[97m"),
    BLACK("\u001B[30m"),
    WHITE("\u001B[37m"),
    CYAN("\u001B[36m"),
    BRIGHT_GREEN("\u001B[92m"),
    RED("\u001B[31m"),
    YELLOW("\u001B[33m"),
    BRIGHT_PURPLE("\u001B[95m"),
    BRIGHT_BLUE("\u001B[94m"),
    BRIGHT_CYAN("\u001B[96m"),
    BRIGHT_BLACK("\u001B[90m"),
    RESET("\u001B[0m");

    private final String code;

    Colors(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
