package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import se.chalmers.get_rect.GameManager;
import se.chalmers.get_rect.adapters.IGameLoopAdapter;

public class LibGDXGameLoopAdapter extends ApplicationAdapter implements IGameLoopAdapter {
    private GameManager gameManager;
    private long lastTime = 0;

    /**
     * Starts the program.
     */
    @Override
    public void create() {
        LibGDXCameraFactoryAdapter cameraAdapterFactory = new LibGDXCameraFactoryAdapter();
        LibGDXAssetManagerAdapter assetManagerAdapter = new LibGDXAssetManagerAdapter();
        LibGDXGraphicsAdapter graphicsAdapter = new LibGDXGraphicsAdapter(new SpriteBatch(), Gdx.gl20, assetManagerAdapter);
        LibGDXInputAdapter inputAdapter = new LibGDXInputAdapter(Gdx.input);
        LibGDXRectangleFactoryAdapter rectangleFactoryAdapter = new LibGDXRectangleFactoryAdapter();

        gameManager = new GameManager(graphicsAdapter, inputAdapter, assetManagerAdapter, cameraAdapterFactory, this, rectangleFactoryAdapter);
    }

    /**
     * Game loop method.
     */
    @Override
    public void render() {
        double delta = 0;
        if (lastTime != 0) {
            delta = (double) (TimeUtils.timeSinceNanos(lastTime))/10000000;
        }

        gameManager.draw();
        gameManager.update(delta);

        lastTime = TimeUtils.nanoTime();
    }

    @Override
    public void exit() {
        Gdx.app.exit();
    }

    @Override
    public void dispose() {
        super.dispose();
        gameManager.exit();
    }
}
