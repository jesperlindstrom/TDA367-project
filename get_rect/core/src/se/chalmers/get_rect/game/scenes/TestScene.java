package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;

public class TestScene extends AbstractScene {
    public TestScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, SceneLoader sceneLoader) {
        super("test", playerEntity, rectangleFactory, camera, sceneLoader);
    }

    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Set the player position
        addPlayerAtPosition(1200, 150);
    }

}
