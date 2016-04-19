package se.chalmers.get_rect.game.entities.overlays.quests;

import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.EntityManager;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.npc.INpcModel;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;
import java.util.stream.Collectors;

public class NpcList extends AbstractModel {
    private EntityManager foreground;

    public NpcList(EntityManager foreground) {
        super(new Point(0, 0));
        this.foreground = foreground;
    }

    public List<IEntity> get() {
        return foreground.getAll()
                .stream()
                .filter((e) -> e.getModel() instanceof INpcModel)
                .collect(Collectors.toList());
    }
}
