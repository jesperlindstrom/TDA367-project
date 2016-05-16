package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

public class ChessT extends AbstractNPCModel {

    private StateManager windowManager;
    private int open;
    private IModel model;

    public ChessT(Point position, IRectangleFactoryAdapter rectangleFactory, StateManager windowManager) {
        super(position, rectangleFactory);
        this.windowManager = windowManager;
        setBoundingBox(200, 200);
        open = 0;
    }

    @Override
    public void onInteract(IModel model) {
        open = 1;
        this.model = model;
        windowManager.set(GameConfig.INVENTORY);
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        if (model != null && model.getPosition().distanceTo(getPosition()) > 100) {
            if (model.getPosition().distanceTo(getPosition()) < 400) {
                open = -1;
            } else {
                open = 0;
            }
        }
        if (model == null) open = -1;
    }

    public int isOpen() {
        return open;
    }
}
