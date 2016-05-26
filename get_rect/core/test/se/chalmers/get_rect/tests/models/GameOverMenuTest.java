package se.chalmers.get_rect.tests.models;
import org.junit.Before;
import org.junit.Test;

import se.chalmers.get_rect.game.window.model.GameOverMenu;
import se.chalmers.get_rect.game.window.model.IGameControl;
import se.chalmers.get_rect.utilities.Point;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameOverMenuTest {
    @Test
    public void testGameOverMenu() {
        IGameControl game = mock(IGameControl.class);
        GameOverMenu window = new GameOverMenu(game);

        window.reset();
        assertEquals("Position should be (0, 0)", window.getCurrentlyMarked(), new Point(0, 0));

        assertFalse("Should not allow regular input", window.isAllowingRegularInput());
    }
}
