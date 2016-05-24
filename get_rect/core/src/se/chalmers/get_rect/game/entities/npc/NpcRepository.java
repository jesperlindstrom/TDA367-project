package se.chalmers.get_rect.game.entities.npc;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.AbstractRepository;
import se.chalmers.get_rect.game.entities.IEntity;

import java.io.FileNotFoundException;

public class NpcRepository extends AbstractRepository<NpcDataStore, IEntity> {
    @Inject private NpcFactory factory;

    public NpcRepository() {
        super("npcs", NpcDataStore.class);
    }

    @Override
    protected IEntity makeFromDataStore(NpcDataStore data) throws FileNotFoundException {
        return factory.make(data.getType(), data.getPosition());
    }
}