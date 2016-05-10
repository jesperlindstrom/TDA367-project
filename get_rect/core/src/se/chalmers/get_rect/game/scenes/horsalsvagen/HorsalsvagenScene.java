package se.chalmers.get_rect.game.scenes.horsalsvagen;

import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.scenes.SceneLoader;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.scenes.AbstractScene;

public class HorsalsvagenScene extends AbstractScene {
    public HorsalsvagenScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, SceneLoader sceneLoader) {
        super("horsalsvagen", playerEntity, rectangleFactory, camera, sceneLoader);
    }

    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Add background image
        addEntity(getBackgroundView());

        // Load all entities from the JSON data
        loadEntities();

        // Set the player position
        addPlayerAtPosition(1200, 500);
    }

    private IEntity getBackgroundView() {
        return new Entity(null, new HorsalsvagenSceneView(getCamera()));
    }
}
