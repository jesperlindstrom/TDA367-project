package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import se.chalmers.get_rect.GameManager;
import se.chalmers.get_rect.adapters.IGameLoopAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;

public class LibGDXGameLoopAdapter extends ApplicationAdapter implements IGameLoopAdapter {
    private GameManager gameManager;
    private long lastTime = 0;

    /**
     * Starts the program.
     */
    @Override
    public void create() {
        LibGDXICameraAdapter cameraAdapter = new LibGDXICameraAdapter();
        cameraAdapter.translate(1920/2,1080/2);
        LibGDXAssetManagerAdapter assetManagerAdapter = new LibGDXAssetManagerAdapter();
        IGraphicsAdapter graphicsAdapter = new LibGDXGraphicsAdapter(new SpriteBatch(), Gdx.gl20, assetManagerAdapter);
        IInputAdapter inputAdapter = new LibGDXInputAdapter(Gdx.input);

        gameManager = new GameManager(graphicsAdapter, inputAdapter, assetManagerAdapter, cameraAdapter, this);
    }

    /**
     * Gameloop method.
     */
    @Override
    public void render() {
        long delta = 0;

        if (lastTime != 0) {
            delta = TimeUtils.timeSinceNanos(lastTime);
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
