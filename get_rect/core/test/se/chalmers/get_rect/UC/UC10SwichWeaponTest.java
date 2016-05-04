package se.chalmers.get_rect.UC;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.adapters.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.player.Player;

import static org.junit.Assert.*;

public class UC10SwichWeaponTest {

    private Player player;

    @Before
    public void setup(){
        RectangleFactoryAdapterStub rectangleFactory = new RectangleFactoryAdapterStub();
        this.player = new Player(rectangleFactory);
    }

    @Test
    public void testSwitchWeapon(){
        assertFalse("Should be false", player.isPrimaryWeapon());
        player.switchWeapon();
        assertTrue("Should be true", player.isPrimaryWeapon());
    }
}
