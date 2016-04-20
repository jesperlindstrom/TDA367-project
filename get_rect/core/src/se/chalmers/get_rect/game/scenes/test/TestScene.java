package se.chalmers.get_rect.game.scenes.test;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.scenes.AbstractScene;

public class TestScene extends AbstractScene {
    public TestScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, CameraManager camera) {
        super("test", playerEntity, rectangleFactory, camera);
    }

    @Override
    public void enteringState(String previousState) {
        super.enteringState(previousState);

        // Add background image
        addEntity(layer.BACKGROUND, getBackgroundView());

        // Load all entities from the JSON data
        loadEntities();

        // Set the player position
        addPlayerAtPosition(1200, 150);
    }

    private IEntity getBackgroundView() {
        return new Entity(null, new TestSceneView(getCamera()));
    }
}