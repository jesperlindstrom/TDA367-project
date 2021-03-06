package se.chalmers.get_rect.game.entities.worldObjects.model;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.physics.CollisionData;

public class Trampoline extends AbstractPhysicsModel {
    private static final int WIDTH = 270;
    private static final int HEIGHT = 75;
    private boolean gotHit;

    public Trampoline(Point position, IRectangleFactoryAdapter rectangleFactory) {
        super(position, new Point(0, 0), false, true, rectangleFactory);
        setBoundingBox();
    }

    private void setBoundingBox() {
        setBoundingBox(WIDTH, HEIGHT);
    }

    @Override
    public void setPosition(Point position) {
        super.setPosition(position);
        setBoundingBox();
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid) {
        if (collisionSide != null &&  collisionSide.top()) {
            otherObject.setVelocity(otherObject.getVelocity().setY(150));
            gotHit = true;
        }
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        gotHit = false;
    }

    public boolean isGotHit() {
        return gotHit;
    }

}