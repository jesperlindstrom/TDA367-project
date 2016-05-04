package se.chalmers.get_rect.UC;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.adapters.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.player.Player;

import static org.junit.Assert.*;

public class UC7PlayerDies {

    private Player player;

    @Before
    public void setup(){
        RectangleFactoryAdapterStub rectangleFactoryAdapterStub = new RectangleFactoryAdapterStub();
        player = new Player(rectangleFactoryAdapterStub);
    }

    @Test
    public void testPlayerDies(){
        assertFalse("Should be false, player is not dead", player.getCurrentHealth() == 0);
        player.takeDamage(player.getCurrentHealth());
        assertTrue("Should be true, player is dead", player.getCurrentHealth() == 0);
    }
}
