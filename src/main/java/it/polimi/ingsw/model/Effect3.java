package it.polimi.ingsw.model;

public class Effect3 extends Effect {
    //Choose an island and calculate the influence as if
    // Mother Nature had finished her movement there.
    @Override
    public void effect(Game game, int playerID) {
        game.verifyIslandInfluence(game.getBoard().getArchipelago().getIslands().get(game.chooseIsland()));
    }
}
