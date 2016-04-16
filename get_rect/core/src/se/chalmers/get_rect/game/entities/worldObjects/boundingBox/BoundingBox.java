package se.chalmers.get_rect.game.entities.worldObjects.boundingBox;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;

public class BoundingBox implements IPhysicsModel {
    private Point position;
    private IRectangleAdapter boundingBox;

    /**
     * This will place a solid bounding box
     * @param position The lower left corner of the boundingBox
     * @param width The boundingBox width
     * @param height The boundingBox height
     * @param factory Factory needed to create a boundingBox for the boundingBox
     */
    public BoundingBox(Point position, int width, int height, IRectangleFactoryAdapter factory) {
        this.position = position.addY((-height));
        boundingBox = factory.make(this.position, width, height);
    }

    @Override
    public void update() {

    }

    @Override
    public void setScene(IScene scene) {

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
        boundingBox.setPosition(position);
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
        return null;
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
