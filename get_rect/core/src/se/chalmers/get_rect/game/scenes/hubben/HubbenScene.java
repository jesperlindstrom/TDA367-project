package se.chalmers.get_rect.game.scenes.hubben;

import se.chalmers.get_rect.game.entities.Entity;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.scenes.AbstractScene;
import se.chalmers.get_rect.game.scenes.SceneLoader;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;

public class HubbenScene extends AbstractScene {
    public HubbenScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, SceneLoader sceneLoader) {
        super("hubben", playerEntity, rectangleFactory, camera, sceneLoader);
    }


    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Add background image
        addEntity(getBackgroundView());

        // Set the player position
        addPlayerAtPosition(22, 500);
    }

    private IEntity getBackgroundView() {
        return new Entity(null, new HubbenSceneView(getCamera()));
    }
}

