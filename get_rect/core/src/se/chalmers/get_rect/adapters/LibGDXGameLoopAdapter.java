package se.chalmers.get_rect.adapters;

import com.badlogic.gdx.ApplicationAdapter;
<<<<<<< HEAD
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
=======
import com.badlogic.gdx.Gdx;
>>>>>>> 3054abba04b8cbaf5182d98e9367aa183733b030
import com.badlogic.gdx.utils.TimeUtils;
import se.chalmers.get_rect.GameManager;

public class LibGDXGameLoopAdapter extends ApplicationAdapter implements IGameLoopAdapter{
	private GameManager gameManager;
	private long lastTime = 0;

	@Override
	public void create() {
		IGraphicsAdapter graphicsAdapter = new LibGDXGraphicsAdapter(new SpriteBatch());
		IInputAdapter inputAdapter = new LibGDXInputAdapter(Gdx.input);

		gameManager = new GameManager(graphicsAdapter, inputAdapter);
	}

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

	@Override
	public void draw() {
		gameManager.draw();
	}

	@Override
	public void update(long delta) {
		gameManager.update(delta);
	}
}
