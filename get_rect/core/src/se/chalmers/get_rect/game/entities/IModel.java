package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.utilities.Point;

public interface IModel {
    Point getPosition();
    void update(double delta);
    void setScene(IEntityHolder scene);
    boolean shouldBeRemoved();
}