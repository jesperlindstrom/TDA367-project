package se.chalmers.get_rect.physics;

import java.util.List;

public interface IPhysicsEngine {
    void add(ISolidObject entity);
    void addAll(List<ISolidObject> entities);
    void update(double delta);
}
