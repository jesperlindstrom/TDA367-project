package se.chalmers.get_rect.game.entities.worldObjects.model;

import se.chalmers.get_rect.game.entities.AbstractInteractableModel;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.game.entities.IInteractableModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.states.IState;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

import java.util.Observable;
import java.util.Observer;

public class Door extends AbstractInteractableModel {
    private StateManager sceneManager;
    private int doorPath;

    public Door(Point position, int width, int height, IRectangleFactoryAdapter factory, StateManager sceneManager, int doorPath) {
        super(position.addY((-height)), new Point(0, 0), false, false,  factory);
        setBoundingBox(width, height);
        this.sceneManager = sceneManager;
        this.doorPath = doorPath;
    }

    @Override
    public void onInteract(IModel model) {
        triggerEvent("door", Integer.toString(doorPath));
        sceneManager.set(doorPath);
    }
}
