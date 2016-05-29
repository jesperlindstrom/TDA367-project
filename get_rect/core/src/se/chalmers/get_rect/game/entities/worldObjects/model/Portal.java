package se.chalmers.get_rect.game.entities.worldObjects.model;

import se.chalmers.get_rect.game.entities.AbstractInteractableModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class Portal extends AbstractInteractableModel{

    private Point target;

    public Portal(Point point, Point target, int width, int height, IRectangleFactoryAdapter rectangleFactory) {
        super(point, new Point(), false, false, rectangleFactory);
        setPosition(point);
        setBoundingBox(width, height);
        this.target = target;
    }

    @Override
    public void onInteract(IModel model) {
        if (model instanceof Player) {
            ((Player) model).setPosition(target);
        }
    }
}
