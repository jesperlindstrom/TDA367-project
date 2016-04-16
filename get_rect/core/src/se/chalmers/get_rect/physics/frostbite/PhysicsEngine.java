package se.chalmers.get_rect.physics.frostbite;

import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;

import java.util.ArrayList;
import java.util.List;

public class PhysicsEngine implements IPhysicsEngine {
    private List<IPhysicsObject> entities;
    private CollisionHandler collision;
    private MovementHandler movement;
    private GravityHandler gravity;

    public PhysicsEngine() {
        entities = new ArrayList<>();
        collision = new CollisionHandler();
        movement = new MovementHandler();
        gravity = new GravityHandler();
    }

    @Override
    public void add(IPhysicsObject entity) {
        entities.add(entity);
    }

    @Override
    public List<IPhysicsObject> getEntities() {
        return entities;
    }

    @Override
    public void update(double delta) {
        for (IPhysicsObject entity : entities) {
            // Check for collision
            SideData entityCollision = collision.check(entity, entities);

            // Move the entity
            movement.move(entity, entityCollision, delta);

            // Apply gravity if entity is in the air
            gravity.apply(entity, entityCollision);
        }
    }
}