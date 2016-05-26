package se.chalmers.get_rect.tests.models;
import org.junit.Before;
import org.junit.Test;

import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.window.model.ErrorWindow;
import se.chalmers.get_rect.game.window.model.IGameControl;
import se.chalmers.get_rect.utilities.Point;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ErrorWindowTest {
    @Test
    public void testErrorWindow() {
        IGameControl game = mock(IGameControl.class);
        ErrorWindow window = new ErrorWindow(game);

        window.setMessage("test");
        assertEquals("Test message should be test", window.getMessage(), "test");

        window.reset();
        assertEquals("Position should be (0, 0)", window.getCurrentlyMarked(), new Point(0, 0));

        assertFalse("Should not allow regular input", window.isAllowingRegularInput());
    }
}
