package se.chalmers.get_rect.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.libGDX.LibGDXGameLoopAdapter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameConfig.SCREEN_WIDTH;
		config.height = GameConfig.SCREEN_HEIGHT;
		config.fullscreen = GameConfig.FULLSCREEN;
		config.title = "get_rect()";
		new LwjglApplication(new LibGDXGameLoopAdapter(), config);
	}
}
