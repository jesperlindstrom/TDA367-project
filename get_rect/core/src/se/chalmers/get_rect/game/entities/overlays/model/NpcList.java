package se.chalmers.get_rect.game.entities.overlays.model;

import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.npc.model.INpcModel;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;
import java.util.stream.Collectors;

public class NpcList extends AbstractModel {
    private List<IModel> models;

    public NpcList(List<IModel> models) {
        super(new Point(0, 0));
        this.models = models;
    }

    public List<IModel> get() {
        return models
                .stream()
                .filter((e) -> e instanceof INpcModel)
                .collect(Collectors.toList());
    }
}
