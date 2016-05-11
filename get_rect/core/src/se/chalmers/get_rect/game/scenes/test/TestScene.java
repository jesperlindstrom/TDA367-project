package se.chalmers.get_rect.game.scenes.test;

import se.chalmers.get_rect.game.scenes.SceneLoader;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.scenes.AbstractScene;

public class TestScene extends AbstractScene {
    public TestScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, SceneLoader sceneLoader) {
        super("test", playerEntity, rectangleFactory, camera, sceneLoader);
    }

    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Add background image
        addEntity(getBackgroundView());

        // Set the player position
        addPlayerAtPosition(1200, 150);
    }

    private IEntity getBackgroundView() {
        return new Entity(null, new TestSceneView(getCamera()));
    }
}
