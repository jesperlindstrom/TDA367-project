package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

public class FrostbiteEngine implements IPhysicsEngine {
    private List<ISolidObject> entities;
    private int tmpCount = 0;
    private int getTmpCount = 0;

    public FrostbiteEngine() {
        entities = new ArrayList<>();
    }

    public FrostbiteEngine(List<ISolidObject> entities){
        this.entities = entities;
    }

    public void add(ISolidObject entity) {
        entities.add(entity);
    }

    @Override
    public void addAll(List<ISolidObject> newEntities) {
        entities.addAll(newEntities);
    }

    @Override
    public void update(double delta) {
        for (ISolidObject entity1 : entities) {
            for(ISolidObject entity2 : entities) {
                if (!entity1.equals(entity2) && entity1.getBoundingBox().intersects(entity2.getBoundingBox())) {
                    entity1.onCollision(entity2);
                }
            }
            entity1.setPosition(move(delta, entity1.getPosition(), entity1.getVelocity()));
        }
    }

    /**
     * Method to calculate new position when entity moves
     * @param delta
     * @param position
     * @param velocity
     * @return
     */
    @Override
    public Point move(double delta, Point position, Point velocity){
        return position.add(deltaToVelocity(delta, velocity));

    }

    /**
     * Method to calculate new position when entity moves
     * @param delta
     * @param position
     * @param velocity
     * @return
     */
    @Override
    public Point jump(double delta, Point position, Point velocity){
       return new Point(position);
    }

    /**
     * Calculate the actual movement if FPS drop
     * @param delta
     * @param velocity
     * @return
     */
    private Point deltaToVelocity(double delta, Point velocity){
        return velocity.multiply(delta);
    }
}