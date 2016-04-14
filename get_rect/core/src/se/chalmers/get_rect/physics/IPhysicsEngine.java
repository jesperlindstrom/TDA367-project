package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.utilities.Point;

import java.util.List;

public interface IPhysicsEngine {
    void add(ISolidObject entity);
    void update(double delta);
    Point move(double delta, Point position, Point velocity);
}
