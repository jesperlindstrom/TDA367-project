package se.chalmers.get_rect.game.entities.enemies;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.AbstractRepository;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;

public class EnemyRepository extends AbstractRepository<EnemyDataStore> {
    private EnemyFactory factory;

    @Inject
    public EnemyRepository(EnemyFactory factory) {
        super("enemies", EnemyDataStore.class);
        this.factory = factory;
    }

    @Override
    protected IPhysicsEntity makeFromDataStore(EnemyDataStore data) {
        return factory.make(data.getType(), data.getPosition());
    }
}