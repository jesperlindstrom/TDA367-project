package se.chalmers.get_rect.game.entities.overlays.model;

import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;
import java.util.stream.Collectors;

public class CombatList extends AbstractModel {

    private List<IModel> models;

    public CombatList(List<IModel> models) {
        super(new Point(0, 0));
        this.models = models;
    }

    public List<IModel> get() {
        return models
                .stream()
                .filter((e) -> e instanceof AbstractCombatModel)
                .collect(Collectors.toList());
    }

}
