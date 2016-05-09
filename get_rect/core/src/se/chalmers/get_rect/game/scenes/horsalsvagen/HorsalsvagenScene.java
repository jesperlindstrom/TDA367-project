package se.chalmers.get_rect.game.scenes.horsalsvagen;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.scenes.AbstractScene;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.states.StateManager;

public class HorsalsvagenScene extends AbstractScene {
    @Inject
    public HorsalsvagenScene(@Named("Player") IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, StateManager<IScene>  sceneManager) {
        super("horsalsvagen", playerEntity, rectangleFactory, camera, sceneManager);
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
