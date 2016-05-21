package se.chalmers.get_rect.game.entities.worldObjects;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.entities.worldObjects.model.SandCastle;
import se.chalmers.get_rect.game.entities.worldObjects.view.*;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.worldObjects.model.BoundingBox;
import se.chalmers.get_rect.game.entities.worldObjects.model.Door;
import se.chalmers.get_rect.game.entities.worldObjects.model.Trampoline;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

public class WorldObjectFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject @Named("Scene") private StateManager sceneManager;
    @Inject private ICamera camera;
    @Inject private IAudioManagerAdapter audioManager;
    @Inject private QuestManager questManager;

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
        if (type.equals("horsalsvagenBg")) {
            return makeHorsalsvagenBg();
        }
        if (type.equals("testBg")) {
            return makeTestBg();
        }
        if (type.equals("hubbenBg")) {
            return makeHubbenBg();
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
        IPhysicsModel model = new SandCastle(point, rectangleFactory, questManager);
        IView view = new SandCastleView(model);

        return new PhysicsEntity(model, view);
    }
    private IPhysicsEntity makeHorsalsvagenBg() {
        return new PhysicsEntity(null, new HorsalsvagenSceneView(camera, audioManager));
    }

    private IPhysicsEntity makeTestBg() {
        return new PhysicsEntity(null, new TestSceneView(camera, audioManager));
    }

    private IPhysicsEntity makeHubbenBg() {
        return new PhysicsEntity(null, new HubbenSceneView(camera, audioManager));
    }
}
