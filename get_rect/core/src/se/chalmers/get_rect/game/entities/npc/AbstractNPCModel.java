package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

public abstract class AbstractNPCModel extends AbstractPhysicsModel implements INpcModel {
    private IModel model;
    private static final int DISTANCE = 200;
    private boolean showDialog;
    private boolean showInteractionHint;
    private String dialog;

    protected AbstractNPCModel(Point position, Point velocity, boolean solid, IRectangleFactoryAdapter rectangleFactory) {
        super(position, velocity, solid, rectangleFactory);
    }

    @Override
    public void update() {
        if(model != null && model.getPosition().distanceTo(getPosition()) > DISTANCE){
            showInteractionHint = false;
            showDialog = false;
            dialog = "";
        }
    }

    @Override
    public QuestState getQuestState() {
        return QuestState.UNAVAILABLE;
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
    public boolean showInteractionHint() {
        return showInteractionHint;
    }

    @Override
    public String getDialog() {
        return dialog;
    }


    @Override
    public void onCollision(IPhysicsObject otherObject, SideData data, boolean isSolid) {
        if (otherObject instanceof Player) {
            model = (IModel) otherObject;
            showInteractionHint = true;
        }
    }
}
