package src.main.java.org.example;

public class Player {
    private final String nickname;
    private final int playerID;
    private PlayerState playerState;
    private Dashboard dashboard;
    private Plance plance;
    private final Wizard wizard;

    public Player(String nickname, int playerID, PlayerState playerState, Dashboard dashboard, Plance plance, Wizard wizard) {
        this.nickname = nickname;
        this.playerID = playerID;
        this.playerState = playerState;
        this.dashboard = dashboard;
        this.plance = plance;
        this.wizard = wizard;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPlayerID() {
        return playerID;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public Plance getPlance() {
        return plance;
    }

    public Wizard getWizard() {
        return wizard;
    }


}
