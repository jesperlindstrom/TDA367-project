package se.chalmers.get_rect;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.TimeUtils;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;

public class MainGame extends ApplicationAdapter {
	private GameManager gameManager;
	private long lastTime = 0;

	@Override
	public void create() {
		IGraphicsAdapter graphicsAdapter = new LibGDXGraphicsAdapter(graphics);
		IInputAdapter inputAdapter = new LibGDXInputAdapter(keyboard);

		gameManager = new GameManager(graphicsAdapter, inputAdapter);
	}

	@Override
	public void render() {
		long delta = 0;

		if (lastTime != 0) {
			delta = TimeUtils.timeSinceNanos(lastTime);
		}

		gameManager.update(delta);
		gameManager.draw();

		lastTime = TimeUtils.nanoTime();
	}
}
