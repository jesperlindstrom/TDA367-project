package se.chalmers.get_rect.adapters;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import se.chalmers.get_rect.GameManager;

public class LibGDXGameLoopAdapter extends ApplicationAdapter implements IGameLoopAdapter{
    private GameManager gameManager;
    private long lastTime = 0;

    /**
     * Starts the program.
     */
    @Override
    public void create() {
        IGraphicsAdapter graphicsAdapter = new LibGDXGraphicsAdapter(new SpriteBatch(), Gdx.gl20);
        IInputAdapter inputAdapter = new LibGDXInputAdapter(Gdx.input);

        gameManager = new GameManager(graphicsAdapter, inputAdapter);
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

        draw();
        update(delta);

        lastTime = TimeUtils.nanoTime();
    }

    /**
     * Used to draw next frame
     */
    @Override
    public void draw() {
        gameManager.draw();
    }

    /**
     * Calls for game logic update
     * @param delta frequenzy of updating
     */
    @Override
    public void update(long delta) {
        gameManager.update(delta);
    }
}
