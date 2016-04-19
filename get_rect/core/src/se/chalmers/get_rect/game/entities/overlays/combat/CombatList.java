package se.chalmers.get_rect.game.entities.overlays.combat;

import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;
import java.util.stream.Collectors;

public class CombatList extends AbstractModel {

    private EntityManager foreground;

    public CombatList(EntityManager foreground) {
        super(new Point(0, 0));
        this.foreground = foreground;
    }

    public List<IEntity> get() {
        return foreground.getAll()
                .stream()
                .filter((e) -> e.getModel() instanceof AbstractCombatModel)
                .collect(Collectors.toList());
    }

}
