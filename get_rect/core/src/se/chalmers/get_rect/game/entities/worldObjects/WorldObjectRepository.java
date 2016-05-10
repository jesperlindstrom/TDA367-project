package se.chalmers.get_rect.game.entities.worldObjects;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.AbstractRepository;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;

public class WorldObjectRepository extends AbstractRepository<WorldObjectDataStore> {
    private WorldObjectFactory factory;

    @Inject
    public WorldObjectRepository(WorldObjectFactory factory) {
        super("worldObjects", WorldObjectDataStore.class);
        this.factory = factory;
    }

    @Override
    protected IPhysicsEntity makeFromDataStore(WorldObjectDataStore data) {
        return factory.make(data.getType(), data.getPosition(), data.getWidth(), data.getHeight(), data.getPath());
    }
}
