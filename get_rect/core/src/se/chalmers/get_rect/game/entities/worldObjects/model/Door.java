package se.chalmers.get_rect.game.entities.worldObjects.model;

import se.chalmers.get_rect.game.entities.AbstractInteractableModel;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

public class Door extends AbstractInteractableModel {
    private StateManager worldManager;
    private int doorPath;

    public Door(Point position, int width, int height, IRectangleFactoryAdapter factory, StateManager worldManager, int doorPath) {
        super(position.addY((-height)), new Point(0, 0), false, false,  factory);
        setBoundingBox(width, height);
        this.worldManager = worldManager;
        this.doorPath = doorPath;
    }

    @Override
    public void onInteract(IModel model) {
        triggerEvent("door", Integer.toString(doorPath));
        worldManager.set(doorPath);
    }
}
