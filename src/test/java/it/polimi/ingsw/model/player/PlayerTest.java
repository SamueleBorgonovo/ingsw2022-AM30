package it.polimi.ingsw.model.player;

import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.exceptions.OutOfCoinsException;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Tower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player = new Player("kek");

    @Test
    void setWizard(){
        player.setWizard(Wizard.WIZARD_GREEN);
        assertEquals(Wizard.WIZARD_GREEN,player.getWizard());
    }

    @Test
    void getCharacterPlayed(){
        assertFalse(player.getCharacterPlayed());
        player.setCharacterPlayed(true);
        assertTrue(player.getCharacterPlayed());
    }

    @Test
    void getNickname() {
        assertEquals("kek",player.getNickname());
    }

    @Test
    void coinsTest() {
        player.setCoins(1);
        assertEquals(1,player.getCoins());
        player.addCoins();
        assertEquals(2,player.getCoins());
        boolean check=false;
        try{
            player.removeCoins(3);
        } catch (OutOfCoinsException e) {
            check=true;
        }
        assertTrue(check);
        assertEquals(2,player.getCoins());
        player.addCoins();
        player.addCoins();
        try{
            player.removeCoins(3);
        }catch (OutOfCoinsException e){}
        assertEquals(1,player.getCoins());
    }

    @Test
    void playerView(){
        Plance plance = new Plance(Tower.BLACK,8);
        player.setPlance(plance);
        player.setWizard(Wizard.WIZARD_PINK);
        player.removeAssistant(Assistant.OSTRICH);
        player.removeAssistant(Assistant.CAT);
        player.setLastassistantplayed(Assistant.LION);
        player.setCoins(3);
        player.setPlayerState(PlayerState.ASSISTANTPHASE);
        PlayerView playerView = player.getPlayerView();
        assertEquals(Wizard.WIZARD_PINK,playerView.getWizard());
        assertEquals(Assistant.LION,playerView.getLastassistantplayed());
        assertEquals(8,playerView.getAssistantCards().size());
        assertTrue(playerView.getAssistantCards().containsAll(player.getAssistantCards()));
        assertEquals(3,playerView.getCoins());
        assertEquals(PlayerState.ASSISTANTPHASE,playerView.getPlayerState());
    }
}