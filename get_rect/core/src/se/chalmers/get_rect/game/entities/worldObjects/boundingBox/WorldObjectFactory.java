package se.chalmers.get_rect.game.entities.worldObjects.boundingBox;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.utilities.Point;

public class WorldObjectFactory {

    private IRectangleFactoryAdapter rectangleFactory;

    public WorldObjectFactory(IRectangleFactoryAdapter rectangleFactory) {
        this.rectangleFactory = rectangleFactory;
    }

    public IPhysicsEntity make(WorldObjectDataStore dataStore) {
        String type = dataStore.getType();
        Point pos = dataStore.getPosition();
        int width = dataStore.getWidth();
        int height = dataStore.getHeight();

        return make(type, pos, width, height);
    }

    public IPhysicsEntity make(String type, Point point, int width, int height) {
        if (type.equals("boundingBox"))
            return makeBoundingBox(point, width, height);

        throw new EntityNotFoundException("worldObject", type);
    }

    private IPhysicsEntity makeBoundingBox(Point point, int width, int height) {
        IPhysicsModel model = new BoundingBox(point, width, height, rectangleFactory);
        return new PhysicsEntity(model, null);
    }
}
