package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class HubbenScene extends AbstractScene {

    public HubbenScene(IEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, SceneLoader sceneLoader, QuestManager quests, IAudioManagerAdapter audioManager) {
        super("hubben", playerEntity, rectangleFactory, camera, sceneLoader, quests, audioManager);
    }


    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Set the player position
        addPlayerAtPosition(22, 500);
    }

    @Override
    public void respawn() {
        getPlayer().setPosition(new Point(30, 480));
    }
}

