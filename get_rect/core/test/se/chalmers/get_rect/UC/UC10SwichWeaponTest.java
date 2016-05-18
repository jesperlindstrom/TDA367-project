package se.chalmers.get_rect.UC;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.adapters.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.item.model.IMelee;
import se.chalmers.get_rect.game.entities.item.model.IRanged;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.player.Player;

import static org.junit.Assert.*;

public class UC10SwichWeaponTest {

    private Player player;
    private IWeapon storedWeapon; // TODO: name in progress

    @Before
    public void setup(){
        RectangleFactoryAdapterStub rectangleFactory = new RectangleFactoryAdapterStub();
        this.player = new Player(rectangleFactory);
        IRanged ranged = Mockito.mock(IRanged.class);
        IMelee melee = Mockito.mock(IMelee.class);
        player.addNewWeapon(ranged);
        player.addNewWeapon(melee);
        storedWeapon = player.getActiveWeapon();
    }

    @Test
    public void testSwitchWeapon(){
        assertEquals("Players active weapon and the stored one should be the same", player.getActiveWeapon(), storedWeapon);
        player.switchWeapon();
        assertNotEquals("After switch the weapons should not be the same", player.getActiveWeapon(), storedWeapon);
    }
}
