package se.chalmers.get_rect.game.scenes;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.scenes.horsalsvagen.HorsalsvagenScene;
import se.chalmers.get_rect.game.scenes.test.TestScene;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.states.StateManager;

public class SceneFactory {
    private IPhysicsEntity playerEntity;
    private IRectangleFactoryAdapter rectangleFactory;
    private ICamera camera;
    private SceneLoader sceneLoader;

    @Inject
    public SceneFactory(@Named("Player") IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, StateManager<IScene> sceneManager, SceneLoader sceneLoader, IInputAdapter input) {
        this.playerEntity = playerEntity;
        this.rectangleFactory = rectangleFactory;
        this.camera = camera;
        this.sceneLoader = sceneLoader;
    }

    public IScene make(String name) {
        if (name.equals("horsalsvagen"))
            return makeHorsalsvagen();

        if (name.equals("test"))
            return makeTest();

        throw new SceneNotFoundException(name);
    }

    private IScene makeHorsalsvagen() {
        return new HorsalsvagenScene(playerEntity, rectangleFactory, camera, sceneLoader);
    }

    private IScene makeTest() {
        return new TestScene(playerEntity, rectangleFactory, camera, sceneLoader);
    }
}
