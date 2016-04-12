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
    private ICameraFactoryAdapter cameraFactory;
    private IGameLoopAdapter gameLoop;
    private IRectangleFactoryAdapter rectangleFactory;

    @Before
    public void setup() {
        assetManager = new AssetManagerStub();
        graphics = mock(GraphicsAdapterStub.class);
        input = new InputAdapterStub();
        cameraFactory = new CameraFactoryAdapterStub();
        gameLoop = new GameLoopAdapterStub();
        rectangleFactory = new RectangleFactoryAdapterStub();

        gameManager = new GameManager(graphics, input, assetManager, cameraFactory, gameLoop, rectangleFactory);
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