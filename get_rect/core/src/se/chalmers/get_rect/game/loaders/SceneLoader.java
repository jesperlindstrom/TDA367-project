package se.chalmers.get_rect.game.loaders;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
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
    private IRectangleFactoryAdapter rectangleFactory;

    public SceneLoader(String sceneName, PlayerController player, IRectangleFactoryAdapter rectangleFactory) {
        zombieLoader = new IOFacade<>("scenes/" + sceneName + "/zombies.json", ZombieDataStore.class);
        this.player = player;
        this.rectangleFactory = rectangleFactory;
    }

    public List<ZombieController> getZombies() throws FileNotFoundException {
        List<ZombieController> entities = new ArrayList<>();
        ZombieFactory factory = new ZombieFactory(player, rectangleFactory);

        for (ZombieDataStore data : zombieLoader.load()) {
            entities.add(factory.make(data));
        }

        return entities;
    }
}
