package se.chalmers.get_rect;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.adapters.*;

import static org.junit.Assert.*;

public class GameManagerTest {
    private IGame gameManager;
    private IAssetManagerAdapter assetManager;
    private IGraphicsAdapter graphics;
    private IInputAdapter input;

    @Before
    public void setup() {
        assetManager = new AssetManagerStub();
        graphics = new GraphicsAdapterStub();
        input = new InputAdapterStub();

        gameManager = new GameManager(graphics, input, assetManager);
    }

    @Test
    public void testGetters() {
        assertEquals(gameManager.getInput(), input);
        assertEquals(gameManager.getAssetManager(), assetManager);
    }
}