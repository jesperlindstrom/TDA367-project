package se.chalmers.get_rect.game.loaders;

import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.enemies.zombie.ZombieController;
import se.chalmers.get_rect.game.entities.enemies.zombie.ZombieDataStore;
import se.chalmers.get_rect.io.IOFacade;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SceneLoader {
    private IOFacade<ZombieDataStore> zombieLoader;

    public SceneLoader(String sceneName) {
        zombieLoader = new IOFacade<>("scenes/" + sceneName + "/zombies.json", ZombieDataStore.class);
    }

    public List<IController> getZombies() throws FileNotFoundException {
        List<IController> entities = new ArrayList<>();

        for (ZombieDataStore data : zombieLoader.load()) {
            entities.add(data.extract());
        }

        return entities;
    }
}
