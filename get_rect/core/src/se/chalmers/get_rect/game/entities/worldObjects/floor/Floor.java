package se.chalmers.get_rect.game.entities.worldObjects.floor;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;

public class Floor implements IPhysicsModel {

    private Point position;
    private IRectangleAdapter boundingBox;
    private Point velocity;


    /**
     * This will place a floor
     *
     * @param position the upper left corner of the floor will be at this position
     * @param width this is how wide the floor will be
     * @param factory factory needed to create a boundingBox for the floor
     */
    public Floor(Point position, int width, IRectangleFactoryAdapter factory) {
        this.position = position.addY(-100);
        boundingBox = factory.make(this.position, width, 100);
        velocity = new Point(0 ,0);
    }

    @Override
    public void update() {
    }

    @Override
    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid) {

    }

    @Override
    public void setPosition(Point position) {
        this.position.setPosition(position);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setVelocity(Point velocity) {
    }

    @Override
    public Point getVelocity() {
        return velocity;
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
