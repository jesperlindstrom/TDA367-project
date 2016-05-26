package se.chalmers.get_rect.tests.models;
import org.junit.Before;
import org.junit.Test;

import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.window.model.Inventory;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setup() {
        Player player = mock(Player.class);
        WeaponRepository repo = mock(WeaponRepository.class);

        List<IWeapon> weapons = new ArrayList<>();

        IWeapon weaponItem = mock(IWeapon.class);
        when(weaponItem.isFound()).thenReturn(true);

        for (int i = 0; i < 15; i++) {
            weapons.add(weaponItem);
        }

        IWeapon disabledWeaponItem = mock(IWeapon.class);
        when(weaponItem.isFound()).thenReturn(false);
        weapons.add(disabledWeaponItem);

        when(repo.get()).thenReturn(weapons);
        inventory = new Inventory(player, repo);
        inventory.reset();
    }

    @Test
    public void testCreation() {
        assertTrue("Should not allow regular input", inventory.isAllowingRegularInput());

        // Already reset in setup, so shouldn't override the
        Map<Point, IWeapon> map = inventory.getItemsMap();
        inventory.reset();
        assertEquals("Should not have reset", inventory.getItemsMap(), map);

        assertTrue("Bottom right position should be disabled", inventory.isButtonDisabled(new Point(3, 3)));
    }

    @Test
    public void testDisable() {
        inventory.disableButton(new Point(0, 0));
        assertTrue("Should be disabled", inventory.isButtonDisabled(new Point(0, 0)));

        inventory.disableButton(new Point(0, 0));
        assertTrue("Should still be disabled", inventory.isButtonDisabled(new Point(0, 0)));
    }

    @Test
    public void testMove() {
        inventory.moveMarkUp();
        assertEquals("Should be at (0, 0)", inventory.getCurrentlyMarked(), new Point(0, 0));

        inventory.moveMarkRight();
        assertEquals("Should be at (1, 0)", inventory.getCurrentlyMarked(), new Point(1, 0));

        inventory.moveMarkLeft();
        assertEquals("Should be at (0, 0)", inventory.getCurrentlyMarked(), new Point(0, 0));
    }
}
