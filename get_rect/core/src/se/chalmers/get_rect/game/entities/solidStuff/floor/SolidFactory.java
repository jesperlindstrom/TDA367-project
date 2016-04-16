package se.chalmers.get_rect.game.entities.solidStuff.floor;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.PhysicsEntity;
import se.chalmers.get_rect.utilities.Point;

/**
 * Created by Simon on 16-04-16.
 */
public class SolidFactory {

    private IRectangleFactoryAdapter rectangleFactory;

    public SolidFactory(IRectangleFactoryAdapter rectangleFactory) {
        this.rectangleFactory = rectangleFactory;

    }

    public IPhysicsEntity make(SolidsDataStore dataStore) {
        return make(dataStore.getPosition(), dataStore.getWidth());
    }

    public IPhysicsEntity make(Point point, int width) {
        IPhysicsModel model = new Floor(point, width, rectangleFactory);
        IView view = new FloorView(model);

        return new PhysicsEntity(model, view);
    }
}
