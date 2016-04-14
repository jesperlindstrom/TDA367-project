package se.chalmers.get_rect.physics;

public interface IPhysicsEngine {
    void add(ISolidObject entity);
    void update(double delta);
}
