package se.chalmers.get_rect.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import se.chalmers.get_rect.adapters.libGDX.LibGDXGameLoopAdapter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;
		config.fullscreen = false;
		config.title = "get_rect()";
		new LwjglApplication(new LibGDXGameLoopAdapter(), config);
	}
}
