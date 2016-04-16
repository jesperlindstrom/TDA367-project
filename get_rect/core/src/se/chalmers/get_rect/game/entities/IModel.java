package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.game.scenes.IEntityHolder;
import se.chalmers.get_rect.utilities.Point;

public interface IModel {
    Point getPosition();
    void update();
    void setScene(IEntityHolder scene);
    boolean shouldBeRemoved();
}
