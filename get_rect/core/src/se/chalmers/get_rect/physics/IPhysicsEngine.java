package se.chalmers.get_rect.physics;

public interface IPhysicsEngine {
    void add(IPhysicsObject entity);
    void update(double delta);
}
