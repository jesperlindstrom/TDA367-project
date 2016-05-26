package se.chalmers.get_rect.tests.models;
import org.junit.Before;
import org.junit.Test;

import se.chalmers.get_rect.game.window.model.IGameControl;
import se.chalmers.get_rect.game.window.model.InGameMenu;
import se.chalmers.get_rect.utilities.Point;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InGameMenuTest {
    @Test
    public void testInGameMenu() {
        IGameControl game = mock(IGameControl.class);
        InGameMenu window = new InGameMenu(game);

        window.reset();
        assertEquals("Position should be (0, 0)", window.getCurrentlyMarked(), new Point(0, 0));

        assertTrue("Should allow regular input", window.isAllowingRegularInput());
    }
}
