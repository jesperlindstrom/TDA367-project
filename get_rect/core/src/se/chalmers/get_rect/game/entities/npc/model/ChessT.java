package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.CollisionData;

public class ChessT extends AbstractNPCModel {

    private StateManager windowManager;
    private int state;
    private IPhysicsModel model;
    private boolean isInteracting = false;

    public ChessT(Point position, IRectangleFactoryAdapter rectangleFactory, StateManager windowManager, IPhysicsModel player) {
        super(position, rectangleFactory);
        this.model = player;
        this.windowManager = windowManager;
        setBoundingBox(200, 200);
        state = 1;
    }

    @Override
    public void onInteract(IModel model) {
        state = 3;
        isInteracting = true;
        windowManager.set(GameConfig.INVENTORY);
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        CollisionData collision = model.getBoundingBox().intersects(getBoundingBox());

        if (collision == null) {
            isInteracting = false;
        }

        if (isInteracting)
            return;

        if (collision == null) {
            state = 1;
        } else {
            state = 2;
        }
    }

    public int getState() {
        return state;
    }
}
