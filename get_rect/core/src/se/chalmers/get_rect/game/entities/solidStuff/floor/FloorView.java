package se.chalmers.get_rect.game.entities.solidStuff.floor;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.IView;


public class FloorView implements IView{

    private IPhysicsModel model;

    public FloorView(IPhysicsModel model) {
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/entities/solids/floor.png", model.getPosition(), model.getBoundingBox().getWidth(), model.getBoundingBox().getHeight());
    }
}
