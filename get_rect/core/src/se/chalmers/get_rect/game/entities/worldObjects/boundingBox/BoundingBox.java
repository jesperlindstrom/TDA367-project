package se.chalmers.get_rect.game.entities.worldObjects.boundingBox;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.utilities.Point;

public class BoundingBox extends AbstractPhysicsModel {
    /**
     * This will place a solid bounding box
     * @param position The lower left corner of the boundingBox
     * @param width The boundingBox width
     * @param height The boundingBox height
     * @param factory Factory needed to create a boundingBox for the boundingBox
     */
    public BoundingBox(Point position, int width, int height, IRectangleFactoryAdapter factory) {
        super(position.addY((-height)), new Point(0, 0), true, factory);
        setBoundingBox(getPosition(), width, height);
    }
}
