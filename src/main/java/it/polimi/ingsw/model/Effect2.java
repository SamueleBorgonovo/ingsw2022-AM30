package it.polimi.ingsw.model;

public class Effect2 extends Effect {
    //Take control of the professors even if you have the same number of students
    // in the hall as the player currently controlling them.
    @Override
    public void effect(Game game, int playerID) {
        game.getVerifyType().setProfessorcontroll(true);
    }
}
