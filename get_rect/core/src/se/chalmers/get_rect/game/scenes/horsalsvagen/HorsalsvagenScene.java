package se.chalmers.get_rect.game.scenes.horsalsvagen;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.Entity;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.scenes.AbstractScene;

public class HorsalsvagenScene extends AbstractScene {
    public HorsalsvagenScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, CameraManager camera) {
        super("horsalsvagen", playerEntity, rectangleFactory, camera);
    }

    @Override
    public void enteringState(String previousState) {
        super.enteringState(previousState);

        // Add background image
        addEntity(layer.BACKGROUND, getBackgroundView());

        // Load all entities from the JSON data
        loadEntities();

        // Set the player position
        addPlayerAtPosition(1200, 500);


    }

    private IEntity getBackgroundView() {
        return new Entity(null, new HorsalsvagenSceneView(getCamera()));
    }
}
