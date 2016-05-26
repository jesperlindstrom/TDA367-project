package se.chalmers.get_rect.tests.models;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.game.window.model.IGameControl;
import se.chalmers.get_rect.game.window.model.MainMenu;
import se.chalmers.get_rect.utilities.Point;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MainMenuTest {
    private IGameControl game;

    @Before
    public void setup() {
        game = mock(IGameControl.class);
    }

    @Test
    public void testLoadAvailable() {
        when(game.loadAvailable()).thenReturn(true);
        MainMenu menu = new MainMenu(game);

        boolean isContinueDisabled = menu.isButtonDisabled(new Point(0, 0));
        assertFalse("Continue button should not be disabled", isContinueDisabled);
    }

    @Test
    public void testLoadUnavailable() {
        when(game.loadAvailable()).thenReturn(false);
        MainMenu menu = new MainMenu(game);

        boolean isContinueDisabled = menu.isButtonDisabled(new Point(0, 0));
        assertTrue("Continue button should be disabled", isContinueDisabled);

        menu.activateAll();

        isContinueDisabled = menu.isButtonDisabled(new Point(0, 0));
        assertFalse("Continue button should not be disabled", isContinueDisabled);
    }

    @Test
    public void testDisabled() {
        when(game.loadAvailable()).thenReturn(false);
        MainMenu menu = new MainMenu(game);

        menu.activateButton(new Point(0, 0));

        boolean isContinueDisabled = menu.isButtonDisabled(new Point(0, 0));
        assertFalse("Continue button should not be disabled", isContinueDisabled);
    }

    @Test
    public void testReset() {
        when(game.loadAvailable()).thenReturn(true);
        MainMenu menu = new MainMenu(game);
        menu.moveMarkDown();
        assertEquals("Marker should be (0, 1)", menu.getCurrentlyMarked(), new Point(0, 1));
        menu.reset();
        assertEquals("Marker should be (0, 0)", menu.getCurrentlyMarked(), new Point(0, 0));
    }

    @Test
    public void testCreation() {
        when(game.loadAvailable()).thenReturn(true);
        MainMenu menu = new MainMenu(game);

        assertFalse("Should not allow regular input", menu.isAllowingRegularInput());
    }

    @Test
    public void testMove() {
        when(game.loadAvailable()).thenReturn(true);
        MainMenu menu = new MainMenu(game);

        assertEquals("Should start at (0, 0)", menu.getCurrentlyMarked(), new Point(0, 0));

        menu.moveMarkDown();
        assertEquals("Should be at (0, 1)", menu.getCurrentlyMarked(), new Point(0, 1));

        menu.getCurrentlyMarkedButton().executeAction();
        verify(game, times(1)).startNew();

        menu.moveMarkUp();
        assertEquals("Should be at (0, 0)", menu.getCurrentlyMarked(), new Point(0, 0));

        // Shouldn't move in this case
        menu.moveMarkLeft();
        assertEquals("Should be at (0, 0)", menu.getCurrentlyMarked(), new Point(0, 0));

        // Shouldn't move in this case
        menu.moveMarkRight();
        assertEquals("Should be at (0, 0)", menu.getCurrentlyMarked(), new Point(0, 0));

        menu.setIndex(new Point(123, 123));
        assertEquals("Should be at (0, 0)", menu.getCurrentlyMarked(), new Point(0, 0));

        // Shouldn't move in this case
        menu.setIndex(new Point(0, 2));
        assertEquals("Should be at (0, 2)", menu.getCurrentlyMarked(), new Point(0, 2));

        menu.moveMarkDown();
        assertEquals("Should still be at (0, 2)", menu.getCurrentlyMarked(), new Point(0, 2));
    }
}
