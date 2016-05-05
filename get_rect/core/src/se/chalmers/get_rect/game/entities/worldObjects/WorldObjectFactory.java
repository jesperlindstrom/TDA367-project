package se.chalmers.get_rect.game.entities.worldObjects;

import se.chalmers.get_rect.game.entities.worldObjects.model.SandCastle;
import se.chalmers.get_rect.game.entities.worldObjects.view.SandCastleView;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.worldObjects.model.BoundingBox;
import se.chalmers.get_rect.game.entities.worldObjects.model.Door;
import se.chalmers.get_rect.game.entities.worldObjects.model.Trampoline;
import se.chalmers.get_rect.game.entities.worldObjects.view.TrampolineView;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.states.IState;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

public class WorldObjectFactory {

    private IRectangleFactoryAdapter rectangleFactory;
    private StateManager<? extends IState> sceneManager;

    public WorldObjectFactory(IRectangleFactoryAdapter rectangleFactory, StateManager<? extends IState> sceneManager) {
        this.rectangleFactory = rectangleFactory;
        this.sceneManager = sceneManager;
    }

    public IPhysicsEntity make(WorldObjectDataStore dataStore) {
        String type = dataStore.getType();
        Point pos = dataStore.getPosition();
        int width = dataStore.getWidth();
        int height = dataStore.getHeight();
        int path = dataStore.getPath();

        return make(type, pos, width, height, path);
    }

    public IPhysicsEntity make(String type, Point point, int width, int height, int path) {
        if (type.equals("boundingBox"))
            return makeBoundingBox(point, width, height);

        if (type.equals("trampoline"))
            return makeTrampoline(point);

        if (type.equals("door")){
            return makeDoor(point,width,height, path);
        }
        if (type.equals("sandCastle")){
            return makeSandCastle(point);
        }

        throw new EntityNotFoundException("worldObject", type);
    }

    private IPhysicsEntity makeBoundingBox(Point point, int width, int height) {
        IPhysicsModel model = new BoundingBox(point, width, height, rectangleFactory);
        return new PhysicsEntity(model, null);
    }

    private IPhysicsEntity makeTrampoline(Point point) {
        IPhysicsModel model = new Trampoline(point, rectangleFactory);
        IView view = new TrampolineView(model);

        return new PhysicsEntity(model, view);
    }

    private IPhysicsEntity makeDoor(Point point, int width, int height, int path){
        IPhysicsModel model = new Door(point,width,height, rectangleFactory, sceneManager, path);
        return new PhysicsEntity(model, null);
    }
    private IPhysicsEntity makeSandCastle(Point point) {
        IPhysicsModel model = new SandCastle(point, rectangleFactory);
        IView view = new SandCastleView(model);

        return new PhysicsEntity(model, view);
    }
}
