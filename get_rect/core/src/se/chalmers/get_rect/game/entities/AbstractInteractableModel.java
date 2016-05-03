package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

public abstract class AbstractInteractableModel extends AbstractPhysicsModel implements IInteractableModel {
    private IModel model;
    private static final int DISTANCE = 150;
    private boolean showDialog;
    private String dialog;

    protected AbstractInteractableModel(Point position, Point velocity, boolean solid, IRectangleFactoryAdapter rectangleFactory) {
        super(position, velocity, solid, rectangleFactory);
    }

    @Override
    public void update(double delta) {
        if(model != null && model.getPosition().distanceTo(getPosition()) > DISTANCE){
            showDialog = false;
            dialog = "";
        }
    }

    @Override
    public void showDialog(String message) {
        dialog = message;
        showDialog = true;
    }

    @Override
    public boolean isDialogVisible() {
        return showDialog;
    }


    @Override
    public String getDialog() {
        return dialog;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData data, boolean isSolid) {
        if (otherObject instanceof Player) {
            model = (IModel) otherObject;
        }
    }
}
