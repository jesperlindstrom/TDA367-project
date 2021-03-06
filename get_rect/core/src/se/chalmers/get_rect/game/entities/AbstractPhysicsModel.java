package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.physics.CollisionData;

public abstract class AbstractPhysicsModel extends AbstractModel implements IPhysicsModel {
    private IRectangleFactoryAdapter rectangleFactory;
    private IRectangleAdapter boundingBox;
    private Point velocity;
    private boolean solid;
    private boolean affectedByGravity;

    protected AbstractPhysicsModel(Point position, Point velocity, boolean solid, boolean affectedByGravity, IRectangleFactoryAdapter rectangleFactory) {
        super(position);
        this.velocity = velocity;
        this.rectangleFactory = rectangleFactory;
        this.affectedByGravity = affectedByGravity;
        this.boundingBox = rectangleFactory.make(position, 0, 0);
        this.solid = solid;
    }

    protected IRectangleFactoryAdapter getRectangleFactory() {
        return rectangleFactory;
    }

    protected void setBoundingBox(IRectangleAdapter boundingBox) {
        this.boundingBox = boundingBox;
    }

    protected void setBoundingBox(int width, int height) {
        setBoundingBox(rectangleFactory.make(getPosition(), width, height));
    }

    @Override
    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void setPosition(Point position) {
        super.setPosition(position);
        boundingBox.setPosition(position);
    }

    @Override
    public void setVelocity(Point velocity) {
        this.velocity = new Point(velocity);
    }

    @Override
    public Point getVelocity() {
        return new Point(velocity);
    }

    @Override
    public boolean isSolid() {
        return solid;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid) {
        // Default: do nothing
    }

    @Override
    public boolean isAffectedByGravity() {
        return affectedByGravity;
    }
}
