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
        super(position.addY((-height)), new Point(0, 0), true, factory);
        setBoundingBox(width, height);
        this.sceneManager = sceneManager;
        this.doorPath = doorPath;
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
        triggerEvent("door", "interacted", doorPath);
        sceneManager.set(doorPath);
    }
}
