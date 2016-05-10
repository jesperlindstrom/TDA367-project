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
    private StateManager<IScene> sceneManager;
    private SceneEntityLoader sceneLoader;
    private IInputAdapter input;

    @Inject
    public SceneFactory(@Named("Player") IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, StateManager<IScene> sceneManager, SceneEntityLoader sceneLoader, IInputAdapter input) {
        this.playerEntity = playerEntity;
        this.rectangleFactory = rectangleFactory;
        this.camera = camera;
        this.sceneManager = sceneManager;
        this.sceneLoader = sceneLoader;
        this.input = input;

        System.out.println(camera);
    }

    public IScene make(String name) {
        if (name.equals("horsalsvagen"))
            return makeHorsalsvagen();

        if (name.equals("test"))
            return makeTest();

        throw new SceneNotFoundException(name);
    }

    private IScene makeHorsalsvagen() {
        return new HorsalsvagenScene(playerEntity, rectangleFactory, camera, sceneManager, sceneLoader, input);
    }

    private IScene makeTest() {
        return new TestScene(playerEntity, rectangleFactory, camera, sceneManager, sceneLoader, input);
    }
}
