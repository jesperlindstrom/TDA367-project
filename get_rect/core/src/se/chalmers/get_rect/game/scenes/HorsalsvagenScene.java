package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class HorsalsvagenScene extends AbstractScene {

    public HorsalsvagenScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, SceneLoader sceneLoader, QuestManager quests) {
        super("horsalsvagen", playerEntity, rectangleFactory, camera, sceneLoader, quests);
    }

    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Set the player position
        addPlayerAtPosition(1200, 500);
    }

    @Override
    public void respawn() {
        getPlayer().getModel().setPosition(new Point(3300, 360));
    }
}
