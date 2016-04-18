package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.*;

public class TestScene extends AbstractScene {
    public TestScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, CameraManager camera) {
        super("test", playerEntity, rectangleFactory, camera);
        setBackgroundImage("img/backgrounds/background.png");
    }

    @Override
    public void enteringState(String previousState) {
        super.enteringState(previousState);
        addPlayerAtPosition(1200, 150);
    }
}
