package se.chalmers.get_rect.game.entities.worldObjects.door;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.game.entities.IInteractableModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.utilities.Point;

public class Door extends AbstractPhysicsModel implements IInteractableModel {
    /**
     * This will place a solid bounding box
     * @param position The lower left corner of the boundingBox
     * @param width The boundingBox width
     * @param height The boundingBox height
     * @param factory Factory needed to create a boundingBox for the boundingBox
     */
    public Door(Point position, int width, int height, IRectangleFactoryAdapter factory) {
        super(position.addY((-height)), new Point(0, 0), true, factory);
        setBoundingBox(getPosition(), width, height);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void setVelocity(Point vel) {
        // Do nothing. We don't want gravity applied to this object.
    }


    @Override
    public void onInteract(IModel model) {

    }

    @Override
    public void showDialog(String text) {

    }

    @Override
    public String getDialog() {
        return null;
    }

    @Override
    public boolean isDialogVisible() {
        return false;
    }
}
