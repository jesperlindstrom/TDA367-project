package se.chalmers.get_rect.game.entities.worldObjects.model;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

public class Trampoline extends AbstractPhysicsModel {
    private static final int WIDTH = 270;
    private static final int HEIGHT = 75;
    private boolean gotHit;

    public Trampoline(Point position, IRectangleFactoryAdapter rectangleFactory) {
        super(position, new Point(0, 0), false, rectangleFactory);
        setBoundingBox();
    }

    private void setBoundingBox() {
        setBoundingBox(getPosition().add(70, 20), WIDTH, HEIGHT);
    }

    @Override
    public void setPosition(Point position) {
        super.setPosition(position);
        setBoundingBox();
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid) {
        if (collisionSide.top()) {
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