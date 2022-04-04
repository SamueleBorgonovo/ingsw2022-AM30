package it.polimi.ingsw.model;

public class Effect5 extends Effect {
    // Place a ban on an island of your choice.
    // The first time Mother Nature moves there the ban falls and does not calculate the influence there.
    // The bans can be a maximum of 4.
    @Override
    public void effect(Game game, int playerID) {
        if(game.getVerifyType().getNumofislandstops()>0) {
            game.getBoard().getArchipelago().getIslands().get(game.chooseIsland()).setStop(true);
            game.getVerifyType().removeislandstop();
        }
    }
}
