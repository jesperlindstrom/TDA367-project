package se.chalmers.get_rect.game.entities.enemies;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.AbstractRepository;
import se.chalmers.get_rect.game.entities.IEntity;

public class EnemyRepository extends AbstractRepository<EnemyDataStore, IEntity> {
    @Inject private EnemyFactory factory;

    public EnemyRepository() {
        super("enemies", EnemyDataStore.class);
    }

    @Override
    protected IEntity makeFromDataStore(EnemyDataStore data) {
        return factory.make(data.getType(), data.getPosition());
    }
}