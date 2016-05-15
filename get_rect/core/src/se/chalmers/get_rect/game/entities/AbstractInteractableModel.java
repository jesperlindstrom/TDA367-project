package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.StringWrapper;

public abstract class AbstractInteractableModel extends AbstractPhysicsModel implements IInteractableModel {
    private IModel model;
    private static final int DISTANCE = 150;
    private boolean showDialog;
    private String dialog;
    private String[] wrappedDialog;
    private StringWrapper wrapper;
    private int dialogIndex;



    protected AbstractInteractableModel(Point position, Point velocity, boolean solid, IRectangleFactoryAdapter rectangleFactory) {
        super(position, velocity, solid, rectangleFactory);
        wrapper = new StringWrapper();
    }

    @Override
    public void update(double delta) {
        if(model != null && model.getPosition().distanceTo(getPosition()) > DISTANCE){
            showDialog = false;
        }
    }

    @Override
    public void showDialog(String message) {
        dialog = message;
        if (message != null && !isDialogVisible()) {
            wrappedDialog = wrapper.wrap(message);
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

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData data, boolean isSolid) {
        if (otherObject instanceof IInteractorModel) {
            model = (IModel) otherObject;
        }
    }
}
