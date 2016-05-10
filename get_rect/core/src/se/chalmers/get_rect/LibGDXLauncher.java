package se.chalmers.get_rect;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.google.inject.Guice;
import com.google.inject.Injector;
import se.chalmers.get_rect.adapters.IGameLoopAdapter;
import se.chalmers.get_rect.adapters.libGDX.LibGDXModule;
import se.chalmers.get_rect.game.GameLauncher;

public class LibGDXLauncher extends ApplicationAdapter implements IGameLoopAdapter {
    private GameLauncher gameManager;

    /**
     * Starts the program.
     */
    @Override
    public void create() {
        Injector injector = Guice.createInjector(new LibGDXModule(this));

        gameManager = injector.getInstance(GameLauncher.class);
    }

    /**
     * Game loop method.
     */
    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        delta = Math.min(delta*10, (float)1/3);
        gameManager.draw();
        gameManager.update(delta);
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
