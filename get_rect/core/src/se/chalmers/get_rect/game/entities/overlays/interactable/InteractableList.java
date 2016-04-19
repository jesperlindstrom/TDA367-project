package se.chalmers.get_rect.game.entities.overlays.interactable;

import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.EntityManager;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IInteractableModel;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;
import java.util.stream.Collectors;

public class InteractableList extends AbstractModel {
    private EntityManager foreground;

    public InteractableList(EntityManager foreground) {
        super(new Point(0, 0));
        this.foreground = foreground;
    }

    public List<IEntity> get() {
        return foreground.getAll()
                .stream()
                .filter((e) -> e.getModel() instanceof IInteractableModel)
                .collect(Collectors.toList());
    }

}
