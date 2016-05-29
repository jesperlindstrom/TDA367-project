package se.chalmers.get_rect.tests.models;
import org.junit.Before;
import org.junit.Test;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.game.window.controller.IWindowController;
import se.chalmers.get_rect.game.window.model.SplashModel;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SplashTest {
    @Test
    @SuppressWarnings("unchecked")
    public void testSplash() {
        IAssetManagerAdapter assets = mock(IAssetManagerAdapter.class);
        StateManager<IWindowController> windows = (StateManager<IWindowController>) mock(StateManager.class);
        SplashModel splash = new SplashModel(assets, windows);
        assertFalse(splash.getAddedAssets());

        when(assets.update()).thenReturn(true);
        splash.preload();
        verify(assets, times(3)).loadTexture(anyString());

        splash.update(0);
        splash.update(0);

        try {
            verify(assets, times(1)).loadTextureDir("img");
            verify(assets, times(1)).loadMusicDir("music");
            verify(assets, times(1)).loadSoundsDir("sounds");
        } catch(FileNotFoundException e) {
            // this will never happen due to the mock not throwing an exception
        }

        when(assets.getProgress()).thenReturn(0.0f);
        splash.update(0);
        assertEquals("Progress should be zero", splash.getProgressValue(), 0.0f, 0.0001);
        splash.setProgress(1.0f);
        splash.update(0);

        assertEquals("no error should have been thrown", null, splash.getError());
        assertTrue(splash.getAddedAssets());
        verify(windows).set(GameConfig.MAIN_MENU);
    }
}
