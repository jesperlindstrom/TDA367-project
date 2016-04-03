package se.chalmers.get_rect;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.log.GameLog;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.instanceOf;

public class GameManagerTest {
    private GameManager gameManager;
    private IAssetManagerAdapter assetManager;
    private IGraphicsAdapter graphics;
    private IInputAdapter input;

    @Before
    public void setup() {
        assetManager = new AssetManagerStub();
        graphics = mock(GraphicsAdapterStub.class);
        input = new InputAdapterStub();

        gameManager = new GameManager(graphics, input, assetManager);
    }

    @Test
    public void testGetters() {
        assertEquals(gameManager.getInput(), input);
        assertEquals(gameManager.getAssetManager(), assetManager);
        assertThat(gameManager.getGameLog(), instanceOf(GameLog.class));
    }

    @Test
    public void testDraw() {
        gameManager.draw();
        verify(graphics, times(1)).clear();
    }

    @Test
    public void testUpdate() {
        gameManager.update(0);
    }
}