package se.chalmers.get_rect.physics;

import java.util.List;

public interface IPhysicsEngine {
    void add(IPhysicsObject entity);
    List<IPhysicsObject> getEntities();
    void update(double delta);
    void reset();
}
