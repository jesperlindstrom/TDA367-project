package se.chalmers.get_rect.game.loaders;

import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.io.IOFacade;

public class SceneLoader {
    public SceneLoader() {
        IOFacade factory = new IOFacade();

        Loader<Player> loader = factory.makeLoader("test.json");
    }
}
