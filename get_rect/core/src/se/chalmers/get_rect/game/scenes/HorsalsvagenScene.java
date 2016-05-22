package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class HorsalsvagenScene extends AbstractScene {
    public HorsalsvagenScene(IEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, SceneLoader sceneLoader, QuestManager quests, IAudioManagerAdapter audioManager) {
        super("horsalsvagen", playerEntity, rectangleFactory, camera, sceneLoader, quests, audioManager);
    }

    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Set the player position
        addPlayerAtPosition(1200, 500);
    }

    @Override
    public void respawn() {
        getPlayer().setPosition(new Point(3300, 360));
    }
}
