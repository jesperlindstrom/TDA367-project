package se.chalmers.get_rect.game.scenes.test;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.EntityCamera;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.scenes.AbstractScene;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.states.StateManager;

public class TestScene extends AbstractScene {
    @Inject
    public TestScene(@Named("Player") IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, EntityCamera camera, StateManager<IScene> sceneManager) {
        super("test", playerEntity, rectangleFactory, camera, sceneManager);
    }

    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Add background image
        addEntity(getBackgroundView());

        // Load all entities from the JSON data
        loadEntities();

        // Set the player position
        addPlayerAtPosition(1200, 150);
    }

    private IEntity getBackgroundView() {
        return new Entity(null, new TestSceneView(getCamera()));
    }
}
