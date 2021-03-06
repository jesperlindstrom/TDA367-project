package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.event.EventSource;
import se.chalmers.get_rect.event.IEventListener;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.utilities.StringWrapper;

public abstract class AbstractInteractableModel extends AbstractPhysicsModel implements IInteractableModel {
    private EventSource eventSource;
    private IModel model;
    private static final int DISTANCE = 150;
    private boolean showDialog;
    private String[] wrappedDialog;
    private int dialogIndex;

    protected AbstractInteractableModel(Point position, Point velocity, boolean solid, boolean affectedByGravity, IRectangleFactoryAdapter rectangleFactory) {
        super(position, velocity, solid, affectedByGravity,  rectangleFactory);
        eventSource = new EventSource();
    }

    @Override
    public void update(double delta) {
        if(model != null && model.getPosition().distanceTo(getPosition()) > DISTANCE){
            showDialog = false;
        }
    }

    @Override
    public void showDialog(String message) {
        if (message != null && !isDialogVisible()) {
            wrappedDialog = StringWrapper.wrap(message);
            dialogIndex = 0;
        }
        showDialog = true;
    }

    @Override
    public boolean isDialogVisible() {
        return showDialog;
    }

    @Override
    public String getDialog() {
        if (dialogIndex >= wrappedDialog.length){
            showDialog = false;
            dialogIndex = 0;
        }
        return wrappedDialog[dialogIndex];
    }

    @Override
    public void nextDialog() {
        dialogIndex++;
    }

    protected boolean isLastDialog() {
        return wrappedDialog.length - 1 == dialogIndex;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData data, boolean isSolid) {
        if (otherObject instanceof IInteractorModel) {
            model = (IModel) otherObject;
        }
    }

    @Override
    public void removeListener(IEventListener o) {
        eventSource.removeListener(o);
    }

    @Override
    public void addListener(IEventListener o) {
        eventSource.addListener(o);
    }

    protected void triggerEvent(String type, String action) {
        eventSource.triggerEvent(type, action);
    }
}
