package se.chalmers.get_rect.game.entities.worldObjects;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.AbstractRepository;
import se.chalmers.get_rect.game.entities.IEntity;

import java.io.FileNotFoundException;

public class WorldObjectRepository extends AbstractRepository<WorldObjectDataStore, IEntity> {
    @Inject private WorldObjectFactory factory;

    public WorldObjectRepository() {
        super("worldObjects", WorldObjectDataStore.class);
    }

    @Override
    protected IEntity makeFromDataStore(WorldObjectDataStore data) throws FileNotFoundException {
        return factory.make(data.getType(), data.getPosition(), data.getWidth(), data.getHeight(), data.getPath());
    }
}
