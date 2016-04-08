package se.chalmers.get_rect.game.loaders;

import se.chalmers.get_rect.game.entities.enemies.zombie.ZombieController;
import se.chalmers.get_rect.game.entities.enemies.zombie.ZombieDataStore;
import se.chalmers.get_rect.game.entities.enemies.zombie.ZombieFactory;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.io.IOFacade;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SceneLoader {
    private IOFacade<ZombieDataStore> zombieLoader;
    private PlayerController player;

    public SceneLoader(String sceneName, PlayerController player) {
        zombieLoader = new IOFacade<>("scenes/" + sceneName + "/zombies.json", ZombieDataStore.class);
        this.player = player;
    }

    public List<ZombieController> getZombies() throws FileNotFoundException {
        List<ZombieController> entities = new ArrayList<>();
        ZombieFactory factory = new ZombieFactory(player);

        for (ZombieDataStore data : zombieLoader.load()) {
            entities.add(factory.make(data));
        }

        return entities;
    }
}
