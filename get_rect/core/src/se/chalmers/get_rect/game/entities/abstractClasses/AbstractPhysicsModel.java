package se.chalmers.get_rect.game.entities.abstractClasses;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.interfaces.IPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

public abstract class AbstractPhysicsModel extends AbstractModel implements IPhysicsModel {
    private IRectangleFactoryAdapter rectangleFactory;
    private IRectangleAdapter boundingBox;
    private Point velocity;
    private boolean solid;

    protected AbstractPhysicsModel(Point position, Point velocity, boolean solid, IRectangleFactoryAdapter rectangleFactory) {
        super(position);
        this.velocity = velocity;
        this.rectangleFactory = rectangleFactory;
        this.boundingBox = rectangleFactory.make(position, 0, 0);
        this.solid = solid;
    }

    protected IRectangleFactoryAdapter getRectangleFactory() {
        return rectangleFactory;
    }

    protected void setBoundingBox(IRectangleAdapter boundingBox) {
        this.boundingBox = boundingBox;
    }

    protected void setBoundingBox(Point pos, int width, int height) {
        setBoundingBox(rectangleFactory.make(pos, width, height));
    }

    protected void setSolid(boolean solid) {
        this.solid = solid;
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
    public void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid) {
        // Default: do nothing
    }
}
