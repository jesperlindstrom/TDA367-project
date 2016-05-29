package se.chalmers.get_rect.game.entities.worldObjects;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.worldObjects.model.*;
import se.chalmers.get_rect.game.entities.worldObjects.view.*;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

public class WorldObjectFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject @Named("World") private StateManager worldManager;
    @Inject private ICamera camera;
    @Inject private IAudioManagerAdapter audioManager;
    @Inject private QuestManager questManager;
    @Inject private WeaponRepository weaponRepository;

    public IEntity make(String type, Point point, int width, int height, int path, Point target) {
        if (type.equals("boundingBox"))
            return makeBoundingBox(point, width, height);

        if (type.equals("trampoline"))
            return makeTrampoline(point);

        if (type.equals("computer"))
            return makeComputer(point);

        if (type.equals("door")){
            return makeDoor(point,width,height, path);
        }
        if (type.equals("sandCastle")){
            return makeSandCastle(point);
        }
        if (type.equals("coffee")) {
            return makeCoffee(point);
        }
        if (type.equals("horsalsvagenBg")) {
            return makeHorsalsvagenBg();
        }
        if (type.equals("portal")) {
            return makePortal(point, target, width, height);
        }
        if (type.equals("pitfall")) {
            return makePitfall(point, width, height);
        }
        if (type.equals("trapfloor")) {
            return makeTrapfloor(point, width, height);
        }
        if (type.equals("testBg")) {
            return makeTestBg();
        }
        if (type.equals("hubbenBg")) {
            return makeHubbenBg();
        }
        if (type.equals("caveBg")) {
            return makeCaveBg();
        }

        throw new EntityNotFoundException("worldObject", type);
    }

    private IEntity makeBoundingBox(Point point, int width, int height) {
        IPhysicsModel model = new BoundingBox(point, width, height, rectangleFactory);
        return new Entity(model, null);
    }

    private IEntity makeTrampoline(Point point) {
        IPhysicsModel model = new Trampoline(point, rectangleFactory);
        IView view = new TrampolineView(model);

        return new Entity(model, view);
    }

    private IEntity makeComputer(Point point) {
        Computer model = new Computer(point, rectangleFactory, questManager);
        IView view = new ComputerView(model);

        return new Entity(model, view);
    }

    private IEntity makeDoor(Point point, int width, int height, int path){
        IPhysicsModel model = new Door(point,width,height, rectangleFactory, worldManager, path);
        return new Entity(model, null);
    }

    private IEntity makePortal(Point point, Point target, int width, int height){
        IPhysicsModel model = new Portal(point, target, width, height, rectangleFactory);
        return new Entity(model, null);
    }

    private IEntity makeSandCastle(Point point) {
        IPhysicsModel model = new SandCastle(point, rectangleFactory, questManager, weaponRepository);
        IView view = new SandCastleView(model);

        return new Entity(model, view);
    }
    private IEntity makeCoffee(Point point) {
        IPhysicsModel model = new Coffee(point, rectangleFactory, questManager);
        IView view = new CoffeeView(model);

        return new Entity(model, view);
    }

    private IEntity makePitfall(Point point, int width, int height) {
        Pitfall model = new Pitfall(point, width, height, new Point(), false, false, rectangleFactory);
        return new Entity(model, null);
    }
    private IEntity makeTrapfloor(Point point, int width, int height) {
        IPhysicsModel model = new Trapfloor(point, width, height, rectangleFactory);
        return new Entity(model, null);
    }
    private IEntity makeHorsalsvagenBg() {
        return new Entity(null, new HorsalsvagenWorldView(camera, audioManager));
    }

    private IEntity makeTestBg() {
        return new Entity(null, new TestWorldView(camera, audioManager));
    }

    private IEntity makeHubbenBg() {
        return new Entity(null, new HubbenWorldView(camera, audioManager));
    }

    private IEntity makeCaveBg() {
        return new Entity(null, new CaveWorldView(camera, audioManager));
    }
}
