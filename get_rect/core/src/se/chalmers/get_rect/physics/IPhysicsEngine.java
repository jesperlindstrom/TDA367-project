package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.utilities.Point;

import java.util.List;

public interface IPhysicsEngine {
    void add(ISolidObject entity);
    void addAll(List<ISolidObject> entities);
    void update(double delta);
    Point move(double delta, Point position, Point velocity);
    Point jump(double delta, Point position, Point velocity);
}
