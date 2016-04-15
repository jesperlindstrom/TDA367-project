package se.chalmers.get_rect.game.loaders;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.enemies.zombie.ZombieDataStore;
import se.chalmers.get_rect.game.entities.enemies.zombie.ZombieFactory;
import se.chalmers.get_rect.io.IOFacade;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SceneLoader {
    private IOFacade<ZombieDataStore> zombieLoader;
    private IPhysicsModel player;
    private IRectangleFactoryAdapter rectangleFactory;

    public SceneLoader(String sceneName, IPhysicsEntity player, IRectangleFactoryAdapter rectangleFactory) {
        zombieLoader = new IOFacade<>("scenes/" + sceneName + "/zombies.json", ZombieDataStore.class);
        this.player = player.getModel();
        this.rectangleFactory = rectangleFactory;
    }

    public List<IPhysicsEntity> getZombies() throws FileNotFoundException {
        List<IPhysicsEntity> entities = new ArrayList<>();
        ZombieFactory factory = new ZombieFactory(player, rectangleFactory);

        for (ZombieDataStore data : zombieLoader.load()) {
            entities.add(factory.make(data));
        }

        return entities;
    }
}
