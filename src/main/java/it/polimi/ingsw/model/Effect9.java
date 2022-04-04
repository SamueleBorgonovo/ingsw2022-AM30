package it.polimi.ingsw.model;

public class Effect9 extends Effect {
    // Once a student color has been chosen, that color does not provide influence
    // in the influence calculation on the current turn.
    @Override
    public void effect(Game game, int playerID) {
        game.getVerifyType().setNocolor(true);
        game.getVerifyType().setStudent(game.chooseStudent());
    }
}
